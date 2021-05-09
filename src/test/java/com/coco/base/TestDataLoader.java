package com.coco.base;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/*
 * The class helps to read data from csv files and return an array of objects to be
 * used by TestNG data provider.
 */
public class TestDataLoader {

	private String filePath = null;

	/*
	 * TestDataLoader class constructor
	 */
	public TestDataLoader(String file) {
		this.filePath = System.getProperty("user.dir") + "//" + "testData" + "//" + file;
	}

	/*
	 * The method reads the csv file and return array of objects to be used by testNG data provider 
	 */
	public Object[][] getTestDataMatrix() {
		String strLine, temp[] = null, splitBy = ",";
		int lineCount = 0, count = 1, i = 0;
		Object[][] objectArray = { {} };
		try {
			FileInputStream fstream = new FileInputStream(filePath);
			FileInputStream fstream1 = new FileInputStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			BufferedReader br1 = new BufferedReader(new InputStreamReader(fstream1));
			while ((strLine = br.readLine()) != null) {
				lineCount++;
				temp = strLine.split(splitBy, -1);
			}
			objectArray = new Object[lineCount - 1][temp.length];
			while ((strLine = br1.readLine()) != null) {
				if (count != 1) {
					temp = strLine.split(splitBy);
					for (int j = 0; j < temp.length; j++) {
						objectArray[i][j] = temp[j];
					}
					i++;
				}
				count++;
			}
			br.close();
			br1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectArray;
	}
}
