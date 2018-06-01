package com.studentapp.junit.studentsinfo;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.model.StudentClass;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;

import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;


@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class StudentsCRUDTest extends TestBase{
	static String firstName = "SMOKEUSER"+TestUtils.getRandomValue();
	static String lastName = "SMOKEUSER"+TestUtils.getRandomValue();
	static String programme = "ComputerScience";
	static String email = TestUtils.getRandomValue()+"xyz@gmail.com"; 
	static int studentId;
	
	@Test
	@Title("Creation of a new Student")
	public void test001(){
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
		SerenityRest.rest()
		.given().contentType(ContentType.JSON).log().all()
		.when().body(student).post()
		.then().log().all().statusCode(201);
		
	}
	@Test
	@Title("Retriving the student created in the previous test")
	public void test002() {
		String p1 = "findAll{it.firstName=='";
		String p2="'}.get(0)";
		
		HashMap<String, Object> value = SerenityRest.rest()
		.given()
		.when().get("/list")
		.then().statusCode(200).extract().path(p1+firstName+p2);
		
		assertThat(value, hasValue(firstName));
		studentId = (int) value.get("id");
		
	}
	
	@Test
	@Title("Updating and validating the Student created in the previous test")
	public void test003(){
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		
		String p1 = "findAll{it.firstName=='";
		String p2="'}.get(0)";
		
		firstName = firstName+"_Updated";
		
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
		SerenityRest.rest()
		.given().contentType(ContentType.JSON).log().all()
		.when().body(student).put("/"+studentId)
		.then()	.log().all();
		
		HashMap<String, Object> value = SerenityRest.rest()
				.given()
				.when().get("/list")
				.then().statusCode(200).extract().path(p1+firstName+p2);
			
				assertThat(value, hasValue(firstName));
				studentId = (int) value.get("id");
	}
	
	@Test
	@Title("Deleting and validating the student created in the previous test")
	public void test004() {
		SerenityRest.rest().given()
		.when()
		.delete("/"+studentId);
		
		SerenityRest.rest()
		.given()
		.when().get("/"+studentId)
		.then().log().all().statusCode(404);
		
		
	}
	
}
