package com.datadynamics.bigdata.api.service.s3.repository;

import com.datadynamics.bigdata.api.service.s3.model.acl.ACLPolicy;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ACLPolicyRepository extends CrudRepository<ACLPolicy, String> {

    Optional<ACLPolicy> findByBucketNameAndKeyAndOwnerName(String bucketName, String key, String ownerName);

}
