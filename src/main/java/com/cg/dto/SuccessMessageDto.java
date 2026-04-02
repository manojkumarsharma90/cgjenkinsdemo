
package com.cg.dto;

public class SuccessMessageDto {

	private String msg;
	
	private Integer id;

	public SuccessMessageDto(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SuccessMessageDto(String msg, Integer id) {
		super();
		this.msg = msg;
		this.id = id;
	}
	
	
	
	
}
