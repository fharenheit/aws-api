package com.datadynamics.bigdata.api.repository.iam;

import com.datadynamics.bigdata.api.model.iam.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository<Service, String> {
}
