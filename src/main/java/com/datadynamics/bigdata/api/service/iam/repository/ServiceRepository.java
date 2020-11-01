package com.datadynamics.bigdata.api.service.iam.repository;

import com.datadynamics.bigdata.api.service.iam.model.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository<Service, String> {
}
