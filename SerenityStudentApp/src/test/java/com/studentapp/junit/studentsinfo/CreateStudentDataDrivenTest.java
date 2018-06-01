package com.studentapp.junit.studentsinfo;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.studentapp.cucumber.serenity.StudentStatusSteps;
import com.studentapp.testbase.TestBase;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
@Concurrent(threads ="4x")
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("testdata/studentinfo.csv")
public class CreateStudentDataDrivenTest extends TestBase{
	private String firstName ;
	private String lastName ;
	private String programme;
	private String email;
	private String course;
	
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

	public String getProgramme() {
		return programme;
	}

	public void setProgramme(String programme) {
		this.programme = programme;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public StudentStatusSteps getSteps() {
		return steps;
	}

	public void setSteps(StudentStatusSteps steps) {
		this.steps = steps;
	}

	@Steps
	StudentStatusSteps steps;

	@Title("DataDriven Test for adding multiple students")
	@Test
	public void createMultipleStudents() {
		ArrayList<String> courses = new ArrayList<>();
		courses.add(course);
		steps.createStudent(firstName, lastName, email, programme, courses)
		.statusCode(201);
	}
}
