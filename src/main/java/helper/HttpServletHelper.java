package helper;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class HttpServletHelper extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static String getBody(HttpServletRequest request) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();

		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("");
		}
		reader.close();

		return sb.toString();
	}
}
