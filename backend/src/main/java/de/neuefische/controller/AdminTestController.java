package de.neuefische.controller;

import de.neuefische.model.UserDocument;
import de.neuefische.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminTestController {

    private final UserService userService;

    @GetMapping
    public String printAdminInfo(Principal principal){
        UserDocument user = userService.findByEmail(principal.getName()).orElseThrow();
        return "AdminInfo: " + user.getEmail();
    }

}
