import React from "react";
import ReactDOM from "react-dom";
import { useForm } from "react-hook-form";
import axios from 'axios';

import "./styles.css";

function App() {
  const {
    register,
    handleSubmit
  } = useForm();
  const onSubmit = (data) => {
    const person = {
      name: data.name,
      horaEntrada: data.horaEntrada,
      horaSaida: data.horaSaida,
    }
    axios.post(`https://jsonplaceholder.typicode.com/users`, { person })
      .then(res => {
        console.log(res);
        console.log(res.data);
      })
  };

  return (
    <div>
      <div>
        <h1> Bem Vindo(a) ao Sistema de Ponto </h1>
      </div>
      <div 
        style = {{
          border:"1px solid gray", 
          padding:"20px", 
          width:"40%", 
          margin:"0 auto", 
          background:"#3a3a3a", 
          borderRadius:"5px"
        }}>
        <form onSubmit={handleSubmit(onSubmit)}>
          <label> Nome </label>
          <input {...register("name")} placeholder="Digite seu nome" required/>
          <label> Hora de Entrada </label>
          <input {...register("horaEntrada")} type="time" required/>
          <label> Hora de Saída: </label>
          <input {...register("horaSaida")} type="time" required/>
          <input type="submit" value="Registrar"/>
        </form>
      </div>
    </div>
  );
}

const rootElement = document.getElementById("root");
ReactDOM.render(<App />, rootElement);
