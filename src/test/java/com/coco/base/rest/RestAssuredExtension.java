package com.coco.base.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.net.URI;
import java.net.URISyntaxException;

import com.coco.constants.ApiEndPoints;

/*
 * The Class acts as a Wrapper to RestAssured to call various HTTP methods
 * like GET, PUT, POST, DELETE
 */
public class RestAssuredExtension {
	private static RequestSpecification Request;
	private Headers headers = null;

	/*
	 * RestAssuredExtension contructor used to initialize rest assured and Base URL
	 */
	public RestAssuredExtension() {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri(ApiEndPoints.BASE_URL);
		builder.setContentType(ContentType.JSON);
		RequestSpecification requestSpec = builder.build();
		Request = RestAssured.given().spec(requestSpec);
	}

	/*
	 * Method to call HTTP GET method
	 */
	public ResponseOptions<Response> submitGet(String url) {
		try {
			return Request.get(new URI(url));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Method to call HTTP POST method
	 */
	public ResponseOptions<Response> submitPost(String url, Object object) {
		try {
			// The bearer token is generated already we can generate it runtime through API if available
			final Header authorization = new Header("Authorization", "Bearer 29f7c99824130fee24c9f7077e15bef46084ea9c65536d6548f99efad7092041");
			final Header contentType = new Header("Content-Type", "application/json");
			final Header accept = new Header("Accept","application/json");
			if(headers==null) {
				headers = new Headers(authorization,contentType,accept);
				return Request.headers(headers).body(object).post(new URI(url));
			}
			
			return Request.log().all().body(object).post(new URI(url));
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Method to call HTTP PUT method
	 */
	public ResponseOptions<Response> submitPut(String url, Object object) {
		try {
			// The bearer token is generated already we can generate it runtime through API if available
			final Header authorization = new Header("Authorization", "Bearer 29f7c99824130fee24c9f7077e15bef46084ea9c65536d6548f99efad7092041");
			final Header contentType = new Header("Content-Type", "application/json");
			final Header accept = new Header("Accept","application/json");
			if(headers==null) {
				headers = new Headers(authorization,contentType,accept);
				return Request.headers(headers).body(object).put(new URI(url));
			}
			
			return Request.log().all().body(object).put(new URI(url));
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Method to call HTTP DELETE method
	 */
	public ResponseOptions<Response> delete(String url) {
		try {
			// The bearer token is generated already we can generate it runtime through API if available
			final Header authorization = new Header("Authorization", "Bearer 29f7c99824130fee24c9f7077e15bef46084ea9c65536d6548f99efad7092041");
			final Header contentType = new Header("Content-Type", "application/json");
			final Header accept = new Header("Accept","application/json");
			if(headers==null) {
				headers = new Headers(authorization,contentType,accept);
				return Request.headers(headers).delete(new URI(url));
			}
			
			return Request.log().all().delete(new URI(url));
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

}
