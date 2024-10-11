package com.example.chatapplication.mapper;

import com.example.chatapplication.dto.response.PageResponse;
import java.util.Collection;
import org.springframework.data.domain.Page;

public interface Mapper<S, D> {
	D map(S source);

	default PageResponse<D> map(Page<S> source) {
		return new PageResponse<>(source.getContent().stream().map(this::map).toList(),
								  source.getPageable().getPageNumber(),
								  source.getPageable().getPageSize(),
								  source.getTotalElements());
	}

	default Collection<D> map(Collection<S> source) {
		return source.stream().map(this::map).toList();
	}
}