package com.datadynamics.bigdata.api.repository;

import com.datadynamics.bigdata.api.model.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository<Service, String> {
}
