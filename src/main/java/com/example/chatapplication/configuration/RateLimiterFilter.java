package com.example.chatapplication.configuration;

import com.example.chatapplication.exception.ResourceNotFoundException;
import com.example.chatapplication.exception.TooManyRequestException;
import com.example.chatapplication.service.RateLimiterService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class RateLimiterFilter extends OncePerRequestFilter {
	private final RateLimiterService limiterService;

	@Override
	protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
									@Nonnull FilterChain filterChain)
			throws ServletException, IOException {
		String apiKey = request.getHeader("X-API-KEY");
		if (apiKey == null) {
			throw new ResourceNotFoundException("Api Key is required");
		}
		if (!limiterService.isRateLimited(RateLimiterService.LimiterTokenType.USER_REQUEST, apiKey)) {
			throw new TooManyRequestException("Too many requests");
		}

		filterChain.doFilter(request, response);
	}
}
