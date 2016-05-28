package com.toptal.blog.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.toptal.blog.service.ReceiveChannel;

/**
 * Servlet implementation class ServiceJsonRpcServlet
 */
public class ServiceJsonRpcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceJsonRpcServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse arg1)
			throws ServletException, IOException {
		String UserKey = request.getHeader("UserKey");
		  //在此可以验证客户端的请求，前提是客户端需要在请求头中加入相应的值，我在后面在讲。
	}

	private JsonRpcServer rpcService = null; 

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		rpcService = new JsonRpcServer(new ReceiveChannel());
	}

}
