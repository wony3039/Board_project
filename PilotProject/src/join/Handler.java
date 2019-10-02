package join;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.CommandHandler;

public class Handler implements CommandHandler {

	private static final String FORM_VIEW = "/join/joinForm.jsp";
	private JoinService joinService = new JoinService();

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
		JoinRequest joinReq = new JoinRequest(); 	//화면단에서 오는 정보를 담는 클래스
		joinReq.setMember_id(req.getParameter("member_id"));
		joinReq.setName(req.getParameter("name"));
		joinReq.setPassword(req.getParameter("password"));
		joinReq.setConfirmPassword(req.getParameter("confirmPassword"));
		joinReq.setAnswer(req.getParameter("answer"));
		
		Map<String, Boolean> errors = new HashMap<>();
		
		req.setAttribute("errors", errors);//해쉬맵은 call by reference이기 때문에 순서는 상관하지 않는다.
		joinReq.validate(errors);//위아래로 바꿔도 결과는 동일

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			joinService.join(joinReq);
			return "/join/joinSuccess.jsp";
		} catch (DuplicateIdException e) {
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

}
