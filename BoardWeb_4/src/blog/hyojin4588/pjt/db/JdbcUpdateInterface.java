package blog.hyojin4588.pjt.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface JdbcUpdateInterface {
	
	int update(PreparedStatement ps) throws SQLException;

}
