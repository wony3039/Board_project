package guestbook;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.CommandHandler;

public class ModifyHandler implements CommandHandler {

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
		Service service = new Service();
		String message_id = req.getParameter("message_id");
		String password = req.getParameter("password");
		String command = req.getParameter("command");
		Map<String, Boolean> errors = new HashMap<>();
		
		if ("modifysuccess".equals(command)) {
			String message_id2 = req.getParameter("printVo.message_id");
			String message = req.getParameter("message");
			service.update(message_id2,message);
			return "/guestbook/modifysuccess.jsp";
		}else {
			if (password == null || "".equals(password)) {// 넘어온 값이 없을경우
					errors.put("password", Boolean.TRUE);
				req.setAttribute("errors", errors);
				return "/guestbook/modify.jsp";

			} else {
				PrintVo printVo = service.modify(message_id, password);

				if (printVo == null) {
					errors.put("noMatch", Boolean.TRUE);
					req.setAttribute("errors", errors);
					return "/guestbook/modify.jsp";
				} else {
					req.setAttribute("printVo", printVo);
					return "/guestbook/modifyprocess.jsp";

				}
			}
		}
	}
}