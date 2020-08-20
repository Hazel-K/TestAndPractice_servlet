package blog.hyojin4588.pjt.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.hyojin4588.pjt.Const;
import blog.hyojin4588.pjt.ViewResolver;
import blog.hyojin4588.pjt.db.BoardDAO;
import blog.hyojin4588.pjt.vo.BoardVO;
import blog.hyojin4588.pjt.vo.UserVO;

@WebServlet("/regmod")
public class RegModSER extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession(); // 세션을 가져온다
		if (null == hs.getAttribute(Const.LOGIN_USER)) {
			response.sendRedirect("login");
			return;
		}
		ViewResolver.forwardLoginChk("board/RegMod", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		if (null == hs.getAttribute(Const.LOGIN_USER)) {
			response.sendRedirect("login");
			return;
		}
		UserVO vo = (UserVO)hs.getAttribute(Const.LOGIN_USER);
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		int i_user = vo.getI_user();
		
		BoardVO param = new BoardVO();
		param.setTitle(title);
		param.setCtnt(ctnt);
		param.setI_user(i_user);
		
		int result = BoardDAO.insBoard(param);
		if (result != 1) {
			request.setAttribute("errMsg", "글 등록에 실패했습니다.");
			doGet(request, response);
			return;
		}
		response.sendRedirect("boardlist");
	}

}