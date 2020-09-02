package blog.hyojin4588.project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class MainServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 서블릿을 몰아줬을 때의 장점
	// 관리가 편하다

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proc(request, response, RequestMethod.GET);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proc(request, response, RequestMethod.POST);
	}
	
	private void proc(HttpServletRequest request, HttpServletResponse response, int method) throws ServletException, IOException {
		System.out.println("uri:" + request.getRequestURI() + ", method=" + method);
	}

}