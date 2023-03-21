package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Teacher;
import com.example.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	TeacherRepository teacherRepository;
	
	public Teacher getTeacherById (long id) {
		return teacherRepository.findById(id).get();
	}
}
