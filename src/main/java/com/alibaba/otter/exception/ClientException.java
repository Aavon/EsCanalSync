package com.alibaba.otter.exception;

public class ClientException extends Exception {

	/**
	 *  自定义异常
	 */
	private static final long serialVersionUID = 2153011890570197406L;
	
	
	public ClientException(String msg) {
		super(msg);
	}

}
