package com.harshitbhardwaj.student_rest_api;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.harshitbhardwaj.student_rest_api.entities.Student;
import com.harshitbhardwaj.student_rest_api.enums.Gender;
import com.harshitbhardwaj.student_rest_api.repositories.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class StudentRestApiApplication implements CommandLineRunner {

	private final StudentRepository studentRepository;

	public StudentRestApiApplication(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(StudentRestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (studentRepository.count() == 0) {

			log.info("######## Adding initial student data ########");

			List<Student> initialStudents = List.of(
					Student.builder().name("John Doe").email("john.doe@example.com").age(20).gender(Gender.MALE)
							.build(),

					Student.builder().name("Jane Smith").email("jane.smith@example.com").age(22).gender(Gender.FEMALE)
							.build(),

					Student.builder().name("Alex Johnson").email("alex.johnson@example.com").age(18)
							.gender(Gender.OTHER).build(),

					Student.builder().name("Emily Davis").email("emily.davis@example.com").age(25).gender(Gender.FEMALE)
							.build(),

					Student.builder().name("Michael Brown").email("michael.brown@example.com").age(30)
							.gender(Gender.MALE).build());

			studentRepository.saveAll(initialStudents);
		}
	}

}
