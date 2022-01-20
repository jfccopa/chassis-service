package com.threetrack.service;

import com.threetrack.dto.UserSessionDto;
import java.util.List;

public interface SessionService {
	List<UserSessionDto> getAllSessions();
	UserSessionDto login(String userName, String password);
	boolean logout(Integer sessionId);
}
