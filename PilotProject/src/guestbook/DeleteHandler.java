package guestbook;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.CommandHandler;

public class DeleteHandler implements CommandHandler  {

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
			PrintVo printVo = new PrintVo();
			
			printVo.setMessage_id(req.getParameter("message_id"));
			printVo.setPassword(req.getParameter("password"));
		
			boolean answer= service.delete(printVo);
			if(answer) {
				return "/guestbook/deleteSuccess.jsp";
			}
				return "/guestbook/deleteFail.jsp";
			
			
		}
	}