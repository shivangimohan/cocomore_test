package com.coco.base;

import org.testng.annotations.DataProvider;

import com.coco.base.rest.RestAssuredExtension;


/*
 * All test classes inherit Base Test class, The class helps in getting the
 * respective test class name and to read the test data csv files for the respective class
 * to be used in TestNG data provider.
 */
public class BaseTest {
	TestDataLoader testData = null;
	String testCaseName = null;
	protected static RestAssuredExtension restAssuredExtension = new RestAssuredExtension();

	/*
	 * BaseTest class constructor
	 */
	public BaseTest() {
		getTestCaseName();
		testData = new TestDataLoader(testCaseName + ".csv");
	}

	/*
	 * Method helps in getting the test class name
	 */
	public void getTestCaseName() {
		testCaseName = this.getClass().getSimpleName();
	}

	/*
	 * Method returns an array of objects
	 */
	@DataProvider(name = "csvData")
	public Object[][] csvData() {
		return testData.getTestDataMatrix();
	}
}
