package blog.hyojin4588.pjt.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.hyojin4588.pjt.Utils;
import blog.hyojin4588.pjt.db.BoardCmtDAO;
import blog.hyojin4588.pjt.vo.BoardCmtVO;

@WebServlet("/coment")
public class BoardComentSER extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i_cmt = Utils.getIntParameter(request, "i_cmt");
		String strI_board = request.getParameter("i_board");
		int i_board = Utils.parseStringToInt(strI_board);
		int i_user = Utils.userInfo(request, response).getI_user();
		
		BoardCmtVO param = new BoardCmtVO();
		param.setI_board(i_board);
		param.setI_cmt(i_cmt);
		param.setI_user(i_user);
		
//		System.out.println(i_board+ "," + i_cmt);
		
		int result = 0;
		result = BoardCmtDAO.delCmt(param);
//		System.out.println(result);
		response.sendRedirect("detail?i_board=" + i_board);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_cmt = request.getParameter("i_cmt");
		int i_cmt = Utils.parseStringToInt(strI_cmt);
		String cmt = request.getParameter("cmt");
		String strI_board = request.getParameter("i_board");
		int i_board = Utils.parseStringToInt(strI_board);
		int i_user = Utils.userInfo(request, response).getI_user();
		
		int result = 0;
		
		BoardCmtVO param = new BoardCmtVO();
//		param.setI_cmt(i_cmt);
		param.setI_board(i_board);
		param.setCmt(cmt);
		param.setI_user(i_user);
		
		switch(strI_cmt) {
			case "0":
				result = BoardCmtDAO.insCmt(param);
				break;
			case "1":
				result = BoardCmtDAO.updCmt(param);
				break;
		}
		response.sendRedirect("detail?i_board=" + i_board);
	}
	
}
