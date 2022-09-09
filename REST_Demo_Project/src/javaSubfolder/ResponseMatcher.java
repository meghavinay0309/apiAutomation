package javaSubfolder;

import io.restassured.path.json.JsonPath;

public class ResponseMatcher {
	
	public static String verifyJSONParameter(JsonPath JSONObject, String expectedValue)
	{
		String nameParameter=JSONObject.getString("name");
		System.out.println("\n**************** Returned param"+nameParameter);
		if(JSONObject.getString("name").equals(expectedValue))
		{
			return "Response contains expected JSON tag: "+expectedValue;
		}
		else
			return "Response doesn't contain expected JSON tag: "+expectedValue;
	}

}
