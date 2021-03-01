package com.datadynamics.bigdata.api.service.s3.repository;

import com.datadynamics.bigdata.api.service.s3.model.acl.ACLPolicyDetail;
import org.springframework.data.repository.CrudRepository;

public interface ACLPolicyDetailRepository extends CrudRepository<ACLPolicyDetail, Integer> {

}
