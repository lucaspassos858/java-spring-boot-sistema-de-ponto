import React from "react";
import ReactDOM from "react-dom";
import { useForm } from "react-hook-form";

import "./styles.css";

function App() {
  const {
    register,
    handleSubmit
  } = useForm();
  const onSubmit = (data) => {
    console.log('Nome => ' + data.name);
    console.log('Hora de Entrada => ' + data.horaEntrada);
    console.log('Hora de Saída => ' + data.horaSaida);
  };

  return (
    <div>
      <div>
        <h1> Bem Vindo(a) ao Sistema de Pontos </h1>
      </div>
      <div style={{border:"1px solid gray", padding:"20px", width:"40%", margin:"0 auto"}}>
        <form onSubmit={handleSubmit(onSubmit)}>
          <label> Nome </label>
          <input {...register("name")} required/>
          <label> Hora de Entrada </label>
          <input {...register("horaEntrada")} type="time" required/>
          <label> Hora de Saída: </label>
          <input {...register("horaSaida")} type="time" required/>
          <input type="submit" />
        </form>
      </div>
    </div>
  );
}

const rootElement = document.getElementById("root");
ReactDOM.render(<App />, rootElement);
