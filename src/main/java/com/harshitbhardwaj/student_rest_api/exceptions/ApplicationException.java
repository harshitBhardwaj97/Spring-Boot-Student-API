package com.harshitbhardwaj.student_rest_api.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final HttpStatus status;

	public ApplicationException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}
}
