package com.aop;

import java.net.URL;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

public class JsonRpcClientTest {

	public static void main(String[] args) throws Throwable {
		JsonRpcHttpClient client = new JsonRpcHttpClient(new URL(
				"http://localhost:8080/EhCacheExample-master/UserService.json"));
		TbUser user = client.invoke("createUser", new Object[] { "bob",
				"the builder" }, TbUser.class);
//		TbUser user1 = client.invoke("findUserByUserName", new Object[] { "bob"}, TbUser.class);
		System.out.println(user);
	}
}
