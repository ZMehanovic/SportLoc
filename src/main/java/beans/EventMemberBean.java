package beans;

import java.io.Serializable;

public class EventMemberBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer eventId;
	private String email;
	private String username;
	private String status;

	
	public EventMemberBean() {

	}

	public Integer getEventId() {
		return eventId;
	}


	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}