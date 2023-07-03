package com.student.entity;



	import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

	@Entity
	@Table(name = "students")
	public class StudentEntity  {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    
	    @Column(name = "first_name")
	    private String firstName;

	    @Column(name = "last_name")
	    private String lastName;

	    @Column(name = "dob")
	    private LocalDate dob;

	    @Column(name = "section")
	    private String section;

	    @Column(name = "gender")
	    private String gender;

	    @Column(name = "marks1")
	    private Integer marks1;

	    @Column(name = "marks2")
	    private Integer marks2;

	    @Column(name = "marks3")
	    private Integer marks3;

	    @Column(name = "total")
	    private Integer total;

	    @Column(name = "average")
	    private Double average;

	    @Column(name = "result")
	    private String result;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public LocalDate getDob() {
			return dob;
		}

		public void setDob(LocalDate dob) {
			this.dob = dob;
		}

		public String getSection() {
			return section;
		}

		public void setSection(String section) {
			this.section = section;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public Integer getMarks1() {
			return marks1;
		}

		public void setMarks1(Integer marks1) {
			this.marks1 = marks1;
		}

		public Integer getMarks2() {
			return marks2;
		}

		public void setMarks2(Integer marks2) {
			this.marks2 = marks2;
		}

		public Integer getMarks3() {
			return marks3;
		}

		public void setMarks3(Integer marks3) {
			this.marks3 = marks3;
		}

		public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}

		public Double getAverage() {
			return average;
		}

		public void setAverage(Double average) {
			this.average = average;
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

		@Override
		public String toString() {
			return "StudentEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
					+ ", section=" + section + ", gender=" + gender + ", marks1=" + marks1 + ", marks2=" + marks2
					+ ", marks3=" + marks3 + ", total=" + total + ", average=" + average + ", result=" + result
					+ ", getId()=" + getId() + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName()
					+ ", getDob()=" + getDob() + ", getSection()=" + getSection() + ", getGender()=" + getGender()
					+ ", getMarks1()=" + getMarks1() + ", getMarks2()=" + getMarks2() + ", getMarks3()=" + getMarks3()
					+ ", getTotal()=" + getTotal() + ", getAverage()=" + getAverage() + ", getResult()=" + getResult()
					+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
					+ "]";
		}

	   
	    
	    
	}


