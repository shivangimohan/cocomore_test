package com.coco.helper;

import org.testng.Assert;

import com.coco.base.rest.RestAssuredExtension;
import com.coco.constants.ApiEndPoints;
import com.coco.constants.Constants;
import com.coco.requestVo.CreateCommentVo;
import com.coco.requestVo.CreatePostVo;
import com.coco.requestVo.CreateUserVo;
import com.coco.responseVo.CreateUserSuccessVo;
import com.coco.responseVo.DeleteSuccessVo;
import com.coco.responseVo.GetUserPostsSuccessVo;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

public class GoRestApiHelper {

	private static final String CREATE_USER = ApiEndPoints.CREATE_USER;
	private static String GET_USER = ApiEndPoints.GET_USER;
	private static String UPDATE_USER = ApiEndPoints.UPDATE_USER;
	private static String CREATE_POST = ApiEndPoints.CREATE_POST;
	private static String CREATE_POST_COMMENT = ApiEndPoints.CREATE_POST_COMMENT;
	private static String GET_USER_POSTS = ApiEndPoints.GET_USER_POSTS;
	private static String DELETE_USER = ApiEndPoints.DELETE_USER;
	
	private static CreateUserSuccessVo createUserSuccessVo = null;
	private static CreateUserSuccessVo verifyCreateUserSuccessVo = null;
	private static CreateUserSuccessVo verifyUpdateUserSuccessVo = null;
	private static CreateUserSuccessVo verifyCreatePostSuccessVo = null;
	private static CreateUserSuccessVo verifyCreatePostCommentSuccessVo = null;
	private static GetUserPostsSuccessVo getUserPostsSuccessVo = null;
	private static CreateUserSuccessVo verifyDeleteUserSuccessVo = null;
	private static DeleteSuccessVo deleteSuccessVo = null;

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
	
	public static void createPost(RestAssuredExtension restAssuredExtension, String title,String body) {

		CREATE_POST = CREATE_POST.replace("{ID}", createUserSuccessVo.getData().getId().toString());
		CreatePostVo createPostVo = new CreatePostVo();
		createPostVo.setTitle(title);
		createPostVo.setBody(body);
		
		ResponseOptions<Response> responseHolder = restAssuredExtension.submitPost(CREATE_POST, createPostVo);
		Assert.assertEquals(responseHolder.getStatusCode(), Constants.StatusCode.OK.getStatusCode(),
				"Call to " + CREATE_POST + " API failed.");
		verifyCreatePostSuccessVo = responseHolder.getBody().as(CreateUserSuccessVo.class);
		Assert.assertTrue(verifyCreatePostSuccessVo.getCode().equals(Constants.StatusCode.CREATED.getStatusCode()));
		Assert.assertTrue(verifyCreatePostSuccessVo.getData().getUserId().equals(createUserSuccessVo.getData().getId()));

	}
	
	public static void createCommentOnPost(RestAssuredExtension restAssuredExtension, String name,String email, String body) {

		CREATE_POST_COMMENT = CREATE_POST_COMMENT.replace("{ID}", verifyCreatePostSuccessVo.getData().getId().toString());
		CreateCommentVo createCommentVo = new CreateCommentVo();
		createCommentVo.setName(name);
		createCommentVo.setEmail(email);
		createCommentVo.setBody(body);
		
		ResponseOptions<Response> responseHolder = restAssuredExtension.submitPost(CREATE_POST_COMMENT, createCommentVo);
		Assert.assertEquals(responseHolder.getStatusCode(), Constants.StatusCode.OK.getStatusCode(),
				"Call to " + CREATE_POST_COMMENT + " API failed.");
		verifyCreatePostCommentSuccessVo = responseHolder.getBody().as(CreateUserSuccessVo.class);
		Assert.assertTrue(verifyCreatePostCommentSuccessVo.getCode().equals(Constants.StatusCode.CREATED.getStatusCode()));
		Assert.assertTrue(verifyCreatePostCommentSuccessVo.getData().getPostId().equals(verifyCreatePostSuccessVo.getData().getId()));

	}
	
	public static void verifyCommentIsLinkedWithCorrectUser(RestAssuredExtension restAssuredExtension) {
		GET_USER_POSTS = GET_USER_POSTS.replace("{ID}", createUserSuccessVo.getData().getId().toString());
		ResponseOptions<Response> responseHolder = restAssuredExtension.submitGet(GET_USER_POSTS);
		Assert.assertEquals(responseHolder.getStatusCode(), Constants.StatusCode.OK.getStatusCode(),
				"Call to " + GET_USER_POSTS + " API failed.");
		
		getUserPostsSuccessVo = responseHolder.getBody().as(GetUserPostsSuccessVo.class);
		Assert.assertTrue(getUserPostsSuccessVo.getCode().equals(Constants.StatusCode.OK.getStatusCode()));
		Assert.assertTrue(getUserPostsSuccessVo.getData().get(0).getId().equals(verifyCreatePostCommentSuccessVo.getData().getId()));
	}
	
	public static void deleteUser(RestAssuredExtension restAssuredExtension) {

		DELETE_USER = DELETE_USER.replace("{ID}", createUserSuccessVo.getData().getId().toString());
		ResponseOptions<Response> responseHolder = restAssuredExtension.delete(DELETE_USER);
		Assert.assertEquals(responseHolder.getStatusCode(), Constants.StatusCode.OK.getStatusCode(),
				"Call to " + DELETE_USER + " API failed.");
		verifyDeleteUserSuccessVo = responseHolder.getBody().as(CreateUserSuccessVo.class);
		Assert.assertTrue(verifyDeleteUserSuccessVo.getCode().equals(Constants.StatusCode.DELETE_SUCCESSFUL.getStatusCode()));
	}
	
	public static void verifyUserDeleted(RestAssuredExtension restAssuredExtension) {
		GET_USER = GET_USER.replace("{ID}", createUserSuccessVo.getData().getId().toString());
		ResponseOptions<Response> responseHolder = restAssuredExtension.submitGet(GET_USER);
		Assert.assertEquals(responseHolder.getStatusCode(), Constants.StatusCode.OK.getStatusCode(),
				"Call to " + GET_USER + " API failed.");
		deleteSuccessVo = responseHolder.getBody().as(DeleteSuccessVo.class);
		Assert.assertTrue(deleteSuccessVo.getCode().equals(Constants.StatusCode.USER_NOT_FOUND.getStatusCode()));
	}
	

}
