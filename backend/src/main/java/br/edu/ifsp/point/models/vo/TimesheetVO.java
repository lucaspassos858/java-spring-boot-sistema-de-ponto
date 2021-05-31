package br.edu.ifsp.point.models.vo;

import br.edu.ifsp.point.models.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
public class TimesheetVO {

    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date data;

    private Time jornadaInicio;

    private Time jornadaFim;

    private UserVO usuario;

}
