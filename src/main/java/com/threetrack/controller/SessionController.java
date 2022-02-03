package com.threetrack.controller;

import java.util.List;

import com.threetrack.dto.ResponseDto;
import com.threetrack.dto.UserSessionDto;
import com.threetrack.service.SessionService;
import com.threetrack.utils.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {

	@Autowired
	private SessionService sessionService;

	@GetMapping("/all")
	public ResponseDto<?> all() {
		ResponseDto<List<?>> response = new ResponseDto<>();
		try {
			response.setData(sessionService.getAllSessions());
			response.setSuccess(true);
			response.setMessage(Constants.PROCESSED_OK);	
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(Constants.ERROR);
		}
		
		return response;
	}

	@PostMapping("/login")
	public ResponseDto<?> logIn(@RequestParam String userName, @RequestParam String password) {
		ResponseDto<UserSessionDto> response = new ResponseDto<>();
		try {
			UserSessionDto userSessionDto = sessionService.login(userName, password);
			if (userSessionDto != null) {
				response.setData(userSessionDto);
				response.setMessage(Constants.LOGIN_SUCCESS);
			} else {
				response.setData(null);
				response.setMessage(Constants.USER_PASS_ERROR);
			}
			response.setSuccess(true);	
		} catch (Exception e) {
			response.setSuccess(true);
			response.setMessage(Constants.ERROR);
		}
		
		return response;
	}

	@PutMapping("/logout")
	public ResponseDto<?> logOut(@RequestParam Integer sessionId) {
		ResponseDto<?> response = new ResponseDto<>();
		try {
			if (sessionService.logout(sessionId)) {
				response.setMessage(Constants.LOGOUT_SUCCESS);
			} else {
				response.setMessage(Constants.LOGOUT_ERROR);
			}	
			response.setSuccess(true);
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(Constants.ERROR);
		}
		
		return response;
	}
}
