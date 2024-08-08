package com.harshitbhardwaj.student_rest_api.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.harshitbhardwaj.student_rest_api.dtos.StudentRequestDTO;
import com.harshitbhardwaj.student_rest_api.dtos.StudentResponseDTO;
import com.harshitbhardwaj.student_rest_api.dtos.UpdateStudentRequestDTO;
import com.harshitbhardwaj.student_rest_api.entities.Student;
import com.harshitbhardwaj.student_rest_api.enums.Gender;
import com.harshitbhardwaj.student_rest_api.exceptions.ApplicationException;
import com.harshitbhardwaj.student_rest_api.repositories.StudentRepository;
import com.harshitbhardwaj.student_rest_api.services.StudentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	private final ModelMapper modelMapper;

	public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
		this.studentRepository = studentRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<StudentResponseDTO> findAllStudents() {
		log.info("######## Attempting to fetch all the students ########");

		List<Student> studentsList = studentRepository.findAll();

		List<StudentResponseDTO> studentsDTOList = studentsList.stream()
				.map(student -> modelMapper.map(student, StudentResponseDTO.class)).toList();

		log.info("######## Students fetched ########");

		return studentsDTOList;
	}

	@Override
	public StudentResponseDTO findStudentById(Long studentId) {
		log.info("######## Attempting to find student with id " + studentId + " ########");

		Student studentFoundById = studentRepository.findById(studentId).orElseThrow(
				() -> new ApplicationException("Student with id " + studentId + " not found", HttpStatus.NOT_FOUND));

		log.info("######## Student with id " + studentId + " found ########");

		StudentResponseDTO foundStudentDTO = modelMapper.map(studentFoundById, StudentResponseDTO.class);

		return foundStudentDTO;
	}

	@Override
	public StudentResponseDTO findStudentByName(String name) {
		log.info("######## Attempting to find student with name " + name + " ########");

		Student studentFoundByName = studentRepository.findByName(name).orElseThrow(
				() -> new ApplicationException("Student with name " + name + " not found", HttpStatus.NOT_FOUND));

		log.info("######## Students student with name " + name + " found ########");

		StudentResponseDTO studentFoundByNameDTO = modelMapper.map(studentFoundByName, StudentResponseDTO.class);

		return studentFoundByNameDTO;
	}

	@Override
	public StudentResponseDTO findStudentByEmail(String email) {
		log.info("######## Attempting to find student with email " + email + " ########");

		Student studentFoundByEmail = studentRepository.findByEmail(email).orElseThrow(
				() -> new ApplicationException("Student with email " + email + " not found", HttpStatus.NOT_FOUND));

		log.info("######## Students student with email " + email + " found ########");

		StudentResponseDTO studentFoundByEmailDTO = modelMapper.map(studentFoundByEmail, StudentResponseDTO.class);

		return studentFoundByEmailDTO;
	}

	@Override
	public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
		log.info("######## Attempting to save student in database ########");

		Student createdStudent = studentRepository.save(modelMapper.map(studentRequestDTO, Student.class));

		log.info("######## Student saved in database ########");

		return modelMapper.map(createdStudent, StudentResponseDTO.class);
	}

	@Override
	public void deleteStudentById(Long studentId) {
		log.info("######## Attempting to delete student with id " + studentId + " ########");

		Student studentToBeDeleted = studentRepository.findById(studentId)
				.orElseThrow(() -> new ApplicationException(
						"Student with id " + studentId + " not found, hence no deletion is possible",
						HttpStatus.NOT_FOUND));

		studentRepository.delete(studentToBeDeleted);

		log.info("######## Student with id " + studentId + " deleted ########");
	}

	@Override
	public StudentResponseDTO updateStudent(Long studentId, UpdateStudentRequestDTO updateStudentRequestDTO) {
		log.info("######## Attempting to update student with id " + studentId + " ########");

		Student studentToBeUpdated = studentRepository.findById(studentId)
				.orElseThrow(() -> new ApplicationException(
						"Student with id " + studentId + " not found, hence no updation is possible",
						HttpStatus.NOT_FOUND));

		boolean noDataSharedForUpdate = (updateStudentRequestDTO.getName() == null
				&& updateStudentRequestDTO.getEmail() == null && updateStudentRequestDTO.getAge() == null
				&& updateStudentRequestDTO.getGender() == null);

		if (noDataSharedForUpdate) {
			log.info("No fields provided for update for student with id " + studentId);
			throw new ApplicationException("No fields provided for update", HttpStatus.BAD_REQUEST);
		}

		if (updateStudentRequestDTO.getName() != null) {
			studentToBeUpdated.setName(updateStudentRequestDTO.getName());
		}
		if (updateStudentRequestDTO.getEmail() != null) {
			studentToBeUpdated.setEmail(updateStudentRequestDTO.getEmail());
		}
		if (updateStudentRequestDTO.getAge() != null) {
			studentToBeUpdated.setAge(updateStudentRequestDTO.getAge());
		}
		if (updateStudentRequestDTO.getGender() != null) {
			studentToBeUpdated.setGender(stringToGender(updateStudentRequestDTO.getGender()));
		}

		// Save the updated student
		Student updatedStudent = studentRepository.save(studentToBeUpdated);
		log.info("######## Student with id " + studentId + " updated ########");

		StudentResponseDTO updateStudentResponseDTO = modelMapper.map(updatedStudent, StudentResponseDTO.class);

		return updateStudentResponseDTO;
	}

	private Gender stringToGender(String gender) {
		switch (gender) {
		case "MALE":
			return Gender.MALE;
		case "FEMALE":
			return Gender.FEMALE;
		case "OTHER":
			return Gender.OTHER;
		default:
			throw new IllegalArgumentException("Invalid gender value: " + gender);
		}
	}

}
