package servlet.post;

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

@WebServlet(name = "EventOptionsServlet", urlPatterns = { "/eventSettings" })
public class EventOptionsServlet extends HttpServletHelper {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		JSONObject result = new JSONObject();

		result.put("success", new EventModel().resolveEvent(gson.fromJson(getBody(request), EventBean.class)));

		response.setContentType("application/json");
		showResponse(response, result);
	}

}
