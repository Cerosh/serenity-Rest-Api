package com.studentapp.junit.studentsinfo;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.studentapp.testbase.TestBase;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;

@RunWith(SerenityRunner.class)
public class TagsTest extends TestBase{
	
	@WithTag("studentfeature:NEGATIVE")
	@Title("Validate the 405 status code when incorrect HTTP method is used to access the resource")
	@Test
	public void invalidMethod() {
		SerenityRest.rest()
		.given()
		.when().post("/list")
		.then().statusCode(405).log().all();
	
	}
	
	@WithTags(
			{
				@WithTag("studentfeature:SMOKE"),
				@WithTag("studentfeature:POSITIVE")
			}
			)
	@Title("Validate the 200 status code when correct method is used to acces the correct resource")
	@Test
	public void verfiyRequest200() {
		SerenityRest.rest().
		given().
		when().get("/list").
		then().statusCode(200).log().all();
		
	}
	
	@WithTags(
			{
				@WithTag("studentfeature:SMOKE")
				
			}
			)	
	@Title("Validate the 400 status code when incorrect resource is accessed")
	@Test
	public void invalidResource() {
		SerenityRest.rest()
		.given()
		.when().get("/listasfk")
		.then().statusCode(400).log().all();
	}
}
