package de.neuefische.controller;

import de.neuefische.model.UserDocument;
import de.neuefische.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public UserDocument createUser(@RequestBody UserDocument user){
        if (!Objects.equals(user.getPassword(), user.getPasswordAgain())){
            throw new IllegalArgumentException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPasswordAgain(passwordEncoder.encode(user.getPasswordAgain()));
        return userService.createUser(user);
    }
}
