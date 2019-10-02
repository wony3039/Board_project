
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
		vo.setArticle_no(Integer.parseInt(req.getParameter("article_no")));// Ȯ�ιٶ�
		vo.setTitle(req.getParameter("title"));
		vo.setContent(req.getParameter("content"));
		
		String command = req.getParameter("command");
		if(command != null || "".equals(command)) { // üũ 
			String password = JOptionPane.showInputDialog(null, "�Խñ��� �����Ͻ÷��� ��ȣ�� �Է��ϼ���.","", JOptionPane.OK_CANCEL_OPTION);
			vo.setArticle_pw(password);
			if(password != null || "".equals(password)) { //�佺���尡 �ִ��� üũ
				boolean check = service.passwordCheck(vo);
				if(check == true) { // ��й�ȣ�� ���� ��
					service.deleteShow(vo);
					return FORM_HOME;
				}else{ //��й�ȣ�� Ʋ�� ��
					return FORM_HOME;
				}
			}else{
				return FORM_HOME;
			}
		}
		return null;
		

	}
}