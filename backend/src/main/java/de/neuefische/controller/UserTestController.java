package de.neuefische.controller;

import de.neuefische.model.UserDocument;
import de.neuefische.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserTestController {
    private final UserService userService;

    @GetMapping
    public String printUserInfo(Principal principal){
        UserDocument user = userService.findByEmail(principal.getName()).orElseThrow();
        return "UserInfo: " + user.getEmail();
    }
}
