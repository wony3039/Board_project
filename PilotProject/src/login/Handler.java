package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.CommandHandler;

public class Handler implements CommandHandler {

	private Service service = new Service();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		if (req.getMethod().equalsIgnoreCase("GET") || req.getMethod().equalsIgnoreCase("POST")) {
			return this.processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		MemberVo memberVo = new MemberVo();
		LoginVo loginVo = new LoginVo();

		memberVo.setMember_id(request.getParameter("member_id"));
		memberVo.setPassword(request.getParameter("password"));
		String command = request.getParameter("command");
		loginVo = service.login(memberVo, loginVo);

		HttpSession session = request.getSession();

		if ("logout".equals(command)) {
			session.invalidate();
			return "/login/login.jsp";
		}

		if (loginVo != null) {
			session.setAttribute("loginVo", loginVo);
			return "/login/success.jsp";
		} else {
			return "/login/login.jsp?errors=noMatch";
		}
	}
}