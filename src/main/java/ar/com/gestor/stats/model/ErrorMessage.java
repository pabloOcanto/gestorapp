package ar.com.gestor.stats.model;

import java.util.Date;

public class ErrorMessage {
	
	private Integer status;
	private String  message;
	private Date timeStampt;
	
	public ErrorMessage() {}
	
	public ErrorMessage(Integer status, String message,Date timeStampt) {
		this.status = status;
		this.message=message;
		this.timeStampt = timeStampt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimeStampt() {
		return timeStampt;
	}

	public void setTimeStampt(Date timeStampt) {
		this.timeStampt = timeStampt;
	}
	
	

}
