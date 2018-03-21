package servlet.get;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import helper.HttpServletHelper;
import model.EventModel;

@WebServlet(name = "SportServlet", urlPatterns = { "/getSports" })
public class SportServlet extends HttpServletHelper {
	private static final long serialVersionUID = 1L;

	public SportServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EventModel event = new EventModel();
		String result = new Gson().toJson(event.getSportsList());

		showResponse(response, result);
	}

}
