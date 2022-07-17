package academy.kata.pp_3_1_4.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class LoginController {

    @GetMapping("/admin")
    public String adminPage() {
        return "adminPage";
    }

    @GetMapping("/user")
    public  String userPage() {
        return "userPage";
    }

    @PreAuthorize("authentication.getPrincipal().getAge() >= 18")
    @GetMapping("/user/onlyAdult")
    public String getPageOver18() {
        return "onlyAdultUserPage";
    }
}
