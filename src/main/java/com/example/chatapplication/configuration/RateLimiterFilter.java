package com.example.chatapplication.configuration;

import com.example.chatapplication.exception.ResourceNotFoundException;
import com.example.chatapplication.exception.TooManyRequestException;
import com.example.chatapplication.service.RateLimiterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import static com.example.chatapplication.exception.GlobalExceptionHandler.buildExceptionHandler;

@Component
@RequiredArgsConstructor
public class RateLimiterFilter extends OncePerRequestFilter {
	private final RateLimiterService limiterService;
	private final ObjectMapper objectMapper;

	@Override
	protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
									@Nonnull FilterChain filterChain)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (!(uri.contains("swagger") || uri.contains("api-docs"))) {
			String apiKey = request.getHeader("X-API-KEY");
			if (apiKey == null) {
				buildExceptionHandler(new ResourceNotFoundException("Api Key is required for path " + request.getRequestURI()), request,
									  response, objectMapper);
				return;
			}
			if (!limiterService.isRateLimited(RateLimiterService.LimiterTokenType.USER_REQUEST, apiKey)) {
				buildExceptionHandler(new TooManyRequestException("Too many requests"), request, response, objectMapper);
				return;
			}
		}

		filterChain.doFilter(request, response);
	}
}
