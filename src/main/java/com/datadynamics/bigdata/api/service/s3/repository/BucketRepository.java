package com.datadynamics.bigdata.api.service.s3.repository;

import com.datadynamics.bigdata.api.service.s3.model.Bucket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BucketRepository extends CrudRepository<Bucket, String> {

    @Query("select t from com.datadynamics.bigdata.api.service.s3.model.Bucket t where t.share = true and t.expose = true")
    List<Bucket> findBucketsBySharedAndExposed();

}
