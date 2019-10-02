
package board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

//import guestbook.MemberVo;
import mvc.CommandHandler;

public class deleteHandler implements CommandHandler {
	private static final String FORM_HOME = "board.do";
	private Service service = new Service();

	public String process(HttpServletRequest req, HttpServletResponse res) { // 
		if (req.getMethod().equalsIgnoreCase("GET") || req.getMethod().equalsIgnoreCase("POST")) {
			return this.processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) { // 
		boardVO vo= new boardVO();
		vo.setArticle_no(Integer.parseInt(req.getParameter("article_no")));// 확인바람
		vo.setTitle(req.getParameter("title"));
		vo.setContent(req.getParameter("content"));
		
		String command = req.getParameter("command");
		if(command != null || "".equals(command)) { // 체크 
			String password = JOptionPane.showInputDialog(null, "게시글을 삭제하시려면 번호를 입력하세요.","", JOptionPane.OK_CANCEL_OPTION);
			vo.setArticle_pw(password);
			if(password != null || "".equals(password)) { //페스워드가 있는지 체크
				boolean check = service.passwordCheck(vo);
				if(check == true) { // 비밀번호가 맞을 시
					service.deleteShow(vo);
					return FORM_HOME;
				}else{ //비밀번호가 틀릴 시
					return FORM_HOME;
				}
			}else{
				return FORM_HOME;
			}
		}
		return null;
		

	}
}