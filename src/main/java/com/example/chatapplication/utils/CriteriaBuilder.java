package com.example.chatapplication.utils;

import java.time.Instant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CriteriaBuilder {

	public static Criteria stringContains(String field, String value) {
		if (value != null) {
			return Criteria.where(field).regex(value, "i");
		}
		return new Criteria();
	}

	public static Criteria stringEquals(String field, String value) {
		if (value != null) {
			return Criteria.where(field).is(value);
		}
		return new Criteria();
	}

	public static Criteria timeBetween(String field, Instant from, Instant to) {
		if (from != null && to != null) {
			return Criteria.where(field).gte(from).lt(to);
		}
		return new Criteria();
	}
}
