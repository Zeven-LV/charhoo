package com.charhoo.os.exception;

/**
 * 封装service 的异常,
 * 
 * @author zyp  
 *
 */
public class BusinessException extends Exception{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String status;
	
	
	public BusinessException(String status, String msg){
		super(msg);
		this.status = status;
	}
	
	public BusinessException(){
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
