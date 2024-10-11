package com.example.chatapplication.configuration;

import jakarta.annotation.Nonnull;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class UserAuditorAware implements AuditorAware<String> {

	@Override
	@Nonnull
	public Optional<String> getCurrentAuditor() {
		// TODO: get user from header
		return Optional.empty();
	}
}
