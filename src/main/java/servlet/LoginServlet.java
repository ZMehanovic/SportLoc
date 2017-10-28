package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import controller.UserController;

//import com.heroku.sdk.jdbc.DatabaseUrl;

@WebServlet(name = "MyServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONObject result=new JSONObject();
		Map<String, String[]> params= req.getParameterMap();
		UserController user=new UserController();
		result.put("loginSuccessful", user.checkLoginData(params.get("username")[0], params.get("password")[0]));

		resp.setContentType("application/json");
		ServletOutputStream out = resp.getOutputStream();
		out.write(result.toString().getBytes());
		out.flush();
		out.close();
	}

}