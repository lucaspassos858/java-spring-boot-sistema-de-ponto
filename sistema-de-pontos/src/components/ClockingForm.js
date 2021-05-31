import React from "react";
import { useForm } from "react-hook-form";
import api from "../api";
import { useHistory } from "react-router-dom";


function ClockingForm() {
  const history = useHistory();
  
  const {
    register,
    handleSubmit
  } = useForm();

  const onSubmit = (data) => {
    const login = {
      email: data.email,
      senha: data.senha
    }

    api.post('/user/login', login)
    .then(res => {

      if(res.status === 200){
        const userId = res.data.data.id;
        const timesheet = {
          data: data.data,
          jornadaInicio: data.jornadaInicio,
          jornadaFim: data.jornadaFim,
          usuario: {
            id: userId
          }
        }  
        api.post('/timesheet', timesheet)
        .then(res => {
          if(res.status === 201){
            history.replace("/view");
          }
        });
      }

    })
    .catch(() => {
      window.alert('Funcionário não encontrado no sistema');
    });
  };

  return (
    <div id="wrapper">
      <div>
        <h1> Sistema de Ponto </h1>
      </div>
      <div id="container">
        <h2>Registro de Ponto</h2>

        <form onSubmit={handleSubmit(onSubmit)}>
          <label> Email </label>
          <input {...register("email")} type="email" placeholder="Digite seu e-mail" required/>
          <label> Senha </label>
          <input {...register("senha")} type="password" placeholder="Digite sua senha" required/>
          <label> Data </label>
          <input {...register("data")} type="date" required/>
          <label> Hora de Entrada </label>
          <input {...register("jornadaInicio")} type="time" step="1" required/>
          <label> Hora de Saída: </label>
          <input {...register("jornadaFim")} type="time" step="1" required/>
          <input type="submit" value="Registrar Ponto"/>
        </form>
      </div>
    </div>
  ); 
}

export default ClockingForm;


