package mvc;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ControllerUsingURI extends HttpServlet {

	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();

	public void init() throws ServletException {  //프로퍼티에서 URL의 매핑정보를 읽어옴, 맵에다 넣음
													//인스턴스를 만들어서
		
		//서블릿환경구성 객체로 web.xml파일의 init-param의 값을 얻어온다.
		String configFile = getInitParameter("configFile");		//config파일 가져옴
		Properties prop = new Properties();	
		
		String configFilePath = getServletContext().getRealPath(configFile);
		/*ServletContext 인터페이스의 getRealPath() 메서드를 이용해 web.xml의 init-param의
		   값으로 지정된 properties 파일이 위치한 절대 경로를 얻는다.*/
		
		try (FileReader fis = new FileReader(configFilePath)) { // properties파일을 읽기위한 스트림개설.
			prop.load(fis); // properties 파일을 로드한다.
		} catch (IOException e) {
			throw new ServletException(e);
		}

		Iterator keyIter = prop.keySet().iterator();
		/* Properties클래스는 HashTable클래스로부터 상속받은 keySet() 메서드를 이용하여
		  Iterator 클래스의 객체를 얻을 수 있다.*/
		
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();  //command=키
			// properties파일에 기록된 키를 command변수에 저장한다.
			
			String handlerClassName = prop.getProperty(command); //값
			 // getProperty() 메서드를 사용하여 properties파일에 기록된 해당키의 값을 얻는다.
			
			try {
				// Class 클래스를 이용하여 해당클래스의 객체를 생성한다.
				Class<?> handlerClass = Class.forName(handlerClassName); //이이름으로 클래스 만듬
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();	//클래스의 인스턴스만듬
				
				// command와 command에 해당하는 handlerInstance를 Map에 키와 값으로 저장한다.
				commandHandlerMap.put(command, handlerInstance);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)	//URL짤라서 뒤에있는거 가져옴	//맵에서 인스턴스 가져옴 //프로세스 오출만 함
			throws ServletException, IOException {
		String command = request.getRequestURI();  // 요청으로 넘어온 파라미터값을 저장.
		if (command.indexOf(request.getContextPath()) == 0) {
			command = command.substring(request.getContextPath().length());
		}
		
		/* 맵에 저장된 command에 해당하는 값. 즉,핸들러클래스의 객체를 가져와서 업캐스팅후,
		  CommandHandler 인터페이스의 객체 변수인 handler에 저장한다.*/
		CommandHandler handler = commandHandlerMap.get(command);
		
		/* 맵에서 command에 해당하는 값. 즉, 핸들러 클래스의 객체가 없다면 NullHandler클래스의
		  객체를 handler 객체변수에 저장한다.*/
		if (handler == null) {
			handler = new NullHandler(); // Not Found Error를 Client에게 전송
		}

		String viewPage = null;
		try {
			/* 업캐스팅과 가상메서드의 원리에 의해 인터페이스의 process()메서드를 구현한 
			   해당 클래스의 process() 메서드를 호출하여 뷰페이지의 경로를 리턴받는다.*/
			viewPage = handler.process(request, response);
		} catch (Throwable e) {
			throw new ServletException(e);
		}
		if (viewPage != null) {
			 // 해당 뷰페이지로 포워딩한다.
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}
}
