package com.example.chatapplication.configuration;

import com.example.chatapplication.context.RequestContext;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuditFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
									@Nonnull FilterChain filterChain)
			throws ServletException, IOException {

		Object userIdHeader = request.getHeader("X-USER-ID");
		if (userIdHeader != null) {
			RequestContext.set(AuditParameter.USER_ID.getStringValue(), userIdHeader);
		}

		filterChain.doFilter(request, response);
	}
}
