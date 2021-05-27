import React from "react";
import { useForm } from "react-hook-form";
import axios from 'axios';

function ClockingForm() {

  const {
    register,
    handleSubmit
  } = useForm();

  const onSubmit = (data) => {
    const personClocking = {
      date: data.date,
      start: data.start,
      end: data.end,
    }

    console.log('personClocking => ' + JSON.stringify(personClocking));

    axios.post(`https://jsonplaceholder.typicode.com/users`, { personClocking })
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
        <h2>Registro de Ponto</h2>

        <form onSubmit={handleSubmit(onSubmit)}>
          <label> Data </label>
          <input {...register("date")} type="date" required/>
          <label> Hora de Entrada </label>
          <input {...register("start")} type="time" required/>
          <label> Hora de Saída: </label>
          <input {...register("end")} type="time" required/>
          <input type="submit" value="Registrar Ponto"/>
        </form>
      </div>
    </div>
  ); 
}

export default ClockingForm;


