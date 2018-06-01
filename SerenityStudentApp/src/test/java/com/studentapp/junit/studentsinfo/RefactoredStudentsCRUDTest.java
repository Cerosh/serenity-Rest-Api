package com.studentapp.junit.studentsinfo;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.cucumber.serenity.StudentStatusSteps;
import com.studentapp.model.StudentClass;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.ResuableSpecifications;
import com.studentapp.utils.TestUtils;

import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;


@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class RefactoredStudentsCRUDTest extends TestBase{
	static String firstName = "SMOKEUSER"+TestUtils.getRandomValue();
	static String lastName = "SMOKEUSER"+TestUtils.getRandomValue();
	static String programme = "ComputerScience";
	static String email = TestUtils.getRandomValue()+"xyz@gmail.com"; 
	static int studentId;
	
	@Steps
	StudentStatusSteps steps;
	
	@Test
	@Title("Creation of a new Student")
	public void test001(){
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		steps.createStudent(firstName, lastName, email, programme, courses).statusCode(201).spec(ResuableSpecifications.getGenericResSpecification());
		
		
	}
	@Test
	@Title("Retriving the student created in the previous test")
	public void test002() {
		HashMap<String, Object> value = steps.getStudentInfoByFname(firstName);	
		assertThat(value, hasValue(firstName));
		studentId = (int) value.get("id");
		
	}
	
	@Test
	@Title("Updating and validating the Student created in the previous test")
	public void test003(){
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		/*
		String p1 = "findAll{it.firstName=='";
		String p2="'}.get(0)";
		*/
		firstName = firstName+"_Updated";
		
		steps.updateStudent(studentId, firstName, lastName, email, programme, courses);	
		HashMap<String, Object> value =  steps.getStudentInfoByFname(firstName);			
		assertThat(value, hasValue(firstName));
		studentId = (int) value.get("id");
	}
	
	@Test
	@Title("Deleting and validating the student created in the previous test")
	public void test004() {
		steps.deleteStudentInfo(studentId);
		steps.validatingStudentInfo(studentId).statusCode(404);
		
		
	}
	
}
