package com.example.chatapplication.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RequestContext {
	private static final ThreadLocal<Map<String, Object>> CONTEXT = ThreadLocal.withInitial(ConcurrentHashMap::new);

	private RequestContext() {}

	public static Object get(String key) {
		return CONTEXT.get().get(key);
	}

	public static void set(String key, Object value) {
		CONTEXT.get().put(key, value);
	}
}
