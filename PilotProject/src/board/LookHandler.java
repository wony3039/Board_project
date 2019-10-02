
package board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import guestbook.MemberVo;
import mvc.CommandHandler;

public class LookHandler implements CommandHandler {
	private static final String FORM_HOME = "/Board/home.jsp";
	private static final String FORM_LOOK = "/Board/look.jsp";
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
		vo.setArticle_no(Integer.parseInt(req.getParameter("no"))); // 확인바람
		vo.setTitle(req.getParameter("title"));
		vo.setContent(req.getParameter("content"));

		List viewinfo = service.look(vo);
		
		req.setAttribute("viewinfo", viewinfo);
		
		return FORM_LOOK;
	}
}