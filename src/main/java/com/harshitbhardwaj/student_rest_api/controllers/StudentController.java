package com.harshitbhardwaj.student_rest_api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harshitbhardwaj.student_rest_api.dtos.StudentRequestDTO;
import com.harshitbhardwaj.student_rest_api.dtos.StudentResponseDTO;
import com.harshitbhardwaj.student_rest_api.dtos.UpdateStudentRequestDTO;
import com.harshitbhardwaj.student_rest_api.services.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Student")
@Slf4j
public class StudentController {
	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@Operation(summary = "Create a new student")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Student created successfully", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = StudentResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Incorrect / incomplete student details supplied", content = @Content) })
	@PostMapping(value = "/students", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody @Valid StudentRequestDTO studentRequestDTO) {
		StudentResponseDTO createdStudent = studentService.createStudent(studentRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
	}

	@Operation(summary = "Get list of all the students")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Students list fetched successfully", content = @Content(mediaType = "application/json")) })
	@GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
		List<StudentResponseDTO> studentsList = studentService.findAllStudents();
		return ResponseEntity.ok(studentsList);
	}

	@Operation(summary = "Get student based on id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student with supplied id found successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StudentResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid student id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Student not found with supplied id", content = @Content) })
	@GetMapping(value = "/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
		StudentResponseDTO studentFoundById = studentService.findStudentById(id);
		return ResponseEntity.ok(studentFoundById);
	}

	@Operation(summary = "Get student based on studentName")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student with supplied name found successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StudentResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Name parameter not supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Student not found with supplied name", content = @Content) })
	@GetMapping(value = "/students/searchByName", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponseDTO> getStudentByName(@RequestParam String name) {
		StudentResponseDTO studentFoundByName = studentService.findStudentByName(name);
		return ResponseEntity.ok(studentFoundByName);
	}

	@Operation(summary = "Get student based on studentEmail")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student with supplied email found successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StudentResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Email parameter not supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Student not found with supplied email", content = @Content) })
	@GetMapping(value = "/students/searchByEmail", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponseDTO> getStudentByEmail(@RequestParam String email) {
		StudentResponseDTO studentFoundByEmail = studentService.findStudentByEmail(email);
		return ResponseEntity.ok(studentFoundByEmail);
	}

	@Operation(summary = "Update a student")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Student updated successfully", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = StudentResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Incorrect / incomplete student details supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Student not found with supplied id", content = @Content) })
	@PutMapping(value = "/students/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id,
			@RequestBody @Valid UpdateStudentRequestDTO updateStudentRequestDTO) {
		StudentResponseDTO updatedStudent = studentService.updateStudent(id, updateStudentRequestDTO);
		return ResponseEntity.ok(updatedStudent);
	}

	@Operation(summary = "Delete student based on id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Student with supplied id deleted successfully", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid student id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Student not found with supplied id", content = @Content) })
	@DeleteMapping(value = "/students/{id}")
	public ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return ResponseEntity.noContent().build();
	}
}
