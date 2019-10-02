package board;

import db.ConnectionProvider;
import db.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.naming.NamingException;

public class Service {
	private static final int MESSAGE_COUNT_PER_PAGE = 10;
//---------------------------------------------------------------------
	public void insert(boardVO memberVo) { // Add단
		BoardDAO dao = new BoardDAO();
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			dao.insert(conn, memberVo); //게시글 추가
			conn.commit();
		} catch (NamingException var9) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(var9);
		} catch (SQLException var10) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(var10);
		} finally {
			JdbcUtil.close(conn);
		}

	}
//---------------------------------------------------------------------	
	public void update(boardVO memberVo) { // update단
		BoardDAO dao = new BoardDAO();
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			dao.updateContent(conn, memberVo); //게시글 추가
			conn.commit();
		} catch (NamingException var9) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(var9);
		} catch (SQLException var10) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(var10);
		} finally {
			JdbcUtil.close(conn);
		}

	}
	
//---------------------------------------------------------------------	
		public List updateShow(boardVO memberVo) { // update단
			BoardDAO dao = new BoardDAO();
			Connection conn = null;
			List show = null;
			try {
				conn = ConnectionProvider.getConnection();
				conn.setAutoCommit(false);
				show = dao.updateLook(conn, memberVo); //게시글 추가
				conn.commit();
			} catch (NamingException var9) {
				JdbcUtil.rollback(conn);
				throw new RuntimeException(var9);
			} catch (SQLException var10) {
				JdbcUtil.rollback(conn);
				throw new RuntimeException(var10);
			} finally {
				JdbcUtil.close(conn);
			}
			return show;

		}
//---------------------------------------------------------------------	
	public List look(boardVO memberVo) { // look단
		BoardDAO dao = new BoardDAO();
		Connection conn = null;
		List look = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			look = dao.look(conn, memberVo); //글보기
			conn.commit();
		} catch (NamingException var9) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(var9);
		} catch (SQLException var10) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(var10);
		} finally {
			JdbcUtil.close(conn);
		}
		return look;
	}
//---------------------------------------------------------------------	
	public Viewinfo getMessageList(int currentPage) {
		
		Connection conn = null;

		int currentPageNumber = currentPage;

		try {
			conn = ConnectionProvider.getConnection();
			BoardDAO dao = new BoardDAO();
			int totnum = dao.selectCount(conn); // 
			int pageTotalCount = 0;
			if(totnum % MESSAGE_COUNT_PER_PAGE != 0) {
				pageTotalCount=(totnum / MESSAGE_COUNT_PER_PAGE)+1;
			}else {
				pageTotalCount=(totnum / MESSAGE_COUNT_PER_PAGE);
			}
			List<boardVO> printList = null;
			int firstRow = 0;
			int endRow = 0;
			if (totnum > 0) {
				firstRow = ((currentPageNumber - 1) * MESSAGE_COUNT_PER_PAGE) + 1;
				endRow = firstRow + MESSAGE_COUNT_PER_PAGE - 1;
				printList = dao.selectList(conn, firstRow, endRow);
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
//---------------------------------------------------------------------	
	public void deleteShow(boardVO memberVo) { // update단
		BoardDAO dao = new BoardDAO();
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			dao.deleteArticle(conn, memberVo); //게시글 추가
			conn.commit();
		} catch (NamingException var9) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(var9);
		} catch (SQLException var10) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(var10);
		} finally {
			JdbcUtil.close(conn);
		}
	}
//---------------------------------------------------------------------
	public Boolean passwordCheck(boardVO memberVo) { // update단
		BoardDAO dao = new BoardDAO();
		Connection conn = null;
		Boolean check = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			check = dao.passwordCheck(conn, memberVo); // 게시글 추가
			conn.commit();
		} catch (NamingException var9) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(var9);
		} catch (SQLException var10) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(var10);
		} finally {
			JdbcUtil.close(conn);
		}
		System.out.println(check);
		return check;
	}
}