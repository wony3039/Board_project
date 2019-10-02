package login;

import db.JdbcUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDao {

	public void change(Connection conn, LoginVo loginVo, String newpassword) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("UPDATE member SET password = ?  WHERE member_id = ? ")) {
			pstmt.setString(1, newpassword);
			pstmt.setString(2, loginVo.getMember_id());
			pstmt.executeUpdate();
		}

	}

	public LoginVo login(Connection conn, MemberVo memberVo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String tempmember_id = null;
		String temppassword = null;
		LoginVo loginVo = null;
		try {
			String sql = "SELECT * ";
			sql = sql + " FROM member ";
			sql = sql + " WHERE member_id = ? ";
			sql = sql + " AND password = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberVo.getMember_id());
			pstmt.setString(2, memberVo.getPassword());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tempmember_id = rs.getString("member_id");
				temppassword = rs.getString("password");
				if (tempmember_id != null && temppassword != null) {
					if (memberVo.getMember_id().equals(tempmember_id) && memberVo.getPassword().equals(temppassword)) {
						loginVo = new LoginVo(rs.getString("member_id"), rs.getString("name"), rs.getString("password"),rs.getString("answer"),
								rs.getString("regdate"));
					}
				}
			}

			return loginVo;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
		}
		return loginVo;
	}

	public LoginVo find(Connection conn, String member_id,String answer) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LoginVo loginVo = null;
		String temp=null;
		try {
			String sql = "SELECT * ";
			sql = sql + " FROM member ";
			sql = sql + " WHERE member_id = ? ";
			sql = sql + " AND answer = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			pstmt.setString(2, answer);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				temp=rs.getString("member_id");
				if(temp!=null||!"".equals(temp)) {
					loginVo = new LoginVo(rs.getString("member_id"), rs.getString("name"), rs.getString("password"),
							rs.getString("answer"), rs.getString("regdate"));
				}	
			}
			return loginVo;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
		}
		return loginVo;

	}
}