package com.datadynamics.bigdata.api.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Query DSL을 사용하지 않는 경우 이 클래스를 삭제하도록함.
 * MyBATIS를 이용하지 않고 복잡한 JOIN을 DB의 의존성없이 하고자 하는 경우 Query DSL을 사용하도록 함.
 */
@RequiredArgsConstructor
@Configuration
public class QuerydslConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(entityManager);
    }

}