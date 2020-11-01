package com.datadynamics.bigdata.api.repository.iam;

import com.datadynamics.bigdata.api.model.Credential;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends CrudRepository<Credential, Long> {
}
