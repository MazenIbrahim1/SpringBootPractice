package com.example.nobsv2.security;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateNewUserController {

    private final PasswordEncoder encoder;

    private final CustomUserRepository customUserRepository;

    public CreateNewUserController(PasswordEncoder encoder, CustomUserRepository customUserRepository) {
        this.encoder = encoder;
        this.customUserRepository = customUserRepository;
    }

    @PostMapping("/createnewuser")
    public ResponseEntity<String> createNewUser(@RequestBody CustomUser user) {
        Optional<CustomUser> optionalUser = customUserRepository.findById(user.getUsername());
        // If user doesn't exist already... save new user
        if(!optionalUser.isPresent()) {
            customUserRepository.save(new CustomUser(user.getUsername(), encoder.encode(user.getPassword())));
            return ResponseEntity.ok("Success");
        }

        return ResponseEntity.badRequest().body("Failure");
    }
}
