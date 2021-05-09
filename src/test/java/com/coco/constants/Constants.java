package com.coco.constants;

/*
 * Class contains all the constants 
 */
public class Constants {

	/*
	 * Enum will contains all the expected status codes
	 * so we don't hard code in the tests at the time of verification
	 */
	public enum StatusCode {
		CREATED(201),
		OK(200),
		DELETE_SUCCESSFUL(204);

		private int statusCode;

		StatusCode(int statusCode) {
			this.statusCode = statusCode;
		}

		public int getStatusCode() {
			return statusCode;
		}

	}

}
