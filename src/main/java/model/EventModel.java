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

	private ArrayList<HashMap<String, Object>> getResultList(ResultSet data) {
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		if (data != null) {
			try {
				while (data.next()) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("id", data.getString(1));
					map.put("name", data.getString(2));
					result.add(map);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
