import React from "react";
import { useForm } from "react-hook-form";
import { useHistory } from "react-router-dom";
import api from '../api';

function Register() {
  const history = useHistory();

  const {
      register,
      handleSubmit
  } = useForm();
    
  const onSubmit = (data) => {
    const person = {
        nome: data.nome,
        email: data.email,
        senha: data.senha,
    }
    console.log('person => ' + person);
    api.post('/user', person)
    .then(res => {
      if(res.status === 201){
        history.replace("/view");
      } 
    });
  };
    
  return (
    <div id="wrapper" className="mt-4">
      <div>
        <h1> Sistema de Ponto </h1>
      </div>
      <div id="container">
        <h2>Cadastro de Funcionário</h2>

        <form onSubmit={handleSubmit(onSubmit)}>
          <label> Nome </label>
          <input {...register("nome")} placeholder="Digite seu nome" required/>
          <label> Email </label>
          <input {...register("email")} type="email" placeholder="Digite seu e-mail" required/>
          <label> Senha </label>
          <input {...register("senha")} type="password" placeholder="Digite sua senha" required/>
          <input type="submit" value="Cadastrar"/>
        </form>
      </div>
    </div>
  );
}

export default Register;

