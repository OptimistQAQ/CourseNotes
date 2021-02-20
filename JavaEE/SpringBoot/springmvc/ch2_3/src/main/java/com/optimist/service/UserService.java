package com.optimist.service;

import com.optimist.pojo.UserForm;

public interface UserService {
	boolean login(UserForm user);
	boolean register(UserForm user);
}
