package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

import beans.UserBean;
import controller.UserController;
import helper.HttpServletHelper;

@WebServlet(name = "RegistrationServlet", urlPatterns = { "/register" })
public class RegistrationServlet extends HttpServletHelper {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		JSONObject result = new JSONObject();
		
		result.put("registrationSuccessful",
				(new UserController()).registerUser(gson.fromJson(getBody(request), UserBean.class)));
		
		response.setContentType("application/json");
		ServletOutputStream out = response.getOutputStream();
		out.write(result.toString().getBytes());
		out.flush();
		out.close();
	}

}
