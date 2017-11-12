package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import controller.UserController;
import helper.HttpServletHelper;

@WebServlet(name = "ResetPasswordServlet", urlPatterns = { "/resetPassword" })
public class ResetPasswordServlet extends HttpServletHelper {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONObject result = new JSONObject();
		UserController user = new UserController();

		result.put("reset", user.resetPassword(req.getParameter("email")));

		resp.setContentType("application/json");
		ServletOutputStream out = resp.getOutputStream();
		out.write(result.toString().getBytes());
		out.flush();
		out.close();
	}

}