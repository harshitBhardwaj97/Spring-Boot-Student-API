package com.harshitbhardwaj.student_rest_api.services;

import java.util.List;

import com.harshitbhardwaj.student_rest_api.dtos.StudentRequestDTO;
import com.harshitbhardwaj.student_rest_api.dtos.StudentResponseDTO;
import com.harshitbhardwaj.student_rest_api.dtos.UpdateStudentRequestDTO;

public interface StudentService {

	List<StudentResponseDTO> findAllStudents();

	StudentResponseDTO findStudentById(Long studentId);

	StudentResponseDTO findStudentByName(String name);

	StudentResponseDTO findStudentByEmail(String email);

	StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO);

	void deleteStudentById(Long studentId);

	StudentResponseDTO updateStudent(Long studentId, UpdateStudentRequestDTO updateStudentRequestDTO);

}
