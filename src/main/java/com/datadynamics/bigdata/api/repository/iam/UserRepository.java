package com.datadynamics.bigdata.api.repository.iam;

import com.datadynamics.bigdata.api.model.iam.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
