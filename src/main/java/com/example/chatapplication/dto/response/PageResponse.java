package com.example.chatapplication.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse<T> {
	private List<T> content;
	@JsonProperty("page_number")
	private int pageNumber;
	@JsonProperty("page_size")
	private int pageSize;
	private long total;
}