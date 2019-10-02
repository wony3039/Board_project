package mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NullHandler implements CommandHandler {
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.sendError(404);
		return null;
	}
}