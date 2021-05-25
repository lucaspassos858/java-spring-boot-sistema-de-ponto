package br.edu.ifsp.point.models.vo;

import br.edu.ifsp.point.models.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

public class TimesheetVO {

    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date data;

    private Time jornadaInicio;

    private Time jornadaFim;

    private UserVO usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getJornadaInicio() {
        return jornadaInicio;
    }

    public void setJornadaInicio(Time jornadaInicio) {
        this.jornadaInicio = jornadaInicio;
    }

    public Time getJornadaFim() {
        return jornadaFim;
    }

    public void setJornadaFim(Time jornadaFim) {
        this.jornadaFim = jornadaFim;
    }

    public UserVO getUsuario() {
        return usuario;
    }

    public void setUsuario(UserVO usuario) {
        this.usuario = usuario;
    }
}
