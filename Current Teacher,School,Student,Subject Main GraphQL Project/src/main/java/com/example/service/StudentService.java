package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Student;
import com.example.entity.Subject;
import com.example.entity.Teacher;
import com.example.repository.StudentRepository;
import com.example.repository.SubjectRepository;
import com.example.repository.TeacherRepository;
import com.example.request.CreateStudentRequest;
import com.example.request.CreateSubjectRequest;


@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	TeacherRepository teacherRepository;
	
	public Student getStudentById (long id) {
		return studentRepository.findById(id).get();
	}
	
	public Student createStudent (CreateStudentRequest createStudentRequest) {
		Student student = new Student(createStudentRequest);
		
		student = studentRepository.save(student);
		
		List<Subject> subjectsList = new ArrayList<Subject>();
		
		if(createStudentRequest.getSubjectList() != null) {
			for (CreateSubjectRequest createSubjectRequest : 
					createStudentRequest.getSubjectList()) {
				Subject subject = new Subject();
				subject.setSubjectName(createSubjectRequest.getSubjectName());
				subject.setGrades(createSubjectRequest.getGrades());
				subject.setStudent(student);
				
				subjectsList.add(subject);
			}
			
			subjectRepository.saveAll(subjectsList);
			
		}
		
		student.setSubjectList(subjectsList);
		
		return student;
	}
}