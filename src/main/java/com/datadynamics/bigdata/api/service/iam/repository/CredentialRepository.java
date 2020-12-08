package com.datadynamics.bigdata.api.service.iam.repository;

import com.datadynamics.bigdata.api.service.iam.model.Credential;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialRepository extends CrudRepository<Credential, String> {

    Optional<Credential> findCredentialByUsernameAndAccessKey(String username, String accessKey);

}
