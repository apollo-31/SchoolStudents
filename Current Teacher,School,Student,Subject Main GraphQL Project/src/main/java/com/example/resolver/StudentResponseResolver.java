package com.example.resolver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.entity.Subject;
import com.example.enums.SubjectNameFilter;
import com.example.response.StudentResponse;
import com.example.response.SubjectResponse;

@Service
public class StudentResponseResolver implements GraphQLResolver<StudentResponse> {

	public List<SubjectResponse> getLearningSubjects (StudentResponse studentResponse,
			SubjectNameFilter subjectNameFilter) {
		
		List<SubjectResponse> subjectList = new ArrayList<SubjectResponse>();
		
		if (studentResponse.getStudent().getSubjectList() != null) {
			for (Subject subject: studentResponse.getStudent().getSubjectList()) {
				if (subjectNameFilter.name().equalsIgnoreCase("ALL")) {
					subjectList.add(new SubjectResponse(subject));
				} else if (subjectNameFilter.name().equalsIgnoreCase(subject.getSubjectName())) {
					subjectList.add(new SubjectResponse(subject));
				}
			}
		}
		
		return subjectList;
		
	}
	
	public String getFullName (StudentResponse studentResponse) {
		return studentResponse.getFirstName() + " " + studentResponse.getLastName();
	}
}
