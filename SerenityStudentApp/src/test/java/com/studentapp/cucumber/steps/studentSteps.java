package com.studentapp.cucumber.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.studentapp.utils.TestUtils;
import com.studentapp.cucumber.serenity.StudentStatusSteps;


import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class studentSteps {
static String email=null;
	
	@Steps
	StudentStatusSteps steps;
	
	 @When("^the user send a get request to the end point then must get back a valid status code 200$") 
 public void verify_get_request() {
		 SerenityRest.rest()
		 .given()
		 .when().get("/list")
		 .then().statusCode(200);
	
	 }
	 
	 @When("^I create a new student by providing the information firstName (.*) lastName (.*) email (.*) programme (.*) courses (.*)$")
		public void createStudent(String firstName,String lastName,String _email,String programme,String course){
			List<String> courses = new ArrayList<>();
			courses.add(course);
			 email =TestUtils.getRandomValue()+ _email;
			
			 steps.createStudent(firstName, lastName, email, programme, courses)
			 .assertThat()
			 .statusCode(201);
			
		}
		
		@Then("^I verify that the student with (.*) is created$")
		public void verifyStudent(String emailId){
			HashMap<String, Object> actualValue=	steps.getStudentInfoByEmailId(email);
			assertThat(actualValue, hasValue(email));
		}

}
