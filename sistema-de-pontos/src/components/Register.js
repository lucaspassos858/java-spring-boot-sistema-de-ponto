import React from "react";
import { useForm } from "react-hook-form";
import axios from 'axios';

function Register() {
    const {
        register,
        handleSubmit
    } = useForm();

    const onSubmit = (data) => {
    const person = {
        name: data.name,
        email: data.email,
        password: data.password,
    }

    console.log('person => ' + JSON.stringify(person));

    axios.post(`https://jsonplaceholder.typicode.com/users`, { person })
        .then(res => {
            console.log(res.data);
        });
    };
  return (
    <div>
      <div>
        <h1> Bem Vindo(a) ao Sistema de Ponto </h1>
      </div>
      <div id="container">
        <h2>Realize seu Cadastro</h2>

        <form onSubmit={handleSubmit(onSubmit)}>
          <label> Nome </label>
          <input {...register("name")} placeholder="Digite seu nome" required/>
          <label> Email </label>
          <input {...register("email")} type="email" placeholder="Digite seu e-mail" required/>
          <label> Senha </label>
          <input {...register("password")} type="password" placeholder="Digite sua senha" required/>
          <input type="submit" value="Cadastrar"/>
        </form>
      </div>
    </div>
  );
}

export default Register;

