package com.datadynamics.bigdata.api.service.iam.repository;

import com.datadynamics.bigdata.api.service.iam.model.User;
import com.datadynamics.bigdata.api.service.iam.model.UserId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, UserId> {

    @Query("select u from com.datadynamics.bigdata.api.service.iam.model.User u where u.userId.path LIKE CONCAT('',:pathPrefix,'%')")
    Optional<List<User>> findUsersWithPathPrefix(@Param("pathPrefix") String pathPrefix);

}
