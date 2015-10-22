package org.bmsource.restcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = { "org.bmsource.restcontroller" })
public class RestExceptionHandler {

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<String> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		JSONObject json = new JSONObject();
		json.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR);
		json.put("exception", e.getClass().getName());
		json.put("message", e.getMessage());
		return new ResponseEntity<>(json.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseEntity<String> unauthorizedErrorHandler(HttpServletRequest req, HttpServletResponse resp, Exception e) throws Exception {
		JSONObject json = new JSONObject();
		json.put("statusCode", HttpStatus.UNAUTHORIZED);
		json.put("message", "Unauthorized access");
		return new ResponseEntity<>(json.toString(), HttpStatus.UNAUTHORIZED);
	}
}
