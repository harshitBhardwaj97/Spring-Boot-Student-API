package com.harshitbhardwaj.student_rest_api.dtos;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDTO {

	private String message;
	private boolean successStatus;
	private HttpStatus httpStatus;

}
