package br.edu.ifsp.point.models.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class ExceptionResponse {

	@SuppressWarnings("unused")
	private Date timestamp;
	@SuppressWarnings("unused")
	private HttpStatus status;
	@SuppressWarnings("unused")
	private String error;

	public ExceptionResponse(HttpStatus status, String error) {
		this.timestamp = new Date();
		this.status = status;
		this.error = error;
	}

}
