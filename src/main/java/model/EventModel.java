package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import beans.EventBean;
import beans.EventMemberBean;
import database.DbManager;
import helper.EventOptions;

public class EventModel {
	
	/**
	 * Creates a new event based on data provided
	 * 
	 * @param eventBean
	 *            bean containing data about event
	 * @return boolean, true if successful
	 */
	public boolean resolveEvent(EventBean eventBean) {
		boolean result = false;

		switch (EventOptions.valueOf(eventBean.getOption().toUpperCase())) {
		case CREATE:
		case UPDATE:
			result = createUpdateEvent(eventBean);
			break;
		case DELETE:
			result = deleteEvent(eventBean.getEventId());
			break;
		default:
			break;

		}
		
		return result;
	}
	/**
	 * deletes event by id
	 *  
	 * @param eventId
	 * @return boolean, true if successful
	 */
	private boolean deleteEvent(Integer eventId) {
		boolean result = new DbManager().deleteEvent(eventId);
		
		return result;
	}

	/**
	 * Creates a new event or updates existing one based on data provided
	 * 
	 * @param eventBean
	 *            bean containing data about event
	 * @return boolean, true if successful
	 */
	private boolean createUpdateEvent(EventBean eventBean) {
		// TODO check values in bean.
		boolean result = new DbManager().upsertEvent(eventBean);

		return result;
	}

	/**
	 * Gets a list of all available cities.
	 * 
	 * @return List of available cities (id, title)
	 */
	public ArrayList<HashMap<String, Object>> getCitiesList() {
		ResultSet data = new DbManager().getCities();
		ArrayList<HashMap<String, Object>> result = getResultList(data);
		// JSONArray array=JSONArray.
		return result;
	}

	/**
	 * Gets a list of all available sports.
	 * 
	 * @return List of available sports (id, title)
	 */
	public ArrayList<HashMap<String, Object>> getSportsList() {
		ResultSet data = new DbManager().getSports();
		ArrayList<HashMap<String, Object>> result = getResultList(data);

		return result;
	}

	/**
	 * Gets a list from ResultSet object
	 * 
	 * @param resultSet
	 *            ResultSet containing id and title
	 * 
	 * @return ArrayList of maps containing id and name
	 */
	private ArrayList<HashMap<String, Object>> getResultList(ResultSet resultSet) {
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("id", resultSet.getString(1));
					map.put("name", resultSet.getString(2));
					result.add(map);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Gets a list of all available events.
	 * 
	 * @return ArrayList containing events and their general data.
	 */
	public ArrayList<EventBean> getEventsList() {
		ArrayList<EventBean> result = new ArrayList<EventBean>();
		ResultSet resultSet = new DbManager().getEvents();
		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					EventBean bean = new EventBean();
					bean.setAddress(resultSet.getString("adresa"));
					bean.setCreatorUserName(resultSet.getString("kreator"));
					bean.setEventId(resultSet.getInt("id_dogadaj"));
					bean.setTitle(resultSet.getString("naziv"));
					bean.setMaxCapacity(resultSet.getInt("kapacitet"));
					bean.setCurrentCapacity((resultSet.getString("sudionici") != null ? resultSet.getInt("sudionici") : 0) + 1);
					bean.setStartTime(resultSet.getString("od"));
					bean.setEndTime(resultSet.getString("do"));
					bean.setOpenEvent(resultSet.getBoolean("otvoreno"));
					bean.setDescription(resultSet.getString("opis"));
					bean.setSport(resultSet.getString("sport"));
					bean.setSportId(resultSet.getInt("id_sport"));
					bean.setLocation(resultSet.getString("grad"));
					bean.setLocationId(resultSet.getInt("id_grad"));
					result.add(bean);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * Updates a request to join event or adds a new one
	 *
	 * @param params
	 *            map containing email,status and eventId
	 * 
	 * @return boolean false if an SQL error occurs or if there is a mistake with
	 *         the parametars otherwise returns true
	 *         
	 */
	public boolean updateEventMember(Map<String, String[]> params) {
		boolean result = false;
		String email = "";
		String status = "";
		String eventId = "";
		if (params != null && !params.isEmpty() && params.containsKey("email") && params.containsKey("status")
				&& params.containsKey("eventId")) {
			email = params.get("email")[0];
			status = params.get("status")[0];
			eventId = params.get("eventId")[0];
			result = true;
		}
		if (result) {
			result = new DbManager().upsertEventMembers(email, status, eventId);

		}

		return result;
	}
	
	/**
	 * Fetches a list of members for a specified event
	 *
	 * @param eventId
	 *            id required for finding event members
	 * 
	 * @return list of EventMemberBean containing email, status and username
	 *         
	 */
	public ArrayList<EventMemberBean> getEventMembers(Integer eventId) {
		if (eventId == null) {
			return null;
		}
		ArrayList<EventMemberBean> result = new ArrayList<EventMemberBean>();
		ResultSet resultSet=new DbManager().getEventMembers(eventId);

		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					EventMemberBean bean = new EventMemberBean();
					bean.setEmail(resultSet.getString("email"));
					bean.setStatus(resultSet.getString("status"));
					bean.setUsername(resultSet.getString("kor_ime"));
					result.add(bean);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
	}

}
