package dao;

import java.sql.ResultSet;

import beans.EventBean;

public interface EventDao {

  
  boolean upsertEvent(EventBean bean);
  
  ResultSet getLocations();
  
  ResultSet getSports();
  
  ResultSet getEvents();
  
  boolean upsertEventMembers(String email, String status, String eventId);
  
  boolean deleteEvent(Integer eventId);
  
  ResultSet getEventMembers(Integer eventId);
  
}
