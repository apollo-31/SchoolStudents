package com.infybuzz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infybuzz.request.CreateStudentRequest;
import com.infybuzz.response.SchoolResponse;
import com.infybuzz.response.StudentResponse;
import com.infybuzz.response.TeacherResponse;

import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;

@Service
public class ClientService {

    @Autowired
    GraphQLWebClient graphQLWebClient;

//This block of code requires the gson maven dependency from maven repo which will convert Java objects to JSON and vice-versa
    public StudentResponse getStudent(String requestBody) {
        JsonObject jsonObject = JsonParser.parseString(requestBody).getAsJsonObject();
//This line parses the incoming JSON request body into a JsonObject using JsonParser from the Gson library. Line 33

        String queryName = jsonObject.get("queryName").getAsString();
//This line retrieves the value of the "queryName" key from the JSON object and converts it to a String. Line 36
        JsonObject identifierObject = jsonObject.get("identifier").getAsJsonObject();
//This line retrieves the value of the "identifier" key from the JSON object and converts it to a JsonObject. Line 38       
        Integer id = identifierObject.get("id").getAsInt();
//This line retrieves the value of the "id" key from the identifierObject and converts it to an Integer. Line 40

        JsonArray studentInformationArray = jsonObject.get("studentInformation").getAsJsonArray();
//This line retrieves the value of the "studentInformation" key from the JSON object and converts it to a JsonArray. Line 43        
        List<String> studentInformationList = new ArrayList<>();
//This line initializes a new ArrayList that will hold the student information. Line 45        
        for (JsonElement element : studentInformationArray) {
//This line loops through the elements in the studentInformationArray. Line 47-58        	
            if (element.isJsonPrimitive()) {
//This line checks if the current element is a JSON primitive (i.e. a string or number), Line 49           	
                studentInformationList.add(element.getAsString());
//and adds it to the studentInformationList if it is. Line 51                
            } else if (element.isJsonObject()) {
//This line checks if the current element is a JSON object,  Line 53            	
                JsonObject obj = element.getAsJsonObject();
                StringBuilder sb = new StringBuilder();
//and if so, it extracts the keys and values from the object and appends them to a StringBuilder.  Line 55-67 
//Note that sb is where the id, subjectName, and the grades are located in.
                for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
//This line iterates through each key-value pair of the JSON object. Line 58                	
                    sb.append(entry.getKey()).append(" { ");
//This line appends the current key to a StringBuilder, followed by an opening brace, 
//to start constructing a nested field. Line 60                    
                    JsonArray subFieldArray = entry.getValue().getAsJsonArray();
//This line retrieves the value associated with the current key, which should be a JsonArray of subfields. Line 63                   
                    for (JsonElement subFieldElement : subFieldArray) {
//This line iterates through each subfield in the subFieldArray. Line 65                   	
                        sb.append(subFieldElement.getAsString()).append(" ");
//This line appends each subfield to the StringBuilder and adds a space character after it. Line 67                       
                    }
                    sb.append("} ");
//This line appends a closing brace to the StringBuilder to complete the nested field.   Line 70                 
                }
                studentInformationList.add(sb.toString());
//This line adds the completed StringBuilder object to the studentInformationList.  Line 73              
            }
        }

        StringBuilder queryBuilder = new StringBuilder("query { "); 
//This line initializes a StringBuilder object with a query string that starts with "query { ". Line 78
        queryBuilder.append(queryName).append("(id: ").append(id).append(") { ");
//This line appends the query name and ID to the query string, 
//which will allow the GraphQL server to identify the specific query to execute. Line 80
        for (String field : studentInformationList) {
                  queryBuilder.append(field).append(" ");
        } //This line appends the list of fields to retrieve for the query. Line 83-84
        queryBuilder.append("} }"); // This line appends the closing curly brackets to the query string. Line 86
        String queryStr = queryBuilder.toString();
// This line converts the StringBuilder object to a string, which can be used as the GraphQL query. Line 87
        GraphQLRequest request = GraphQLRequest.builder().query(queryStr).build();
//This line creates a GraphQL request object using the query string. Line 89       
        GraphQLResponse graphQLResponse = graphQLWebClient.post(request).block();
//This line sends the GraphQL request to the server using a WebClient and blocks until a response is received. Line 91
        
/* The block() method used in line 71 is used to wait for the response to be received before 
 * continuing with the execution of the code. This is known as a blocking operation, because it will block the thread 
 * until the response is received.  In this case, it is important to block until a response is received, 
 * because the response is needed to populate the GraphQLResponse object in order to retrieve the desired data.      */
        
        return graphQLResponse.get(queryName, StudentResponse.class);
//This line retrieves the response data from the GraphQL response object and returns it as a StudentResponse object. Line 100 
    }
    
    


	
	public TeacherResponse getTeacher (Integer id) {
		String queryStr = "query {\r\n"
				+ "  teacher(id : "+ id +") {\r\n"
				+ "    id\r\n"
				+ "    firstName\r\n"
				+ "    lastName\r\n"
				+ "    studentList {\r\n"
				+ "      id\r\n"
				+ "      firstName\r\n"
				+ "      lastName\r\n"
				+ "    subjectList {\r\n"
				+ "      id\r\n"
				+ "      subjectName\r\n"
				+ "      grades\r\n"
				+ "  } \r\n"
				+ "  } \r\n"
				+ "  } \r\n"
				+ "}";
		
		GraphQLRequest request = GraphQLRequest.builder()
				.query(queryStr).build();
		
		GraphQLResponse graphQLResponse = graphQLWebClient.post(request).block();
		
		return graphQLResponse.get("teacher", TeacherResponse.class);
	}
	
	public StudentResponse createStudent(
			CreateStudentRequest createStudentRequest) {
		
		Map<String, Object> variables = new HashMap<>();
		variables.put("createStudentRequest", createStudentRequest);
		
		String mutationStr = "mutation createStudent ($createStudentRequest : CreateStudentRequest) {\r\n"
				+ "  createStudent (createStudentRequest : $createStudentRequest) {\r\n"
				+ "    id\r\n"
				+ "    firstName\r\n"
				+ "    lastName\r\n"
				+ "    subjectList {\r\n"
				+ "      id\r\n"
				+ "      subjectName\r\n"
				+ "      grades\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}";
		
		GraphQLRequest request = GraphQLRequest.builder()
				.query(mutationStr).variables(variables).build();
		
		GraphQLResponse graphQLResponse = graphQLWebClient.post(request).block();
		
		return graphQLResponse.get("createStudent", StudentResponse.class);
	}
}
