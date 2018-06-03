package com.studentapp.junit.studentsinfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest {
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI="http://localhost:8085/student";
	}
	@Test
	public void getAllStudents() {
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.statusCode(200);
	}
	@Pending
	@Test
	public void thisIsAPendingTest(){
		
	}
	
	@Ignore
	@Test
	public void thisIsASkippedTest(){
		
	}
	@Manual
	@Test
	public void thisIsAManualTest() {
	
	}
	
	@Title("This is a failure case")
	@Test
	public void test01(){
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.statusCode(500);
	}
	
	@Title("Error: division by Zero ")
	@Test
	public void thisIsAtestWithError(){
		System.out.println("This is an error"+(5/0));
	}
	
	
	@Title("FileNotFoundException captured as compramised in serenity properties")
	@Test
	public void fileDoesNotExist() throws FileNotFoundException{
		File file = new File("E://file.txt");
		@SuppressWarnings({ "unused", "resource" })
		FileReader fr = new FileReader(file);
	}

}
