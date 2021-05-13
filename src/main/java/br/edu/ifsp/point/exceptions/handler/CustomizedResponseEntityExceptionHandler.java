package br.edu.ifsp.point.exceptions.handler;

import br.edu.ifsp.point.models.api.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	private ResponseEntity<ExceptionResponse> exceptionResponse(HttpStatus status, String message) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(status, message);
		return new ResponseEntity<>(exceptionResponse, status);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex){
		return exceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	}
	
}
