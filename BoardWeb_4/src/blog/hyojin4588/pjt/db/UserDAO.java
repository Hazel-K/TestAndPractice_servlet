package blog.hyojin4588.pjt.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import blog.hyojin4588.pjt.vo.UserVO;

public class UserDAO {

	public static int insUser(UserVO param) {
		String sql = "INSERT INTO t_user"
				+ "(i_user, u_id, u_pw, u_nm, u_email) "
				+ "VALUES "
				+ "(seq_user.nextval, ?, ?, ?, ?) ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public int update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getUser_id());
				ps.setNString(2, param.getUser_pw());
				ps.setNString(3, param.getUser_nm());
				ps.setNString(4, param.getEmail());
				return ps.executeUpdate();
			}
		});
	};
}
