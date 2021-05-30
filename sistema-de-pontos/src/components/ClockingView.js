import React from 'react';
import Table from 'react-bootstrap/Table';
import api from '../api';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit } from '@fortawesome/free-solid-svg-icons';
import { faEraser } from '@fortawesome/free-solid-svg-icons';
import 'bootstrap/dist/css/bootstrap.min.css';

export default class ClockingView extends React.Component {

    state = {
        users: []
    }

    async componentDidMount(){
        const response = await api.get('/user');        
        if(response.status === 200){
            this.setState({ users: response.data.data });
        }
    }
    
    handleDelete(userId){
        console.log('Id => ' + userId);

        api.delete(`/user/${userId}`)
        .then(res => {
            if(res.status === 204){
                /*let users = this.state.users;
                const updatedUsers = users.filter(function(value){ 
                    return value.id !== res.data.data.id;
                });
                this.setState({users: updatedUsers});*/
                window.location.reload();
            }
        })
        .catch(error => {
            alert('Erro ao deletar usuário!' + error);
        });
    }
    
    render(){
        const {users} = this.state;        
        return (
            <div className="container mt-3">
                <h1> Visualização de Dados </h1>
                
                <Table striped bordered hover variant="dark">
                    <thead>
                        <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {users.map(user => (
                            <tr key={user.id}>
                                <td> {user.id} </td>
                                <td> {user.nome} </td>
                                <td> {user.email} </td>
                                <td> 
                                    <FontAwesomeIcon 
                                        icon={faEdit} 
                                        className="ml-1"
                                        style={{cursor:"pointer"}}
                                        onClick={() => {console.log('Editar Id => ' + user.id)}}
                                    />
                                    <FontAwesomeIcon 
                                        icon={faEraser} 
                                        className="ml-3"
                                        style={{cursor:"pointer"}}
                                        onClick={() => { this.handleDelete(user.id) }}
                                    />
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            </div>
        );
    }
}