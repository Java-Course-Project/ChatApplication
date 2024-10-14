package com.example.chatapplication.service;

import com.example.chatapplication.exception.TooManyRequestException;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RateLimiterService {
	private final DistributedLockService distributedLockService;
	private final RedisTemplate<String, String> redisTemplate;
	private static final String RATE_LIMITER_KEY = "rate_limiter";

	// enum here for extension as we can have different timeframe and max token for different apis
	public enum LimiterTokenType {
		USER_REQUEST(Duration.ofSeconds(10), 1);
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
				log.debug("Creating limiter key {}", fullKey);
				redisTemplate.opsForValue().set(fullKey, "1", timeFrame);
				return true;
			}
			Long currentToken = redisTemplate.opsForValue().increment(fullKey);
			log.debug("Current token {}", currentToken);
			return currentToken != null && currentToken < tokenType.max;
		} finally {
			distributedLockService.unlock(key);
		}
	}
}
