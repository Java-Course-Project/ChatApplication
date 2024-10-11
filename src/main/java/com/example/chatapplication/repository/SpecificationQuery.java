package com.example.chatapplication.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationQuery<E> {
	Page<E> findByCriteria(Query query, Pageable pageable, Class<E> clazz);
}
