package javaSubfolder;

import org.json.JSONObject;

public class RequestBuilder {
	
	public static String methodToBuildJsonFromPOJO(booksPOJO booksPOJOObject)
	{
		JSONObject jsonObj = new JSONObject( booksPOJOObject );
        System.out.println("**Printing JSON string that is built from POJO**\n" +jsonObj );
        return jsonObj.toString();
	}

}
