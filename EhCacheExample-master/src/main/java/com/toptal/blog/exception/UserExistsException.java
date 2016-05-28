package com.toptal.blog.exception;

public class UserExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2321486277448163463L;
	
	private String theUserName ;  //异常对应的返回码
    private String thePassword;  //异常对应的描述信息
     
    public UserExistsException() {
        super();
    }

	public String getTheUserName() {
		return theUserName;
	}

	public void setTheUserName(String theUserName) {
		this.theUserName = theUserName;
	}

	public String getThePassword() {
		return thePassword;
	}

	public void setThePassword(String thePassword) {
		this.thePassword = thePassword;
	}
 
//    public UserExistsException(String message) {
//        super(message);
//        msgDes = message;
//    }
}
