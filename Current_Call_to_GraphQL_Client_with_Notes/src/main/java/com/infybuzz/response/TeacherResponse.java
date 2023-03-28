package com.infybuzz.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherResponse {

	private long id;

	private String firstName;

	private String lastName;
	
	private List<StudentResponse> studentList;

}