package com.harshitbhardwaj.student_rest_api.dtos;

import com.harshitbhardwaj.student_rest_api.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StudentResponseDTO {

	private Long id;
	private String name;
	private String email;
	private int age;
	private Gender gender;

}