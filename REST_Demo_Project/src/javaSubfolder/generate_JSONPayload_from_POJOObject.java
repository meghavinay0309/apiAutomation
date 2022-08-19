package javaSubfolder;

import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; 

public class generate_JSONPayload_from_POJOObject {
	
    //This method demonstrates how to form Json object from the POJO class object(employeeObject)
	@Test
	public void createEmployeeJSONFromEmployeePOJOClass() throws JsonProcessingException
	{
		// Just create an object of Pojo class
				Employee_POJO employeeObject = new Employee_POJO();
				// Set value as you wish
				employeeObject.setFirstName("Vijay");
				employeeObject.setLastName("Mahajan");
				employeeObject.setAge(29);
				employeeObject.setGender("Male");
				employeeObject.setSalary(3434343);
				employeeObject.setMarried(false);
				
				// Converting a Java class object to a JSON payload as string
				ObjectMapper objectMapper = new ObjectMapper();
				String employeeJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employeeObject);
				System.out.println(employeeJson);
	}
	
	
	//This method demonstrates how to populate POJO class object using Json object(employeeJson)
	@Test
	public void getPojoFromEmployeeObject() throws JsonProcessingException
	{
		// Just create an object of Pojo class
		Employee_POJO employee = new Employee_POJO();
		// Set value as you wish
		employee.setFirstName("Raj");
		employee.setLastName("Mahajan");
		employee.setAge(29);
		employee.setGender("Male");
		employee.setSalary(3434343);
		employee.setMarried(false);
		
		// Converting a Java class object to a JSON payload as string
		ObjectMapper objectMapper = new ObjectMapper();
		String employeeJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee);
		
		
		// Converting EMployee json string to Employee class object
		System.out.println("***********Second Test**************");
		Employee_POJO employee2 = objectMapper.readValue(employeeJson, Employee_POJO.class);
		System.out.println("First Name of employee : "+employee2.getFirstName());
		System.out.println("Last Name of employee : "+employee2.getLastName());
		System.out.println("Age of employee : "+employee2.getAge());
		System.out.println("Gender of employee : "+employee2.getGender());
		System.out.println("Salary of employee : "+employee2.getSalary());
		System.out.println("Marital status of employee : "+employee2.getMarried());
	}

}
