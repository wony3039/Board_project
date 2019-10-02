package board;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane; // 다운

import db.JdbcUtil;

public class BoardDAO {
	public int selectCount(Connection conn) { // 갯수 파악
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totnum = 0;

		try {
			String sql = "SELECT COUNT(*)   ";
			sql = sql + " FROM article a, article_content ac";
			sql = sql + " WHERE a.article_no = ac.article_no";
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
	
//--------------------------------------------------------------------------------------
	public List<boardVO> selectList(Connection conn, int firstRow, int endRow) { // 셋팅
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<boardVO> printList = new ArrayList<boardVO>();

		try {
			String sql = "select * ";
			sql = sql + " from (select rownum rnum, a.*, ac.content from article a, article_content ac WHERE ac.article_no = a.article_no) ";
			sql = sql + "where rnum >= ? and rnum <= ? ";
			sql = sql + "order by article_no desc ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, firstRow);
			pstmt.setInt(2, endRow);
			System.out.println("sql"+sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				printList.add(new boardVO(
						rs.getInt("article_no"),
						rs.getString("writer_id"),
						rs.getString("writer_name"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getDate("regdate"),
						rs.getDate("moddate"),
						rs.getInt("read_cnt")));
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
//--------------------------------------------------------------------------------------
	public List listMembers(Connection con) { // 게시판 글 조회
		List list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT a.article_no, title, writer_name, read_cnt" +  
						   " FROM article a, article_content ac" +
						   " WHERE a.article_no = ac.article_no"+
						   " ORDER BY article_no DESC";  
			
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int _no = rs.getInt("article_no");
				String _name= rs.getString("writer_name");
				String _title= rs.getString("title");
				int _cnt = rs.getInt("read_cnt");
							
				boardVO vo = new boardVO();
				vo.setArticle_no(_no);
				vo.setWriter_name(_name);
				vo.setTitle(_title);
				vo.setRead_cnt(_cnt);
				
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return list;
	}
//------------------------------------------------------------------------------------------
	public List updateContent(Connection con, boardVO vo) { // 게시판 글 수정
		List list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int no = vo.getArticle_no();
		String title = vo.getTitle();
		String content = vo.getContent();
		
		try {
				String query = "UPDATE article" + " SET title = '" + title + "'" + " ,moddate = sysdate"
						+ " WHERE article_no ='" + no + "'";

				String query2 = " UPDATE article_content" + " SET content = '" + content + "'" + " WHERE article_no ='"
						+ no + "'";

				System.out.println("prepareStatement: " + query);
				System.out.println("prepareStatement: " + query2);
				pstmt = con.prepareStatement(query);
				pstmt.executeUpdate();

				pstmt = con.prepareStatement(query2);
				pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return list;
	}
//----------------------------------------------------------------------
	public List look(Connection con,  boardVO vo) { // 게시판 글 조회
		List list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int no = vo.getArticle_no();
		try {
			String query = "SELECT a.article_no, writer_name, title, content" +  
						   " FROM article a, article_content ac" +
						   " WHERE a.article_no = ac.article_no" +
						   " AND a.article_no = '" + no + "'";
			
			String query2 = "UPDATE article SET read_cnt = read_cnt+1 WHERE article_no = '" + no +"'";
			
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int _no = rs.getInt("article_no");
				String _name= rs.getString("writer_name");
				String _title= rs.getString("title");
				String _cnt = rs.getString("content");
				
				System.out.println("no: "+_no);
				System.out.println("name: "+_name);
				System.out.println("title: "+_title);
				System.out.println("content: "+_cnt);
				
				vo.setArticle_no(_no);
				vo.setWriter_name(_name);
				vo.setTitle(_title);
				vo.setContent(_cnt);
				
				list.add(vo);
			}
			System.out.println("prepareStatement: " + query2);
			
			pstmt = con.prepareStatement(query2);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return list;
	}


//----------------------------------------------------------------------	
	public List updateLook(Connection con, boardVO vo) { // 수정글 조회
		List list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int no = vo.getArticle_no();
		try {
			String query = "SELECT a.article_no, title, content" +  
						   " FROM article a, article_content ac" +
						   " WHERE a.article_no = ac.article_no" +
						   " AND a.article_no = '" + no + "'";
						    
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int _no = rs.getInt("article_no");
				String _content= rs.getString("content");
				String _title= rs.getString("title");
				
				System.out.println(_no);
				System.out.println(_content);
				System.out.println(_title);
				
				vo.setArticle_no(_no);
				vo.setContent(_content);
				vo.setTitle(_title);
				
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return list;
	}
	
	
	
	
//--------------------------------------------------------------------------------------
	public void insert(Connection con, boardVO vo) { // 게시글 추가
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			int article_no = vo.getArticle_no();
			String article_pw = vo.getArticle_pw();
			String title = vo.getTitle();
			String content = vo.getContent();
			String writer_id = vo.getWriter_id();
			String writer_name = vo.getWriter_name();
			Date regdate = vo.getRegdate();
			Date moddate = vo.getModdate();

			String query = " insert into article(article_no,article_pw,writer_id, writer_name, title, regdate, moddate, read_cnt)"
					+ " values((SELECT NVL(MAX(article_no),0)+1 FROM article),?,1,?,?,sysdate,sysdate,0)";// 변경

			System.out.println("prepareStatememt : " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, article_pw);
			pstmt.setString(2, writer_name);
			pstmt.setString(3, title);
			pstmt.executeUpdate();
			pstmt.close();

			String query2 = "insert into article_content(article_no,content)" + " values((SELECT NVL(MAX(article_no),0)+1 FROM article_content),?)";

			System.out.println("prepareStatememt : " + query2);
			pstmt = con.prepareStatement(query2);
			pstmt.setString(1, content);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		System.out.println("완성");
	}
	
	
//--------------------------------------------------------------------------------------

	public List deleteArticle(Connection con, boardVO vo) { // 게시판 글 수정
		
		List list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int no = vo.getArticle_no();
		String title = vo.getTitle();
		String content = vo.getContent();
		String pw = vo.getArticle_pw();
		try {
			String sql = "select article_pw from article where article_no='" + no +"'";//
			pstmt = con.prepareStatement(sql);//
			rs = pstmt.executeQuery();//
			while(rs.next()) {
				String article_pw = rs.getString("article_pw");//
				
				vo.setArticle_pw(article_pw);
			}
			String article_pw = vo.getArticle_pw();
			
			System.out.println("a_pw: "+article_pw);
			System.out.println("pw: "+ pw);
			if(pw.equals(article_pw)) {
				String query = "delete from article where TRIM(article_no) = ?";
				String query2 = "delete from article_content where TRIM(article_no) = ?";

				System.out.println("prepareStatement: " + query);
				System.out.println("prepareStatement: " + query2);
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, no);
				pstmt.executeUpdate();

				pstmt = con.prepareStatement(query2);
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
			}else {
				JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return list;
	}
//--------------------------------------------------------------------------------------
		
	public Boolean passwordCheck(Connection con, boardVO vo) { // 수정글 조회
		List list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int no = vo.getArticle_no();
		String pw = vo.getArticle_pw();
		try {
			String sql = "select article_pw from article where article_no='" + no + "'"; // 비밀번호 체크
			pstmt = con.prepareStatement(sql); //
			rs = pstmt.executeQuery(); //
			while (rs.next()) {
				String article_pw = rs.getString("article_pw");// 비밀번호 받아오기
				vo.setArticle_pw(article_pw);
			}

			String article_pw = vo.getArticle_pw();

			System.out.println("a_pw: " + article_pw);
			System.out.println("pw: " + pw);

			if (pw.equals(article_pw)) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다.");
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return null;
	}
}