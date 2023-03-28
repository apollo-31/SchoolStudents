package com.infybuzz.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SchoolResponse {

	private long id;

	private String schoolName;

	private String street;
	
	private String city;
	
	private List<TeacherResponse> teacherList;
	
	private List<StudentResponse> studentList;

}