package com.example.chatapplication.configuration;

import lombok.Getter;

@Getter
public enum AuditParameter {
	USER_ID("userId"),;
	private final String stringValue;

	AuditParameter(String stringValue) {
		this.stringValue = stringValue;
	}
}
