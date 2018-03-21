package servlet.get;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import helper.HttpServletHelper;
import model.EventModel;

@WebServlet(name = "CitiesServlet", urlPatterns = { "/getCities" })
public class CitiesServlet extends HttpServletHelper {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventModel event = new EventModel();
		String result = new Gson().toJson(event.getLocationsList());

		showResponse(response, result);
	}

}
