package com.studentapp.utils;

import java.util.concurrent.TimeUnit;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;


public class ResuableSpecifications {
	public static RequestSpecBuilder reqSpecBld;
	public static RequestSpecification reqSpec;
	
	public static ResponseSpecBuilder resSpecBld;
	public static ResponseSpecification resSpec;
	
	public static RequestSpecification getGenericReqSpecification() {
		reqSpecBld = new RequestSpecBuilder();
		reqSpecBld.setContentType(ContentType.JSON);
		reqSpec = reqSpecBld.build();
		return reqSpec;
		
	}
	
	public static ResponseSpecification getGenericResSpecification() {
		resSpecBld = new ResponseSpecBuilder();
		resSpecBld.expectHeader("Content-Type", "application/json;charset=UTF-8");
		resSpecBld.expectHeader("Transfer-Encoding", "chunked");
		resSpecBld.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);
		resSpec = resSpecBld.build();
		return resSpec;
	}

}
