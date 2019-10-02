package login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.CommandHandler;

public class ChangeHandler implements CommandHandler {

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
		HttpSession session = req.getSession();
		Service service = new Service();
		LoginVo loginVo = (LoginVo) session.getAttribute("loginVo");

		String inputPassword = req.getParameter("inputpassword");
		String newPassword = req.getParameter("newpassword");

		Map<String, Boolean> errors = new HashMap<>();
		loginVo.validate(errors, inputPassword);// ���Ʒ��� �ٲ㵵 ����� ����
		req.setAttribute("errors", errors);// �ؽ����� call by reference�̱� ������ ������ ������� �ʴ´�.

		if (!errors.isEmpty()) {
			return "/login/passwordChange.jsp";
		} else {
			service.change(loginVo, newPassword);
			return "/login/main.jsp";
		}

	}

}
