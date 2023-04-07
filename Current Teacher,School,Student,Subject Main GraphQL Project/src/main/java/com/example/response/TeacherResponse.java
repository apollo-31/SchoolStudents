package com.example.response;

import java.util.ArrayList;
import java.util.List;

import com.example.entity.Teacher;
import com.example.entity.Student;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherResponse {

	private long id;

	private String firstName;

	private String lastName;
	
	private List<StudentResponse> studentList;
	
	public TeacherResponse (Teacher teacher) {
		this.id = teacher.getId();
		this.firstName = teacher.getFirstName();
		this.lastName = teacher.getLastName();
		
		if (teacher.getStudentList() != null) {
			studentList = new ArrayList<StudentResponse>();
			for (Student student: teacher.getStudentList()) {
				studentList.add(new StudentResponse(student));
			}
		}
	}

}
