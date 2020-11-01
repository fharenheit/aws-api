package com.datadynamics.bigdata.api.service.iam.repository;

import com.datadynamics.bigdata.api.model.iam.Credential;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends CrudRepository<Credential, Long> {
}
