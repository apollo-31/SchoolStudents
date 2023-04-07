package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.School;

import com.example.repository.SchoolRepository;

@Service
public class SchoolService {

	@Autowired
	SchoolRepository schoolRepository;
	
	public School getSchoolById (long id) {
		return schoolRepository.findById(id).get();
	}
}