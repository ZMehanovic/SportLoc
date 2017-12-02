package beans;

import java.io.Serializable;

public class EventBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int eventId, sportId, locationId;
	
	private boolean openEvent;

	private String creatorEmail;
	private String title;
	private String capacity;
	private String startTime;
	private String endTime;
	private String description;
	private String address;

	public EventBean() {

	}	

	public EventBean(int eventId, int sportId, int locationId, boolean openEvent, String creatorEmail, String title,
			String capacity, String startTime, String endTime, String description, String address) {
		super();
		this.eventId = eventId;
		this.sportId = sportId;
		this.locationId = locationId;
		this.openEvent = openEvent;
		this.creatorEmail = creatorEmail;
		this.title = title;
		this.capacity = capacity;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		this.address = address;
	}



	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getSportId() {
		return sportId;
	}

	public void setSportId(int sportId) {
		this.sportId = sportId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public boolean isOpenEvent() {
		return openEvent;
	}

	public void setOpenEvent(boolean openEvent) {
		this.openEvent = openEvent;
	}

	public String getCreatorEmail() {
		return creatorEmail;
	}

	public void setCreatorEmail(String creatorEmail) {
		this.creatorEmail = creatorEmail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

}
