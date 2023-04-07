package com.example.response;

import java.util.ArrayList;
import java.util.List;


import com.example.entity.School;
import com.example.entity.Student;
import com.example.entity.Teacher;

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
	
	public SchoolResponse (School school) {
		this.id = school.getId();
		this.schoolName = school.getSchoolName();
		this.street = school.getStreet();
		this.city = school.getCity();
		
		if (school.getTeacherList() != null){
			teacherList = new ArrayList<TeacherResponse>();
			for (Teacher teacher: school.getTeacherList()) {
				teacherList.add(new TeacherResponse(teacher));
			} 
		
		
		if (school.getStudentList() != null) {
			studentList = new ArrayList<StudentResponse>();
			for (Student student1: school.getStudentList()) {
				studentList.add(new StudentResponse(student1));
			} 
		}
	}
}
}



