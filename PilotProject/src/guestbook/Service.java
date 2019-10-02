package guestbook;

import db.ConnectionProvider;
import db.JdbcUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.naming.NamingException;

public class Service {
	private static final int MESSAGE_COUNT_PER_PAGE = 3;

	public void insert(MemberVo memberVo) {
		MemberDao memberDao = new MemberDao();
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			memberDao.insert(conn, memberVo);
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

	public boolean delete(PrintVo printVo) {
		MemberDao memberDao = new MemberDao();
		Connection conn = null;
		boolean answer = false;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			answer = memberDao.delete(conn, printVo);
			conn.commit();
			return answer;
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
	public PrintVo modify(String message_id, String password) {
		MemberDao memberDao = new MemberDao();
		Connection conn = null;
		PrintVo printVo= null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			printVo = memberDao.modify(conn, message_id,  password);
			conn.commit();
			return printVo;
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
	public void update(String message_id, String message) {
		MemberDao memberDao = new MemberDao();
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			memberDao.update(conn, message_id, message);
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
	public Viewinfo getMessageList(int currentPage) {
		Connection conn = null;

		int currentPageNumber = currentPage;

		try {
			conn = ConnectionProvider.getConnection();
			MemberDao memberDao = new MemberDao();
			int totnum = memberDao.selectCount(conn);
			int pageTotalCount = 0;
			if(totnum % MESSAGE_COUNT_PER_PAGE != 0) {
				pageTotalCount=(totnum / MESSAGE_COUNT_PER_PAGE)+1;
			}else {
				pageTotalCount=(totnum / MESSAGE_COUNT_PER_PAGE);
			}
			List<PrintVo> printList = null;
			int firstRow = 0;
			int endRow = 0;
			if (totnum > 0) {
				firstRow = ((currentPageNumber - 1) * MESSAGE_COUNT_PER_PAGE) + 1;
				endRow = firstRow + MESSAGE_COUNT_PER_PAGE - 1;
				printList = memberDao.selectList(conn, firstRow, endRow);
			} else {
				currentPageNumber = 0;
				printList = Collections.emptyList();
			}
			Viewinfo viewinfo = new Viewinfo(printList, totnum, currentPageNumber, pageTotalCount, firstRow, endRow);
			return viewinfo;

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}

		return null;
	}
}