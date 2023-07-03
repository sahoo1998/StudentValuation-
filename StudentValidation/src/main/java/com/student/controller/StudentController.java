package com.student.controller;



	import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Optional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.StudentEntity;
import com.student.repository.StudentRepository;

	@RestController
	@RequestMapping("/students")
	@Validated
	public class StudentController {

	    @Autowired
	    private StudentRepository studentRepository;

	    @PostMapping
	    public ResponseEntity<?> createStudent(@Validated @RequestBody StudentEntity studentRequest) {
	        // Perform validation on the student request object

	        // Minimum length for first and last name is 3 characters
	        if (studentRequest.getFirstName().length() < 3 || studentRequest.getLastName().length() < 3) {
	            return ResponseEntity.badRequest().body("Minimum length for first and last name is 3 characters");
	        }

	        // DOB is mandatory. Age should be greater than 15 years and less than or equal to 20 years.
	        LocalDate currentDate = LocalDate.now();
	        LocalDate dob = studentRequest.getDob();
	        int age = Period.between(dob, currentDate).getYears();
	        if (dob == null || age <= 15 || age > 20) {
	            return ResponseEntity.badRequest().body("DOB is mandatory. Age should be greater than 15 years and less than or equal to 20 years");
	        }

	        // Valid values for section are A, B, and C
	        String section = studentRequest.getSection();
	        if (!Arrays.asList("A", "B", "C").contains(section)) {
	            return ResponseEntity.badRequest().body("Valid values for section are A, B, and C");
	        }

	        // Valid values for gender are M or F
	        String gender = studentRequest.getGender();
	        if (!Arrays.asList("M", "F").contains(gender)) {
	            return ResponseEntity.badRequest().body("Valid values for gender are M or F");
	        }

	        // Marks range is 0 to 100 (Not mandatory)
	        Integer marks1 = studentRequest.getMarks1();
	        Integer marks2 = studentRequest.getMarks2();
	        Integer marks3 = studentRequest.getMarks3();
	        if (marks1 != null && (marks1 < 0 || marks1 > 100) ||
	                marks2 != null && (marks2 < 0 || marks2 > 100) ||
	                marks3 != null && (marks3 < 0 || marks3 > 100)) {
	            return ResponseEntity.badRequest().body("Marks should be in the range of 0 to 100");
	        }

	        // Calculate Total, Average, and Result
	        int total = (marks1 != null ? marks1 : 0) + (marks2 != null ? marks2 : 0) + (marks3 != null ? marks3 : 0);
	        double average = total / 3.0;
	        String result = (marks1 != null && marks2 != null && marks3 != null && marks1 >= 35 && marks2 >= 35 && marks3 >= 35) ? "Pass" : "Fail";

	        // Create a new Student entity
	        StudentEntity student = new StudentEntity();
	        student.setFirstName(studentRequest.getFirstName());
	        student.setLastName(studentRequest.getLastName());
	        student.setDob(studentRequest.getDob());
	        student.setSection(studentRequest.getSection());
	        student.setGender(studentRequest.getGender());
	        student.setMarks1(marks1);
	        student.setMarks2(marks2);
	        student.setMarks3(marks3);
	        student.setTotal(total);
	        student.setAverage(average);
	        student.setResult(result);

	        // Save the student in the database
	        studentRepository.save(student);

	        return ResponseEntity.ok("Student created successfully");
	    }


	    @PutMapping("/{id}/marks")
	    public ResponseEntity<?> updateStudentMarks(
	            @PathVariable("id") Long id,
	            @NotNull(message = "Marks1 is mandatory") 
	            @Min(value = 0, message = "Marks1 should be greater than or equal to 0") @RequestParam("marks1") Integer marks1,
	            @NotNull(message = "Marks2 is mandatory")
	            @Min(value = 0, message = "Marks2 should be greater than or equal to 0") @RequestParam("marks2") Integer marks2,
	            @NotNull(message = "Marks3 is mandatory") 
	            @Min(value = 0, message = "Marks3 should be greater than or equal to 0") @RequestParam("marks3") Integer marks3
	    ) {
	        // Find the student by id
	        Optional<StudentEntity> optionalStudent = studentRepository.findById(id);
	        if (optionalStudent.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        }

	        StudentEntity student = optionalStudent.get();

	        // Validate the marks
	        if (marks1 < 0 || marks1 > 100 ||
	                marks2 < 0 || marks2 > 100 ||
	                marks3 < 0 || marks3 > 100) {
	            return ResponseEntity.badRequest().body("Marks should be in the range of 0 to 100");
	        }

	        // Update the marks
	        student.setMarks1(marks1);
	        student.setMarks2(marks2);
	        student.setMarks3(marks3);

	        // Recalculate Total, Average, and Result
	        int total = marks1 + marks2 + marks3;
	        double average = total / 3.0;
	        String result = (marks1 >= 35 && marks2 >= 35 && marks3 >= 35) ? "Pass" : "Fail";

	        student.setTotal(total);
	        student.setAverage(average);
	        student.setResult(result);

	        // Save the updated student in the database
	        studentRepository.save(student);

	        return ResponseEntity.ok("Student marks updated successfully");
	    }
	}
