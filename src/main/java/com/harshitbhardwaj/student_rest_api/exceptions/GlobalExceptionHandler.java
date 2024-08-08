package com.harshitbhardwaj.student_rest_api.exceptions;

import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.harshitbhardwaj.student_rest_api.dtos.ErrorDTO;

import jakarta.validation.UnexpectedTypeException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private ErrorDTO buildErrorDTO(String message, HttpStatus status) {
		return ErrorDTO.builder().successStatus(false).httpStatus(status).message(message).build();
	}

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ErrorDTO> appExceptionHandler(ApplicationException ex) {
		String message = ex.getMessage();
		HttpStatus httpStatus = ex.getStatus();
		ErrorDTO errorDTO = buildErrorDTO(message, httpStatus);
		return new ResponseEntity<>(errorDTO, httpStatus);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDTO> invalidArgumentExceptionHandler(MethodArgumentNotValidException ex) {
		String message = ex.getBindingResult().getAllErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
		ErrorDTO errorDTO = buildErrorDTO(message, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorDTO> invalidArgumentExceptionHandler(MethodArgumentTypeMismatchException ex) {
		String message = "Make sure that correct argument is passed in url (Long id)";
		ErrorDTO errorDTO = buildErrorDTO(message, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ErrorDTO> noResourceFoundExceptionHandler(NoResourceFoundException ex) {
		String message = ex.getMessage();
		ErrorDTO errorDTO = buildErrorDTO(message, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorDTO> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
		String message = "Check the JSON Body being passed in the request";
		ErrorDTO errorDTO = buildErrorDTO(message, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorDTO> httpRequestMethodNotSupportedExceptionHandler(
			HttpRequestMethodNotSupportedException ex) {
		String message = "Request method not supported";
		ErrorDTO errorDTO = buildErrorDTO(message, HttpStatus.METHOD_NOT_ALLOWED);
		return new ResponseEntity<>(errorDTO, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<ErrorDTO> httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException ex) {
		String message = "Unsupported media type";
		ErrorDTO errorDTO = buildErrorDTO(message, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		return new ResponseEntity<>(errorDTO, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(UnexpectedTypeException.class)
	public ResponseEntity<ErrorDTO> handleUnexpectedTypeException(UnexpectedTypeException ex) {
		String message = "Check the JSON Body being passed in the request";
		ErrorDTO errorDTO = buildErrorDTO(message, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorDTO> handleMissingServletRequestParameterException(
			MissingServletRequestParameterException ex) {
		String message = "Required parameter '" + ex.getParameterName() + "' is missing.";
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		ErrorDTO errorDTO = buildErrorDTO(message, statusCode);
		return new ResponseEntity<>(errorDTO, statusCode);
	}
}
