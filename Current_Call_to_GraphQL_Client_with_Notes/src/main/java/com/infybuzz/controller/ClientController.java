package com.infybuzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infybuzz.request.CreateStudentRequest;
import com.infybuzz.response.SchoolResponse;
import com.infybuzz.response.StudentResponse;
import com.infybuzz.response.TeacherResponse;
import com.infybuzz.service.ClientService;

@RestController
@RequestMapping("/api/student")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/get")
    public StudentResponse getStudent(@RequestBody String query) {
        return clientService.getStudent(query);
    }

}

	



/* @PostMapping("/create")
public StudentResponse createStudent(
		@RequestBody CreateStudentRequest createStudentRequest) {
	return clientService.createStudent(createStudentRequest);
}

} */
