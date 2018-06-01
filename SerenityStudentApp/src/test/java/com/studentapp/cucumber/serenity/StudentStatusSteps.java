package com.studentapp.cucumber.serenity;

import java.util.HashMap;
import java.util.List;

import com.studentapp.model.StudentClass;
import com.studentapp.utils.ResuableSpecifications;


import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StudentStatusSteps {
	@Step("Creating student information with firstName: {0}, lastName:{1}, Email:{2},Programme:{3}, Courses:{4}")
	public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, List<String> courses) {
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
		return SerenityRest.rest()
		.given().spec(ResuableSpecifications.getGenericReqSpecification())
		.when().body(student).post()
		.then();		 
	}
	
	@Step("Getting Student information based on first name: {0}")
	public HashMap<String, Object> getStudentInfoByFname(String firstName){
		String p1 = "findAll{it.firstName=='";
		String p2="'}.get(0)";
		
		return  SerenityRest.rest()
		.given()
		.when().get("/list")
		.then().statusCode(200).extract().path(p1+firstName+p2);	
	}
	
	@Step("Updating student information with studentId: {0}, firstName: {1}, lastName:{2}, Email:{3},Programme:{4}, Courses:{5}")
	public ValidatableResponse updateStudent(int studentID, String firstName, String lastName, String email, String programme, List<String> courses) {
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
		return SerenityRest.rest()
		.given().spec(ResuableSpecifications.getGenericReqSpecification())
		.when().body(student).put("/"+studentID)
		.then();		 
	}
	
	@Step("Deleting the student information with studentId:{0}")
	public void deleteStudentInfo(int studentId) {
		SerenityRest.rest().given()
		.when()
		.delete("/"+studentId);
	}
	
	@Step ("validating the student information with studentId:{0}")
	public ValidatableResponse validatingStudentInfo(int studentId) {
		return SerenityRest.rest()
		.given()
		.when().get("/"+studentId)
		.then();
		
	}

}
