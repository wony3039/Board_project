package board;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.LoginVo;
//import guestbook.MemberVo;
import mvc.CommandHandler;

public class AddHandler implements CommandHandler {
	private static final String FORM_HOME = "board.do";
	private static final String FORM_ADD = "/Board/add.jsp";
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
		LoginVo loginVo = new LoginVo();
		
		HttpSession session = req.getSession();
		LoginVo login = (LoginVo) session.getAttribute("loginVo");
		
		vo.setTitle(req.getParameter("title"));
		vo.setContent(req.getParameter("content"));
		vo.setArticle_pw(req.getParameter("password"));
		vo.setWriter_name(login.getName());
		
		if (vo.getTitle() != null && vo.getContent() != null ) {
			service.insert(vo);
			
			int currentPage = 1;
			String nullcheck = req.getParameter("page");
			if (nullcheck != null && !"".equals(nullcheck)) {
				currentPage = Integer.parseInt(req.getParameter("page"));
			}

			Viewinfo viewinfo = service.getMessageList(currentPage);

			req.setAttribute("viewinfo", viewinfo);
			
			return FORM_HOME;
		}
		
		return FORM_ADD;
	}
}