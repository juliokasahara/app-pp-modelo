package br.com.app.modelo.domain.hendler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import br.com.app.modelo.domain.exception.BadRequestException;
import br.com.app.modelo.domain.exception.BadRequestExceptionDetails;
import br.com.app.modelo.domain.exception.ExceptionDetails;
import br.com.app.modelo.domain.exception.ValidationExceptionDetails;

@RestControllerAdvice
public class RestExecptionHendler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BadRequestExceptionDetails> handeBadRequestException(BadRequestException bad){
		return new ResponseEntity<>(
				BadRequestExceptionDetails.builder()
					.timestemp(LocalDateTime.now())
					.status(HttpStatus.BAD_REQUEST.value())
					.title("Bad Request Execption, check the Documetation")
					.details(bad.getMessage())
					.developerMessage(bad.getClass().getName())
					.build(), HttpStatus.BAD_REQUEST);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<FieldError> fieldErrors =  exception.getBindingResult().getFieldErrors();
		
		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
		String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
		
		return new ResponseEntity<>(
				ValidationExceptionDetails.builder()
					.timestemp(LocalDateTime.now())
					.status(HttpStatus.BAD_REQUEST.value())
					.title("Bad Request Execption, Invalid Fields")
					.details("check the fields errors")
					.developerMessage(exception.getClass().getName())
					.fields(fields)
					.fieldsMessage(fieldsMessage)
					.build(), HttpStatus.BAD_REQUEST);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionDetails exceptionDetails = ExceptionDetails.builder()
			.timestemp(LocalDateTime.now())
			.status(status.value())
			.title(ex.getCause().getMessage())
			.details(ex.getMessage())
			.developerMessage(ex.getClass().getName())
			.build();
	
		return new ResponseEntity<>(exceptionDetails, headers, status);
	}

}
