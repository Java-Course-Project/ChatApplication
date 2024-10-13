package com.example.chatapplication.service;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistributedLockService {
	private final RedisTemplate<String, String> redisTemplate;
	private static final String LOCK_KEY = "lock";
	private static final String LOCK_VALUE = "lock-value";

	private String getFullKey(String lockKey) {
		return LOCK_KEY + ":" + lockKey;
	}
	public boolean tryLock(Duration lockTime, String lockKey) {
		return Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(getFullKey(lockKey), LOCK_VALUE, lockTime));
	}

	public void unlock(String lockKey) {
		redisTemplate.delete(getFullKey(lockKey));
	}
}
