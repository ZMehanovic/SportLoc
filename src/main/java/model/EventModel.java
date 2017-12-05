package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import beans.EventBean;
import database.DbManager;

public class EventModel {
	/**
	 * Creates a new event based on data provided
	 * 
	 * @param eventBean
	 *            bean containing data about event
	 * @return error message, null if successful
	 */
	public String createEvent(EventBean eventBean) {
		// TODO check values in bean.
		String result = new DbManager().addEvent(eventBean);

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
					bean.setCurrentCapacity(
							resultSet.getString("sudionici") != null ? resultSet.getInt("sudionici") : 0);
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
}
