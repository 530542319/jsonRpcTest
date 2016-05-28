package com.toptal.blog.client;

import java.net.URL;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.toptal.blog.pojo.User;

public class ClientTest {

	public static void main(String[] args) throws Throwable {
		JsonRpcHttpClient client = new JsonRpcHttpClient(new URL(
				"http://localhost:8080/EhCacheExample-master/UserService.json"));
//		User user = client.invoke("createUser", new Object[] { "bob", "jackson",
//				"the builder" }, User.class);
		User user1 = client.invoke("findUserByUserName", new Object[] { "bob"}, User.class);
		System.out.println(user1);
	}

}
