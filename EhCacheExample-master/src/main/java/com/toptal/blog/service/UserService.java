package com.toptal.blog.service;

import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.toptal.blog.exception.UserExistsException;
import com.toptal.blog.pojo.User;

public interface UserService {

	User createUser(String userName, String firstName, String password);
	
	@JsonRpcErrors({
        @JsonRpcError(exception=UserExistsException.class, code=-5678, message="User already exists", data="The Data"),
        @JsonRpcError(exception=Throwable.class,code=-187)
    })
    User createUser(@JsonRpcParam(value="theUserName") String userName, @JsonRpcParam(value="thePassword") String password);
    User findUserByUserName(String userName);
    int getUserCount();
    
}
