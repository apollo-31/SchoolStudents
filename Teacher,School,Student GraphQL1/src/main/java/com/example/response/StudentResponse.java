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
	
	private String Firstname;

	private String Lastname;
	
	private List<SubjectResponse> subjectList;
	
	public StudentResponse (Student student) {
		this.id = student.getId();
		this.Firstname = student.getFirstname();
		this.Lastname = student.getLastname();
		
		if (student.getSubjectList() != null){
			subjectList = new ArrayList<SubjectResponse>();
			for (Subject subject: student.getSubjectList()) {
				subjectList.add(new SubjectResponse(subject));
			} 
	}
}
}
