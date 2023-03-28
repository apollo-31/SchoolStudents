package com.infybuzz.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateSchoolRequest {

	private String schoolName;

	private String street;
	
	private String city;

	private List<CreateTeacherRequest> teacherList;
	
	private List<CreateStudentRequest> studentList;

}
