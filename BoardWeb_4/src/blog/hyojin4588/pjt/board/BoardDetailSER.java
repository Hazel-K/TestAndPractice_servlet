package blog.hyojin4588.pjt.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.hyojin4588.pjt.ViewResolver;
import blog.hyojin4588.pjt.db.BoardDAO;
import blog.hyojin4588.pjt.vo.BoardVO;

@WebServlet("/detail")
public class BoardDetailSER extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_board = request.getParameter("i_board");
		int i_board = Integer.parseInt(strI_board);
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		BoardVO vo = BoardDAO.selDetail(param);
		request.setAttribute("data", vo);
//		System.out.println(vo.getI_user());
//		UserVO vo2 = (UserVO)request.getSession().getAttribute(Const.LOGIN_USER);
//		System.out.println(vo2.getI_user());
		ViewResolver.forward("board/BoardDetail", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
	}

}
