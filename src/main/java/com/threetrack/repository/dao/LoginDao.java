package com.threetrack.repository.dao;

import com.threetrack.entity.Login;
import java.util.List;

public interface LoginDao {
	List<Login> list();
	boolean registerLogin(Login login);
	boolean registerLogout(Login logout);
}
