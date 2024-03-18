package fr.campus.cda.charly.java_spring_boot_api.controller;

import fr.campus.cda.charly.java_spring_boot_api.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/users-security/")
public class SebApiController {
    @Autowired
    private MyService myService;

@GetMapping("/get-matches")
    public String getAllMatches() {
        return myService.callEndpoint();
    }
}
