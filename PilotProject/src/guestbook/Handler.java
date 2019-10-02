package guestbook;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.CommandHandler;

public class Handler implements CommandHandler {
	private static final String FORM_HOME = "/guestbook/home.jsp";
	private static final String FORM_ADD = "/guestbook/addSuccess.jsp";
	private Service service = new Service();

	public String process(HttpServletRequest req, HttpServletResponse res) {
		if (req.getMethod().equalsIgnoreCase("GET") || req.getMethod().equalsIgnoreCase("POST")) {
			return this.processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		MemberVo memberVo = new MemberVo();
		memberVo.setGuest_name(req.getParameter("guest_name"));
		memberVo.setPassword(req.getParameter("password"));
		memberVo.setMessage(req.getParameter("message"));

		if (memberVo.getGuest_name() != null && memberVo.getMessage() != null && memberVo.getPassword() != null) {
			service.insert(memberVo);
			return FORM_ADD;
		}

		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		memberVo.validate(errors);
		req.setAttribute("errors", errors);

		int currentPage = 1;
		String nullcheck = req.getParameter("page");
		if (nullcheck != null && !"".equals(nullcheck)) {
			currentPage = Integer.parseInt(req.getParameter("page"));
		}

		Viewinfo viewinfo = service.getMessageList(currentPage);

		req.setAttribute("viewinfo", viewinfo);
		return FORM_HOME;
	}
}