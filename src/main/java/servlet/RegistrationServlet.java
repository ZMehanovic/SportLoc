package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.UserBean;
import controller.UserController;


@WebServlet(name = "RegistrationServlet", urlPatterns = { "/register" })
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Gson gson= new Gson();
		String jsonString=gson.toJson(new UserBean(0, 20, "test", "test2", "testtest", "corn1994@hotmail.com", "M", "desc"));
//		JsonObject json=new JsonObject();
//		gson.fromJson(jsonString, UserBean.class);
		(new UserController()).registerUser(gson.fromJson(jsonString, UserBean.class));
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("application/json");
		ServletOutputStream out = response.getOutputStream();
		out.write(jsonString.getBytes());
		out.flush();
		out.close();
	}



}
