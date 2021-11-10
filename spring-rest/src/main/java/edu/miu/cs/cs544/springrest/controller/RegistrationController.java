package edu.miu.cs.cs544.springrest.controller;

import edu.miu.cs.cs544.springrest.domain.Student;
import edu.miu.cs.cs544.springrest.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("registrations")
public class RegistrationController {

    private final RegistrationService service;

    @Autowired
    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> allRegistration() {
        return service.findAll();
    }
}
