package com.student.controller;

import java.util.Optional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.StudentEntity;
import com.student.repository.StudentRepository;

public class StudentValidation {

	@RestController
	@RequestMapping("/students")
	@Validated
	public class StudentController {

	    @Autowired
	    private StudentRepository studentRepository;

	    @PutMapping("/{id}/marks")
	    public ResponseEntity<?> updateStudentMarks(
	            @PathVariable("id") Long id,
	            @NotNull(message = "Marks1 is mandatory") @Min(value = 0, message = "Marks1 should be greater than or equal to 0") @RequestParam("marks1") Integer marks1,
	            @NotNull(message = "Marks2 is mandatory") @Min(value = 0, message = "Marks2 should be greater than or equal to 0") @RequestParam("marks2") Integer marks2,
	            @NotNull(message = "Marks3 is mandatory") @Min(value = 0, message = "Marks3 should be greater than or equal to 0") @RequestParam("marks3") Integer marks3
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

}
