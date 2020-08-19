package blog.hyojin4588.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.hyojin4588.pjt.vo.BoardVO;

public class BoardDAO {
	public static int insBoard(BoardVO param) {
		String sql = " INSERT INTO t_board4(i_board, title, ctnt, i_user) SELECT seq_board.nextval, ?, ?, ? FROM DUAL ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_user());
			}
		});
	}
	
	public static List<BoardVO> selBoard() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		String sql = " SELECT a.i_board, a.title, a.hits, a.i_user, a.r_dt, b.u_nm FROM t_board4 a INNER JOIN t_user b ON a.i_user = b.i_user ORDER BY i_board DESC ";
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				int total = 0;
				while(rs.next()) {
					int i_board = rs.getInt("i_board");
					int i_user = rs.getInt("i_user");
					String title = rs.getNString("title");
					int hits = rs.getInt("hits");
					String r_dt = rs.getNString("r_dt");
					String u_nm = rs.getNString("u_nm");
					
					BoardVO vo = new BoardVO();
					vo.setI_board(i_board);
					vo.setI_user(i_user);
					vo.setTitle(title);
					vo.setHits(hits);
					vo.setR_dt(r_dt);
					vo.setU_nm(u_nm);
					
					list.add(vo);
					total++;
				}
				return total;
			}
		});
		return list;
	}

}