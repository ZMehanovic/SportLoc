package helper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletHelper extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public String getBody(HttpServletRequest request) throws IOException {
    request.setCharacterEncoding(StandardCharsets.UTF_8.name());
    return request.getReader().lines().collect(Collectors.joining(""));
  }

  public void showResponse(HttpServletResponse response, Object jsonToShow) {

    try {
      response.setContentType("application/json");
      ServletOutputStream out = response.getOutputStream();

      out.write(jsonToShow.toString().getBytes());
      out.flush();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
