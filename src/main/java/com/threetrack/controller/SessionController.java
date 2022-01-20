package com.threetrack.controller;

import com.threetrack.dto.ResponseDto;
import com.threetrack.dto.UserSessionDto;
import com.threetrack.service.SessionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
public class SessionController {

	@Autowired
	private SessionService sessionService;

	@GetMapping("/all")
	public ResponseDto<?> all() {
		ResponseDto<List<?>> response = new ResponseDto<>();
		response.setData(sessionService.getAllSessions());
		response.setSuccess(true);
		return response;
	}

	@PostMapping("/login")
	public ResponseDto<?> logIn(@RequestParam String userName, @RequestParam String password) {

		UserSessionDto userSessionDto = sessionService.login(userName, password);

		ResponseDto<UserSessionDto> response = new ResponseDto<>();

		if (userSessionDto != null) {
			response.setData(userSessionDto);
			response.setMessage("Login success!");
			response.setSuccess(true);
		} else {
			response.setData(null);
			response.setMessage("The userName or password is incorrect");
			response.setSuccess(true);
		}
		return response;
	}

	@PutMapping("/logout")
	public ResponseDto<?> logOut(@RequestParam Integer sessionId) {

		ResponseDto<?> response = new ResponseDto<>();
		if (sessionService.logout(sessionId)) {
			response.setMessage("Logout success!");
			response.setSuccess(true);
		} else {
			response.setMessage("can't logout!");
			response.setSuccess(false);
		}
		return response;
	}
}
