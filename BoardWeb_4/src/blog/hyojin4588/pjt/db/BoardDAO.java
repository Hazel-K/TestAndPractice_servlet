package blog.hyojin4588.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.hyojin4588.pjt.vo.BoardVO;

public class BoardDAO {
	public static int insDetail(BoardVO param) {
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
	
	public static int modDetail(BoardVO param) {
		String sql = " UPDATE t_board4 SET (title, ctnt, i_user) = (SELECT ?, ?, ? FROM DUAL) WHERE i_board = ? ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_user());
				ps.setInt(4, param.getI_board());
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
	
	public static BoardVO selDetail(BoardVO param) {
		BoardVO vo = new BoardVO();
		String sql = " SELECT a.title, a.ctnt, a.r_dt, a.i_user, b.u_nm FROM t_board4 a INNER JOIN t_user b ON a.i_user = b.i_user WHERE i_board = ? ";
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				int result = 0;
				if(rs.next()) {
					String title = rs.getNString("title");
					String ctnt = rs.getNString("ctnt");
					String r_dt = rs.getNString("r_dt");
					String u_nm = rs.getNString("u_nm");
					int i_user = rs.getInt("i_user");
					
					vo.setTitle(title);
					vo.setCtnt(ctnt);
					vo.setR_dt(r_dt);
					vo.setU_nm(u_nm);
					vo.setI_user(i_user);
					vo.setI_board(param.getI_board());
					result++;
				}
				return result;
			}
		});
		return vo;
	}
	
	public static int delDetail(BoardVO param) {
		String sql = " DELETE FROM t_board4 WHERE i_board = ? ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
			}
		});
	}

}
