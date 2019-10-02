
package board;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane; // �ٿ�

//import guestbook.MemberVo;
import mvc.CommandHandler;

public class UpdateHandler implements CommandHandler {
	private static final String FORM_HOME = "board.do";
	private static final String FORM_UPDATE = "/Board/update.jsp";
	private static final String FORM_DELETE = "/Board/password.jsp";
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
		if(command != null || "".equals(command)) {
			String password = JOptionPane.showInputDialog(null, "�Խñ��� �����Ͻ÷��� ��ȣ�� �Է��ϼ���.", "");
			vo.setArticle_pw(password);
			if(password != null || "".equals(password)) {
				boolean check = service.passwordCheck(vo);
				if(check == true) {
					List viewinfo = service.updateShow(vo);
					
					req.setAttribute("viewinfo", viewinfo);
					
					return FORM_UPDATE;
				}else{
					return FORM_HOME;
				}
			}else {
				return FORM_HOME;
			}
		}
		
		System.out.println(req.getParameter("content"));
		if (vo.getContent() != null ) {
			service.update(vo);
			
			return FORM_HOME;
		}
		return null;
	}
}