package com.coco.test;

import org.testng.annotations.Test;

import com.coco.base.BaseTest;
import com.coco.helper.GoRestApiHelper;

public class GoRestApiTest extends BaseTest{
	
	@Test(dataProvider="csvData")
	public void createUser(String name,String gender,String email,String status, String newName, String postTitle, String postBody, String commentName, String commentEmail, String commentBody) {
		GoRestApiHelper.createUser(restAssuredExtension, name, gender, email, status);
		GoRestApiHelper.verifyUser(restAssuredExtension);
		GoRestApiHelper.updateUser(restAssuredExtension, newName,gender, email, status);
		GoRestApiHelper.createPost(restAssuredExtension, postTitle, postBody);
		GoRestApiHelper.createCommentOnPost(restAssuredExtension, commentName, commentEmail, commentBody);
		GoRestApiHelper.verifyCommentIsLinkedWithCorrectUser(restAssuredExtension);
		GoRestApiHelper.deleteUser(restAssuredExtension);
		GoRestApiHelper.verifyUserDeleted(restAssuredExtension);
	}
	

}
