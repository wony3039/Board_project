package mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler {
	String process(HttpServletRequest var1, HttpServletResponse var2) throws Exception;
}