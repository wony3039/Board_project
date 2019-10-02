package board;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.LoginVo;
import login.MemberVo;
import mvc.CommandHandler;

public class Handler implements CommandHandler {
	private static final String FORM_HOME = "/Board/home.jsp";
	private static final String FORM_HOME2 = "/login/main.jsp";
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
		LoginVo loginVo = new LoginVo();
		
		HttpSession session = req.getSession();
		LoginVo login = (LoginVo) session.getAttribute("loginVo");
		System.out.println(login);
		
		if (login != null) {
			int currentPage = 1;
			String nullcheck = req.getParameter("page");
			if (nullcheck != null && !"".equals(nullcheck)) {
				currentPage = Integer.parseInt(req.getParameter("page"));
			}

			Viewinfo viewinfo = service.getMessageList(currentPage);

			req.setAttribute("viewinfo", viewinfo);

			return FORM_HOME;
		}
		else {
			return FORM_HOME2;
		}
	}
}