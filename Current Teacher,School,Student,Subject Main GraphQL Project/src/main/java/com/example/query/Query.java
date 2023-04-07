package com.example.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.request.SampleRequest;
import com.example.response.SchoolResponse;
import com.example.response.StudentResponse;
import com.example.response.TeacherResponse;
import com.example.service.TeacherService;
import com.example.service.SchoolService;
import com.example.service.StudentService;

@Component
public class Query implements GraphQLQueryResolver {
	
	@Autowired
	TeacherService teacherService;
	@Autowired
	StudentService studentService;
	@Autowired
	SchoolService schoolService;

	public String firstQuery () {
		return "First Query";
	}
	
	public String secondQuery () {
		return "Second Query";
	}
	
	public String fullName (SampleRequest sampleRequest) {
		return sampleRequest.getFirstName() + " " + sampleRequest.getLastName();
	}
	
	public TeacherResponse getTeacher (long id) {
		return new TeacherResponse(teacherService.getTeacherById(id));
	}
	
	public StudentResponse getStudent (long id) {
		return new StudentResponse(studentService.getStudentById(id));
	}
	
	public SchoolResponse getSchool (long id) {
		return new SchoolResponse(schoolService.getSchoolById(id));
	}
}
