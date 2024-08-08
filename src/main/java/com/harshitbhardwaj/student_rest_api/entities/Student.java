package com.harshitbhardwaj.student_rest_api.entities;

import com.harshitbhardwaj.student_rest_api.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	@NotBlank(message = "Student's name cannot be blank")
	@Size(min = 3, max = 32, message = "Student's name must be between 3 and 30 characters long")
	private String name;

	@Column(name = "email", nullable = false)
	@NotBlank(message = "Student's email cannot be blank")
	@Email(message = "Student's email should be valid")
	private String email;

	@Column(name = "age", nullable = false)
	@NotNull(message = "Student's age cannot be null")
	@Min(value = 10, message = "Student's age must be at least 10 years old")
	@Max(value = 40, message = "Student's age must be at most 40 years old")
	private int age;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false)
	@NotNull(message = "Student's gender cannot be null")
	private Gender gender;

}
