package br.edu.ifsp.point.models.api;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class SuccessResponse {

    private Date timestamp;
    private HttpStatus status;
    private Object data;

    public SuccessResponse(HttpStatus status, Object data) {
        this.timestamp = new Date();
        this.status = status;
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
