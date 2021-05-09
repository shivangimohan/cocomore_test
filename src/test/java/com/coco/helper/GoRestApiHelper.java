package com.coco.helper;

import org.testng.Assert;

import com.coco.base.rest.RestAssuredExtension;
import com.coco.constants.ApiEndPoints;
import com.coco.constants.Constants;
import com.coco.requestVo.CreateUserVo;
import com.coco.responseVo.CreateUserSuccessVo;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

public class GoRestApiHelper {

	private static final String CREATE_USER = ApiEndPoints.CREATE_USER;
	private static String GET_USER = ApiEndPoints.GET_USER;
	private static String UPDATE_USER = ApiEndPoints.UPDATE_USER;
	private static String DELETE_USER = ApiEndPoints.DELETE_USER;
	
	private static CreateUserSuccessVo createUserSuccessVo = null;
	private static CreateUserSuccessVo verifyCreateUserSuccessVo = null;
	private static CreateUserSuccessVo verifyUpdateUserSuccessVo = null;
	private static CreateUserSuccessVo verifyDeleteUserSuccessVo = null;

	public static void createUser(RestAssuredExtension restAssuredExtension, String name, String gender,
			String email, String status) {

		CreateUserVo createUserVo = new CreateUserVo();
		createUserVo.setName(name);
		createUserVo.setGender(gender);
		createUserVo.setEmail(email);
		createUserVo.setStatus(status);
		ResponseOptions<Response> responseHolder = restAssuredExtension.submitPost(CREATE_USER, createUserVo);
		Assert.assertEquals(responseHolder.getStatusCode(), Constants.StatusCode.OK.getStatusCode(),
				"Call to " + CREATE_USER + " API failed.");
		createUserSuccessVo = responseHolder.getBody().as(CreateUserSuccessVo.class);
		Assert.assertTrue(createUserSuccessVo.getCode().equals(Constants.StatusCode.CREATED.getStatusCode()));

	}
	
	public static void verifyUser(RestAssuredExtension restAssuredExtension) {

		GET_USER = GET_USER.replace("{ID}", createUserSuccessVo.getData().getId().toString());
		ResponseOptions<Response> responseHolder = restAssuredExtension.submitGet(GET_USER);
		Assert.assertEquals(responseHolder.getStatusCode(), Constants.StatusCode.OK.getStatusCode(),
				"Call to " + GET_USER + " API failed.");
		verifyCreateUserSuccessVo = responseHolder.getBody().as(CreateUserSuccessVo.class);
		Assert.assertTrue(verifyCreateUserSuccessVo.getCode().equals(Constants.StatusCode.OK.getStatusCode()));
		Assert.assertTrue(verifyCreateUserSuccessVo.getData().getId().equals(createUserSuccessVo.getData().getId()));

	}
	
	public static void updateUser(RestAssuredExtension restAssuredExtension, String newname,String gender,
			String email, String status) {

		UPDATE_USER = UPDATE_USER.replace("{ID}", createUserSuccessVo.getData().getId().toString());
		CreateUserVo updateUserVo = new CreateUserVo();
		updateUserVo.setName(newname);
		updateUserVo.setGender(gender);
		updateUserVo.setEmail(email);
		updateUserVo.setStatus(status);
		
		ResponseOptions<Response> responseHolder = restAssuredExtension.submitPut(UPDATE_USER, updateUserVo);
		Assert.assertEquals(responseHolder.getStatusCode(), Constants.StatusCode.OK.getStatusCode(),
				"Call to " + UPDATE_USER + " API failed.");
		verifyUpdateUserSuccessVo = responseHolder.getBody().as(CreateUserSuccessVo.class);
		Assert.assertTrue(verifyUpdateUserSuccessVo.getCode().equals(Constants.StatusCode.OK.getStatusCode()));
		Assert.assertTrue(verifyUpdateUserSuccessVo.getData().getName().equals(newname));

	}
	
	public static void deleteUser(RestAssuredExtension restAssuredExtension) {

		DELETE_USER = DELETE_USER.replace("{ID}", createUserSuccessVo.getData().getId().toString());
		ResponseOptions<Response> responseHolder = restAssuredExtension.delete(DELETE_USER);
		Assert.assertEquals(responseHolder.getStatusCode(), Constants.StatusCode.OK.getStatusCode(),
				"Call to " + DELETE_USER + " API failed.");
		verifyDeleteUserSuccessVo = responseHolder.getBody().as(CreateUserSuccessVo.class);
		Assert.assertTrue(verifyDeleteUserSuccessVo.getCode().equals(Constants.StatusCode.DELETE_SUCCESSFUL.getStatusCode()));
	}
	

}
