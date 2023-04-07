package com.cellers.response;

import com.cellers.model.School;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_EMPTY)
public class TeacherResponse extends QueryResponse{

	private Long id;
	
	private String name;
	
	private String subject;
	
	private School school;
}
