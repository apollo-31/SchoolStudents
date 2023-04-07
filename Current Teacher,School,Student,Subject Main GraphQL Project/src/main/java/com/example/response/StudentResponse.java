package com.example.response;

import java.util.ArrayList;
import java.util.List;

import com.example.entity.Student;
import com.example.entity.Subject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {

	private long id;
	
	private String firstName;

	private String lastName;
	
	private List<SubjectResponse> subjectList;
	
	private Student student;
	
	private String fullName;
	
	public StudentResponse (Student student) {
		this.student = student;
		this.id = student.getId();
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		
		if (student.getSubjectList() != null){
			subjectList = new ArrayList<SubjectResponse>();
			for (Subject subject: student.getSubjectList()) {
				subjectList.add(new SubjectResponse(subject));
			} 
	}
}
}
