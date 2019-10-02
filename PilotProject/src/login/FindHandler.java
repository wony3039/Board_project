package login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.dbcp.pool2.BaseObject;

import mvc.CommandHandler;

public class FindHandler implements CommandHandler {

	@Override
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
		String member_id = req.getParameter("member_id");
		String answer = req.getParameter("answer");
		Map<String, Boolean> errors = new HashMap<>();

		if (member_id == null || "".equals(member_id) || answer == null || "".equals(answer)) {// 넘어온 값이 없을경우
			if (member_id == null || "".equals(member_id)) {
				errors.put("member_id", Boolean.TRUE);
			}
			if (answer == null || "".equals(answer)) {
				errors.put("answer", Boolean.TRUE);
			}
			req.setAttribute("errors", errors);
			return "/login/passwordFind.jsp";

		} else {

			LoginVo loginVo = service.find(member_id, answer);

			if (loginVo==null) {
				errors.put("noMatch", Boolean.TRUE);
				req.setAttribute("errors", errors);
				return "/login/passwordFind.jsp";
			}else {
				req.setAttribute("check", Boolean.TRUE);
				req.setAttribute("gurantee", loginVo.getPassword());
				return "/login/passwordFind.jsp";

			}
		}
	}
}
