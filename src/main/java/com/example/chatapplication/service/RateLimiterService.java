package com.example.chatapplication.service;

import com.example.chatapplication.exception.TooManyRequestException;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateLimiterService {
	private final DistributedLockService distributedLockService;
	private final RedisTemplate<String, String> redisTemplate;
	private static final String RATE_LIMITER_KEY = "rate_limiter";

	// enum here for extension as we can have different timeframe and max token for different apis
	public enum LimiterTokenType {
		USER_REQUEST(Duration.ofSeconds(1), 1);
		private final Duration timeFrame;
		private final long max;

		LimiterTokenType(Duration timeFrame, long max) {
			this.timeFrame = timeFrame;
			this.max = max;
		}
	}

	public boolean isRateLimited(LimiterTokenType tokenType, String key) {
		boolean tryLock = distributedLockService.tryLock(Duration.ofSeconds(1), key);
		if (!tryLock) {
			throw new TooManyRequestException("Another instance is holding lock");
		}
		try {
			Duration timeFrame = tokenType.timeFrame;
			String fullKey = RATE_LIMITER_KEY + ":" + key;
			if (redisTemplate.opsForValue().get(fullKey) == null) {
				redisTemplate.opsForValue().set(fullKey, "1", timeFrame);
				return false;
			}
			Long currentToken = redisTemplate.opsForValue().increment(fullKey);
			return currentToken != null && currentToken > tokenType.max;
		} finally {
			distributedLockService.unlock(key);
		}
	}
}
