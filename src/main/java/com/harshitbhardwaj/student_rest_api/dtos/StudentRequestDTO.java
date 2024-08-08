package com.harshitbhardwaj.student_rest_api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDTO {

	@NotBlank(message = "Student's name cannot be blank")
	@Size(min = 3, max = 32, message = "Student's name must be between 3 and 30 characters long")
	private String name;

	@NotBlank(message = "Student's email cannot be blank")
	@Email(message = "Student's email should be valid")
	private String email;

	@NotNull(message = "Student's age cannot be null")
	@Min(value = 10, message = "Student's age must be at least 10 years old")
	@Max(value = 40, message = "Student's age must be at most 40 years old")
	private Integer age;

	@Pattern(regexp = "MALE|FEMALE|OTHER", message = "Student's gender can only be MALE, FEMALE or OTHER")
	private String gender;

}
