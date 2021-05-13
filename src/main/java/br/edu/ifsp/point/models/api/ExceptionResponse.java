package br.edu.ifsp.point.models.api;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ExceptionResponse {

	private Date timestamp;
	private HttpStatus status;
	private String error;

	public ExceptionResponse(HttpStatus status, String error) {
		this.timestamp = new Date();
		this.status = status;
		this.error = error;
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

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
