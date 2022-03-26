package com.example.javapatternmirea2.registration;

import com.example.javapatternmirea2.registration.dto.NewUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private final UserDao userDao;

    @Autowired
    public RegistrationController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping("")
    public void registrationPage(@RequestBody NewUserDto newUser) {
        userDao.saveNewUser(newUser.getUsername(), newUser.getPassword());
    }
}
