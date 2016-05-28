package com.toptal.blog.service;

import java.util.HashMap;
import java.util.Map;

import com.toptal.blog.exception.UserExistsException;
import com.toptal.blog.pojo.User;

public class UserServiceImpl implements UserService {
	
	private static Map<String,User> map = new HashMap<String,User>();

	public User createUser(String userName, String firstName, String password) {
        User user = new User();
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setPassword(password);
        map.put(userName, user);
        return user;
    }

	@Override
    public User createUser(String userName, String password) {
		if(map.containsKey(userName))
    		throw new UserExistsException();
        return this.createUser(userName, null, password);
    }

    public User findUserByUserName(String userName) {
//        return database.findUserByUserName(userName);
    	return map.get(userName);
    }

    public int getUserCount() {
//        return database.getUserCount();
    	return map.size();
    }

}
