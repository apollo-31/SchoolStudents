package com.infybuzz.response;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@JsonInclude(Include.NON_NULL) // This excludes fields that have null values
public class SubjectResponse {

	private Long id;
	
	private String subjectName;

	private Float grades;
	
}
