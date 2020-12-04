package com.datadynamics.bigdata.api.service.iam.service;

import com.datadynamics.bigdata.api.service.iam.model.Credential;
import com.datadynamics.bigdata.api.service.iam.model.User;
import com.datadynamics.bigdata.api.service.iam.model.UserId;

import java.util.Optional;

public interface UserService {

    void saveUser(User user);

    Optional<User> getUserByUserId(UserId userId);

    Optional<Credential> getCredentialByUserId(String userName);
}
