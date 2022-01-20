package com.threetrack.service;

import com.threetrack.dto.UserSessionDto;
import com.threetrack.entity.Login;
import com.threetrack.entity.User;
import com.threetrack.repository.dao.LoginDao;
import com.threetrack.repository.dao.LoginDaoJpa;
import com.threetrack.repository.dao.UserDao;
import com.threetrack.repository.dao.UserDaoJpa;
import com.threetrack.utils.DateUtils;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

	@Override
	public List<UserSessionDto> getAllSessions() {

		LoginDao loginDao = new LoginDaoJpa();

		return loginDao.list()
		    .stream()
		    .map(login -> new UserSessionDto(login.getUserId(), "", login.getLoginId(), login.getLoginDate()))
		    .collect(Collectors.toList());
	}

	@Override
	public UserSessionDto login(String userName, String password) {
		LoginDao loginDao = new LoginDaoJpa();
		UserDao userDao = new UserDaoJpa();

		User user = userDao.findUser(userName, password);

		if (user == null)
			return null;

		// init login
		Login login = new Login();
		login.setUserId(user.getId());
		login.setLoginDate(DateUtils.getCurrentDate());

		if (!loginDao.registerLogin(login))
			return null;

		UserSessionDto userSession =
		    new UserSessionDto(user.getId(), user.getName(), login.getLoginId(), login.getLoginDate());

		return userSession;
	}

	@Override
	public boolean logout(Integer loginId) {

		LoginDao loginDao = new LoginDaoJpa();

		Login login = new Login();
		login.setLoginId(loginId);
		login.setLogoutDate(DateUtils.getCurrentDate());

		return loginDao.registerLogout(login);
	}
}
