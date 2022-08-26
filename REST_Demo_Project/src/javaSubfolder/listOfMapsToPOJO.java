package javaSubfolder;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class listOfMapsToPOJO {
	
	public static booksPOJO methodToConvertListOfMapsToPOJO(List<Map<?, ?>> listOfMap)
	{
		System.out.println("**********Inside POJO class creation method");
		System.out.println(listOfMap);
		booksPOJO booksPOJOObject=new booksPOJO();
		for (Map<?, ?> map : listOfMap) {
		    for (Entry<?, ?> entry : map.entrySet()) {
		    //	 = (String) entry.getKey();
		      // Object value = entry.getValue();
		    	//System.out.println(entry.getKey() + " - " + entry.getValue());
		    	String s=(String) entry.getKey();
		    	//System.out.println("Value of s\n" + s);
		    	switch(s)
		    	{
		    	case "userId":  
		    		booksPOJOObject.setUserID(entry.getValue().toString());
		    		break;
		    	case "it":
		    		booksPOJOObject.setIt(entry.getValue());
		    		break;
		    	case "title":
		    		booksPOJOObject.setTitle((String) entry.getValue());
		    		break;
		    	case "body":
		    		booksPOJOObject.setBody((String) entry.getValue());
		    		break;
		    	}
		    }
		}
		return booksPOJOObject;
	}

}
