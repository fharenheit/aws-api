package com.datadynamics.bigdata.api.service.iam.service;

import com.datadynamics.bigdata.api.service.iam.model.Credential;
import com.datadynamics.bigdata.api.service.iam.model.User;
import com.datadynamics.bigdata.api.service.iam.model.UserId;
import com.datadynamics.bigdata.api.service.iam.repository.CredentialRepository;
import com.datadynamics.bigdata.api.service.iam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/iam")
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CredentialRepository credentialRepository;

    @GetMapping("/password")
    public ResponseEntity service(@RequestParam String username) {
        UserId userId = UserId.builder().username(username).path("/").build();
        Optional<User> userById = userRepository.findById(userId);
        if (!userById.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Credential> credentialById = credentialRepository.findById(username);
        if (!credentialById.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(credentialById.get().getSecretKey());
    }
}
