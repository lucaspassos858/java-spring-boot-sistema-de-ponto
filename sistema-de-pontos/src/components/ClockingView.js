import React from 'react';
import Table from 'react-bootstrap/Table';
import api from '../api';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faTimes, faPlus } from '@fortawesome/free-solid-svg-icons';
import { faEraser } from '@fortawesome/free-solid-svg-icons';
import 'bootstrap/dist/css/bootstrap.min.css';
import Modal from 'react-modal';
import { Button, Form } from 'react-bootstrap';

const customStyles = {
    content : {
        top                   : '50%',
        left                  : '50%',
        right                 : 'auto',
        bottom                : 'auto',
        marginRight           : '-50%',
        transform             : 'translate(-50%, -50%)',
        width                 : '50%',
        background            : 'rgb(58 58 58)'
    }
};

export default class ClockingView extends React.Component {
    state = {
        data: [],
        dataById: {},
        date: "",
        jornadaInicio: "",
        jornadaFim: "",
        isModalOpen: false
    }

    async componentDidMount(){
        const response = await api.get('/timesheet');        
        if(response.status === 200){
            this.setState({ data: response.data.data });
        }
    }

    componentWillMount() {
        Modal.setAppElement('body');
    }

    openModal = () => {
        this.setState({ isModalOpen: true });
    }

    closeModal = () => {
        this.setState({ isModalOpen: false });
    }
    
    handleDelete(id){
        api.delete(`/timesheet/${id}`)
        .then(res => {
            if(res.status === 204){
                window.location.reload();
            }
        })
        .catch(error => {
            alert('Erro ao deletar ! => ' + error);
        });
    }

    handleEdit(id){
        api.get(`/timesheet/${id}`)
        .then(res => {
            if(res.status === 200){
                this.setState({dataById: res.data.data});
            }
        })
        .catch(error => {
            alert('Erro ao buscar dados! => ' + error);
        });

        this.openModal();
    }

    handleSubmit(dataById){
        const id = dataById.id;
        const data = this.state.date ? this.state.date : dataById.data;
        const jornadaInicio = this.state.jornadaInicio ? this.state.jornadaInicio : dataById.jornadaInicio;
        const jornadaFim = this.state.jornadaFim ? this.state.jornadaFim : dataById.jornadaFim;
        const userId = dataById.usuario.id;

        const timesheet = {
            "data": data,
            "jornadaInicio": jornadaInicio,
            "jornadaFim": jornadaFim,
            "usuario": {
                "id": userId
            } 
        }

        api.put(`/timesheet/${id}`, timesheet)
        .then(res => {
            if(res.status === 200){
                this.closeModal();
                window.location.reload();
            }
        })
        .catch(error => {
            alert('Erro ao atualizar dados!');
        });
    }

    render(){
        const {data} = this.state;   
        const {dataById} = this.state;

        console.log('dataById => ' + JSON.stringify(dataById));
        
        return (
            <div className="container mt-3">
                <h1> Visualização de Dados </h1>
                
                <Table striped bordered hover variant="dark" className="mt-4">
                    <thead>
                        <tr>
                            <th colSpan="7" style={{background: 'rgb(37 38 39)'}}> Tabela de Marcação de Ponto </th>
                            <th colSpan="7" style={{background: 'rgb(37 38 39)'}}>
                                <a href="/clocking">
                                    <FontAwesomeIcon 
                                        icon={faPlus} 
                                        className="ml-3"
                                        style={{cursor:"pointer"}}
                                    />
                                </a>
                            </th>
                        </tr>
                        <tr>
                        <th>ID</th>
                        <th>ID do Funcionário</th>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Data</th>
                        <th>Hora de Entrada</th>
                        <th>Hora de Saída</th>
                        <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {data.map(row => (
                            <tr key={row.id}>
                                <td> {row.id} </td>
                                <td> {row.usuario.id} </td>
                                <td> {row.usuario.nome} </td>
                                <td> {row.usuario.email} </td>
                                <td> {row.data} </td>
                                <td> {row.jornadaInicio} </td>
                                <td> {row.jornadaFim} </td>
                                <td> 
                                    <FontAwesomeIcon 
                                        icon={faEdit} 
                                        className="ml-1"
                                        style={{cursor:"pointer", color:"#4caf50"}}
                                        onClick={() => { this.handleEdit(row.id) }}
                                    />
                                    <FontAwesomeIcon 
                                        icon={faEraser} 
                                        className="ml-3"
                                        style={{cursor:"pointer", color:"#d32f2f"}}
                                        onClick={() => { this.handleDelete(row.id) }}
                                    />
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
                

                <Modal
                    isOpen={this.state.isModalOpen}
                    style={customStyles}
                    contentLabel="Editar Usuário">   

                        <FontAwesomeIcon 
                            icon={faTimes} 
                            style={{cursor: "pointer", marginRight: "10px"}}
                            pull="right"
                            color="white"
                            onClick={() => { this.closeModal() }}
                        />

                        <div style={{marginTop:"50px"}}>
                            <Form>
                                <Form.Group controlId="nome">
                                    <Form.Label>Data</Form.Label>
                                    <Form.Control 
                                        type="date" 
                                        defaultValue={dataById.data}
                                        onChange={e => this.setState({ date: e.target.value })}
                                    />
                                    <Form.Label>Hora de Entrada</Form.Label>
                                    <Form.Control 
                                        type="time" 
                                        step="1"
                                        defaultValue={dataById.jornadaInicio}
                                        onChange={e => this.setState({ jornadaInicio: e.target.value })}
                                    />
                                    <Form.Label>Hora de Saída</Form.Label>
                                    <Form.Control 
                                        type="time" 
                                        step="1"
                                        defaultValue={dataById.jornadaFim}
                                        onChange={e => this.setState({ jornadaFim: e.target.value })}
                                    />
                                </Form.Group>


                                <center>
                                    <Button 
                                        onClick={() => {this.handleSubmit(dataById)}}
                                        style={{
                                            background: "#0b0c0c", 
                                            width: "40%", 
                                            fontWeight: "380",
                                            letterSpacing: "4px"
                                        }}>
                                        Atualizar
                                    </Button>
                                </center>
                            </Form>
                        </div>
                </Modal>
            </div>
        );
    }
}