package com.example.chatapplication.configuration;

import com.example.chatapplication.context.RequestContext;
import jakarta.annotation.Nonnull;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class UserAuditorAware implements AuditorAware<String> {

	@Override
	@Nonnull
	public Optional<String> getCurrentAuditor() {
		return Optional.ofNullable((String) RequestContext.get(AuditParameter.USER_ID.getStringValue()));
	}
}
