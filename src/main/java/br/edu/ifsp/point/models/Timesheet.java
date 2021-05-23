package br.edu.ifsp.point.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "tb_timesheet")
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @Column(nullable = false)
    private Time start;

    @Column(nullable = false)
    private Time end;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
