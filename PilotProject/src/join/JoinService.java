package join;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import javax.naming.NamingException;
import db.ConnectionProvider;
import db.JdbcUtil;

public class JoinService {
	private MemberDao memberDao = new MemberDao();

	public void join(JoinRequest joinReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection(); 
			conn.setAutoCommit(false);		//오토커밋을 꺼줘야함

			Member member = memberDao.selectById(conn, joinReq.getMember_id());
			if (member != null) {	// 기존 계정이 있는지 확인
				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();
			}

			memberDao.insert(conn, new Member(joinReq.getMember_id(), joinReq.getName(), joinReq.getPassword(), joinReq.getAnswer(),new Date()));
			conn.commit();
		} catch (NamingException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
