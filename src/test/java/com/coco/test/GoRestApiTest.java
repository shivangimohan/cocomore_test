package com.coco.test;

import org.testng.annotations.Test;

import com.coco.base.BaseTest;
import com.coco.helper.GoRestApiHelper;

public class GoRestApiTest extends BaseTest{
	
	@Test(dataProvider="csvData")
	public void createUser(String name,String gender,String email,String status, String newName) {
		GoRestApiHelper.createUser(restAssuredExtension, name, gender, email, status);
		GoRestApiHelper.verifyUser(restAssuredExtension);
		GoRestApiHelper.updateUser(restAssuredExtension, newName,gender, email, status);
		GoRestApiHelper.deleteUser(restAssuredExtension);
	}
	

}
