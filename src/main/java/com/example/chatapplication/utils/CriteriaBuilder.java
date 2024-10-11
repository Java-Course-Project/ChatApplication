package com.example.chatapplication.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.mongodb.core.query.Criteria;

@UtilityClass
public class CriteriaBuilder {
	public Criteria stringContains(String field, String value) {
		if (value != null) {
			return Criteria.where(field).regex(value, "i");
		}
		return new Criteria();
	}
}
