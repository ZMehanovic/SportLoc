package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.EventBean;

class EventDaoImpl implements EventDao{
  
  DAOFactory daoFactory;
  
  public EventDaoImpl(DAOFactory daoFactory) {
    this.daoFactory=daoFactory;
  }
  
  @Override
  public boolean upsertEvent(EventBean bean) {
    boolean result = false;
    
    String eventId = bean.getEventId() != null ? String.valueOf(bean.getEventId()) : "default";
    
    String query = "INSERT INTO public.dogadaj( id_dogadaj, naziv, kapacitet, od, \"do\", adresa, otvoreno, opis, id_sport, id_grad, kreator)\r\n" + 
        "VALUES ("+eventId+",\r\n" + 
        "        '" + bean.getTitle() + "',\r\n" + 
        "        '" + bean.getMaxCapacity() + "',\r\n" + 
        "        '" + bean.getStartTime() + "',\r\n" + 
        "        '" + bean.getEndTime() + "',\r\n" + 
        "        '" + bean.getAddress() + "',\r\n" + 
        "        " + bean.isOpenEvent() + ",\r\n" + 
        "        '" + bean.getDescription() + "',\r\n" + 
        "        " + bean.getSportId() + ",\r\n" + 
        "        " + bean.getLocationId() + ",\r\n" + 
        "        '" + bean.getCreatorEmail() + "') ON CONFLICT (id_dogadaj) DO\r\n" + 
        "UPDATE\r\n" + 
        "SET naziv = excluded.naziv,\r\n" + 
        "    kapacitet = excluded.kapacitet,\r\n" + 
        "    od = excluded.od,\r\n" + 
        "    \"do\" = excluded.\"do\",\r\n" + 
        "    adresa = excluded.adresa,\r\n" + 
        "    otvoreno = excluded.otvoreno,\r\n" + 
        "    opis = excluded.opis,\r\n" + 
        "    id_sport = excluded.id_sport,\r\n" + 
        "    id_grad = excluded.id_grad;";

    try {
      Connection con = daoFactory.getConnection();
      con.createStatement().executeUpdate(query);
      con.close();
      result = true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
  
  @Override
  public ResultSet getLocations() {
    ResultSet result = null;
    try {

      Connection con = daoFactory.getConnection();
      result = con.createStatement().executeQuery("SELECT id_grad, naziv FROM public.grad");
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
  
  @Override
  public ResultSet getSports() {
    ResultSet result = null;
    try {

      Connection con = daoFactory.getConnection();
      result = con.createStatement().executeQuery("SELECT id_sport, naziv FROM public.sport;");
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
  
  @Override
  public ResultSet getEvents() {
    ResultSet result = null;
    String query= "SELECT n1.*,\r\n" + 
        "       n2.sudionici\r\n" + 
        "FROM\r\n" + 
        "  (SELECT d.id_dogadaj,\r\n" + 
        "          d.naziv,\r\n" + 
        "          kapacitet,\r\n" + 
        "          od,\r\n" + 
        "          \"do\",\r\n" + 
        "          adresa,\r\n" + 
        "          otvoreno,\r\n" + 
        "          d.opis,\r\n" + 
        "          s.naziv sport,\r\n" + 
        "          gr.naziv grad,\r\n" + 
        "          ko.kor_ime kreator,\r\n" + 
        "          gr.id_grad,\r\n" + 
        "          s.id_sport\r\n" + 
        "   FROM public.dogadaj d\r\n" + 
        "   INNER JOIN public.grad gr ON gr.id_grad=d.id_grad\r\n" + 
        "   INNER JOIN public.sport s ON s.id_sport=d.id_sport\r\n" + 
        "   INNER JOIN public.korisnik ko ON ko.email=d.kreator\r\n" + 
        "   GROUP BY d.id_dogadaj,\r\n" + 
        "            s.naziv,\r\n" + 
        "            gr.naziv,\r\n" + 
        "            ko.kor_ime,\r\n" + 
        "            gr.id_grad,\r\n" + 
        "            s.id_sport) AS n1\r\n" + 
        "LEFT JOIN\r\n" + 
        "  (SELECT count(s.id_dogadaj) AS sudionici,\r\n" + 
        "          d.id_dogadaj\r\n" + 
        "   FROM public.dogadaj d\r\n" + 
        "   INNER JOIN public.sudionik s ON s.id_dogadaj=d.id_dogadaj\r\n" + 
        "   WHERE s.status='accepted'\r\n" + 
        "   GROUP BY s.id_dogadaj,\r\n" + 
        "            d.id_dogadaj) AS n2 ON n2.id_dogadaj=n1.id_dogadaj;";
    try {
      Connection con = daoFactory.getConnection();
      result = con.createStatement().executeQuery(query);
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;

  }
  
  @Override
  public boolean upsertEventMembers(String email, String status, String eventId) {
    boolean result = false;
    String query="INSERT INTO public.sudionik( id_dogadaj, email_korisnik, status)\r\n" + 
        "VALUES (" + eventId + ",\r\n" + 
        "        '" + email + "',\r\n" + 
        "        '" + status + "') ON CONFLICT (id_dogadaj, email_korisnik) DO\r\n" + 
        "UPDATE\r\n" + 
        "SET status = excluded.status;";
    try {

      Connection con = daoFactory.getConnection();
      result = con.createStatement().executeUpdate(query) > 0 ? true : false;
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
  
  @Override
  public boolean deleteEvent(Integer eventId) {
    boolean result = false;
    String query = "DELETE FROM public.dogadaj WHERE id_dogadaj=" + eventId + ";";
    
    try {
      Connection con = daoFactory.getConnection();
      result = con.createStatement().executeUpdate(query) > 0 ? true : false;
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
  
  @Override
  public ResultSet getEventMembers(Integer eventId) {
    ResultSet result = null;
    String query = "SELECT s.status, ko.email, ko.kor_ime FROM public.sudionik s\r\n"
        + "INNER JOIN korisnik ko on ko.email=s.email_korisnik WHERE id_dogadaj=" + eventId + ";";
    try {
      Connection con = daoFactory.getConnection();
      result = con.createStatement().executeQuery(query);
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return result;
  }

}
