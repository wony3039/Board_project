package login;

import db.ConnectionProvider;
import db.JdbcUtil;
import oracle.net.aso.a;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;

public class Service {

	public LoginVo find(String member_id,String answer) {
		MemberDao memberDao = new MemberDao();
		Connection conn = null;
		LoginVo loginVo = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			loginVo = memberDao.find(conn, member_id,answer);
			conn.commit();
			return loginVo;
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
	public LoginVo login(MemberVo memberVo, LoginVo loginVo) {
		MemberDao memberDao = new MemberDao();
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			loginVo = memberDao.login(conn, memberVo);
			conn.commit();
			return loginVo;
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
	public void change(LoginVo loginVo, String newpassword) {
		MemberDao memberDao = new MemberDao();
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			memberDao.change(conn, loginVo, newpassword);
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