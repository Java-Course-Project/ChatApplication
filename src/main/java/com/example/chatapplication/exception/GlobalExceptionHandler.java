package com.example.chatapplication.exception;

import com.example.chatapplication.dto.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoWriteException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	public static final Map<Class<? extends RuntimeException>, HttpStatus> HTTP_STATUS_MAP
			= Map.of(ResourceNotFoundException.class, HttpStatus.BAD_REQUEST,
					 TooManyRequestException.class, HttpStatus.TOO_MANY_REQUESTS,
					 UserNotInRoomException.class, HttpStatus.BAD_REQUEST,
					 DuplicateKeyException.class, HttpStatus.BAD_REQUEST);

	public static void buildExceptionHandler(RuntimeException ex, HttpServletRequest request, HttpServletResponse response,
											 ObjectMapper objectMapper)  {

		response.setStatus(HTTP_STATUS_MAP.get(ex.getClass()).value());
		try {
			response.getWriter().write(objectMapper.writeValueAsString(ErrorResponse.builder()
																					.error(ex.getClass().getSimpleName())
																					.message(ex.getMessage())
																					.path(request.getRequestURI())
																					.build()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@ExceptionHandler({ResourceNotFoundException.class, TooManyRequestException.class, UserNotInRoomException.class,
			MongoWriteException.class})
	public ResponseEntity<ErrorResponse> customExceptionHandler(RuntimeException ex, HttpServletRequest request) {
		log.error("", ex);
		return ResponseEntity.status(HTTP_STATUS_MAP.get(ex.getClass())).body(ErrorResponse.builder()
																						   .error(ex.getClass().getSimpleName())
																						   .message(ex.getMessage())
																						   .path(request.getRequestURI())
																						   .build());
	}

}
