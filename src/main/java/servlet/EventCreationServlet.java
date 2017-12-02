package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

import beans.EventBean;
import helper.HttpServletHelper;
import model.EventModel;

@WebServlet(name = "EventCreationServlet", urlPatterns = { "/createEvent" })
public class EventCreationServlet extends HttpServletHelper {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		JSONObject result = new JSONObject();

		String responseMessage = new EventModel().createEvent(gson.fromJson(getBody(request), EventBean.class));
		boolean success = responseMessage == null;
		result.put("success", success);
		result.put("message", success ? "Event has been created successfully." : responseMessage);

		response.setContentType("application/json");
		showResponse(response, result);
	}

}
