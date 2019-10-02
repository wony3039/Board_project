package guestbook;

import db.JdbcUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
	public List<PrintVo> selectList(Connection conn, int firstRow, int endRow) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PrintVo> printList = new ArrayList<PrintVo>();

		try {
			String sql = "select * ";
			sql = sql + " from (select rownum rnum, a.* from guestbook_message a) ";
			sql = sql + "where rnum >= ? and rnum <= ? ";
			sql = sql + "order by message_id desc ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, firstRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				printList.add(new PrintVo(rs.getString("message_id"), rs.getString("guest_name"),
						rs.getString("password"), rs.getString("message")));
			}
			return printList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return printList;
	}

	public int selectCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totnum = 0;

		try {
			String sql = "SELECT COUNT(*)   ";
			sql = sql + " FROM guestbook_message";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				totnum = rs.getInt(1);
			}

			return totnum;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return totnum;
	}

	public void insert(Connection conn, MemberVo memberVo) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"INSERT INTO guestbook_message(message_id,guest_name,password,message) VALUES ((SELECT NVL(MAX(message_id),0)+1 FROM guestbook_message) ,?,?,?)")) {
			pstmt.setString(1, memberVo.getGuest_name());
			pstmt.setString(2, memberVo.getPassword());
			pstmt.setString(3, memberVo.getMessage());
			pstmt.executeUpdate();
		}
	}
	public boolean delete(Connection conn, PrintVo printVo) throws SQLException {
		if(deleteCheck(conn, printVo)) {
			try (PreparedStatement pstmt = conn.prepareStatement(
					"DELETE FROM guestbook_message WHERE message_id = ? ")) {
				pstmt.setString(1, printVo.getMessage_id());
				pstmt.executeUpdate();
				return true;
			}
		}
		return false;
	}

	public PrintVo modify(Connection conn, String message_id,String password)  {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PrintVo printVo = null;
		String temp=null;
		try {
			String sql = "SELECT *  FROM guestbook_message WHERE message_id = ? AND password = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, message_id);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				temp=rs.getString("message_id");
				if(temp!=null||!"".equals(temp)) {
					printVo = new PrintVo(rs.getString("message_id"), rs.getString("guest_name"), rs.getString("password"),
							rs.getString("message"));
				}	
			}
			return printVo;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
		}
		return printVo;

	}
	public void update(Connection conn, String message_id,String message) throws SQLException  {
		try (PreparedStatement pstmt = conn.prepareStatement("UPDATE guestbook_message SET message = ? WHERE message_id = ? ")){	
			pstmt.setString(1, message);
			pstmt.setString(2, message_id);
			pstmt.executeUpdate();
		} 
	}

	public boolean deleteCheck(Connection conn, PrintVo printVo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String temppassword=null;
		try {
			String sql = "SELECT password ";
			sql = sql + " FROM guestbook_message ";
			sql = sql + " WHERE message_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, printVo.getMessage_id());
			rs = pstmt.executeQuery();
			System.out.println("printVo.getPassword():" + printVo.getPassword());
			while (rs.next()) {
				temppassword = rs.getString("password");
				System.out.println("temppassword:" + temppassword);
			}
			if (temppassword != null) {
				if (temppassword.equals(printVo.getPassword())) {
					return true;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
		}
		return false;
	}
}