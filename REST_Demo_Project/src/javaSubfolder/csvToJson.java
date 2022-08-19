package javaSubfolder;
import java.io.*;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.csv.*;

public class csvToJson {
	
	public static List<Map<?, ?>> methodToConvertCSVToJSON(String fileName)
	{
		List<Map<?, ?>> list=new ArrayList<>();
		File input = new File("+fileNAme+");
		// System.out.println(new File(".").getAbsolutePath()); //This line will tell you what the current directory is:
		try {
			CsvSchema csv = CsvSchema.emptySchema().withHeader();
			CsvMapper csvMapper = new CsvMapper();
			MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader().forType(Map.class).with(csv)
					.readValues(input);
			//List<Map<?, ?>> list = mappingIterator.readAll();
			list=mappingIterator.readAll();
			System.out.println(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	//for testing purpose only
	public static void dummyMethod(String fileName)
	{
		File input = new File(fileName);
		// System.out.println(new File(".").getAbsolutePath()); //This line will tell you what the current directory is:
		try {
			CsvSchema csv = CsvSchema.emptySchema().withHeader();
			CsvMapper csvMapper = new CsvMapper();
			MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader().forType(Map.class).with(csv)
					.readValues(input);
			//List<Map<?, ?>> list = mappingIterator.readAll();
			List<Map<?, ?>> list = mappingIterator.readAll();
			
			System.out.println(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String convertListOfMapToJsonString(List<Map<?, ?>> myList)
	{
		List<JSONObject> jsonObj = new ArrayList<JSONObject>();

		for(Map<?, ?> data : myList) {
		    JSONObject obj = new JSONObject(data);
		    jsonObj.add(obj);
		}

		JSONArray test = new JSONArray(jsonObj);

		System.out.println(test.toString());
		return test.toString();
	}
	{
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName="books.txt";
		dummyMethod(fileName);
		
	}
}
