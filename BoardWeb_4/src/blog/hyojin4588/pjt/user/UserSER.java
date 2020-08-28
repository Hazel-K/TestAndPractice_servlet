package blog.hyojin4588.pjt.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.hyojin4588.pjt.Utils;
import blog.hyojin4588.pjt.ViewResolver;
import blog.hyojin4588.pjt.db.UserDAO;
import blog.hyojin4588.pjt.vo.UserVO;

@WebServlet("/profile")
public class UserSER extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 프로필 화면 (나의 프로필 이미지, 이미지 변경 가능한 화면), 업로드는 무조건 post로 동작
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i_user = Utils.userInfo(request, response).getI_user();
		UserVO param = UserDAO.selUser(i_user);
		request.setAttribute("data", param);
		ViewResolver.forward("user/Profile", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
	}

}
