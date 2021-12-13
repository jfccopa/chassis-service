package com.threetrack.repository.dao;

import com.threetrack.entity.User;
import com.threetrack.repository.GenericDaoJpa;
import org.springframework.stereotype.Component;

@Component
public class UserDaoJpa extends GenericDaoJpa<User, Integer> implements UserDao {}