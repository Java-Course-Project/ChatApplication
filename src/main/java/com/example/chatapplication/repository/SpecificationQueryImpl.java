package com.example.chatapplication.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecificationQueryImpl<E> implements SpecificationQuery<E> {
	private final MongoTemplate mongoTemplate;

	@Override
	public Page<E> findByCriteria(Query query, Pageable pageable, Class<E> clazz) {
		long total = mongoTemplate.count(query, clazz);

		query.with(pageable);
		List<E> content = mongoTemplate.find(query, clazz);

		return new PageImpl<>(content, pageable, total);
	}
}
