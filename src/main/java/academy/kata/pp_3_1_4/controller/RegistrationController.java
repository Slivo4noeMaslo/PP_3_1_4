package academy.kata.pp_3_1_4.controller;

import academy.kata.pp_3_1_4.model.User;
import academy.kata.pp_3_1_4.service.MailSender;
import academy.kata.pp_3_1_4.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private UserService userService;
    private MailSender mailSender;

    public RegistrationController(UserService userService, MailSender mailSender) {
        this.userService = userService;
        this.mailSender = mailSender;
    }

    @PostMapping("/registration")
    public ResponseEntity<User> registrationUser(@RequestBody User user) {
        mailSender.send(user);
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
