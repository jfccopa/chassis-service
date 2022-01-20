package com.threetrack.repository.dao;

import com.threetrack.entity.User;
import com.threetrack.repository.GenericDao;

public interface UserDao extends GenericDao<User, Integer> {
	User findUser(String userName, String password);
}
