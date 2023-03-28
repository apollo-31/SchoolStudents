package com.infybuzz.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(Include.NON_NULL) //This excludes fields that have null values
public class StudentResponse {

//	private long id;

	private String firstName;

	private String lastName;
	
	private List<SubjectResponse> subjectList;

}
