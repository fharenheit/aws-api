package com.datadynamics.bigdata.api.service.iam.service;

import com.datadynamics.bigdata.api.service.iam.model.Credential;
import com.datadynamics.bigdata.api.service.iam.model.User;
import com.datadynamics.bigdata.api.service.iam.model.UserId;
import com.datadynamics.bigdata.api.service.iam.repository.CredentialRepository;
import com.datadynamics.bigdata.api.service.iam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    CredentialRepository credentialRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByUserId(UserId userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<Credential> getCredentialByUserId(String userId) {
        return credentialRepository.findById(userId);
    }

}
