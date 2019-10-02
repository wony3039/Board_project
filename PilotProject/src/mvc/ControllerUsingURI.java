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

	public void init() throws ServletException {  //������Ƽ���� URL�� ���������� �о��, �ʿ��� ����
													//�ν��Ͻ��� ����
		
		//����ȯ�汸�� ��ü�� web.xml������ init-param�� ���� ���´�.
		String configFile = getInitParameter("configFile");		//config���� ������
		Properties prop = new Properties();	
		
		String configFilePath = getServletContext().getRealPath(configFile);
		/*ServletContext �������̽��� getRealPath() �޼��带 �̿��� web.xml�� init-param��
		   ������ ������ properties ������ ��ġ�� ���� ��θ� ��´�.*/
		
		try (FileReader fis = new FileReader(configFilePath)) { // properties������ �б����� ��Ʈ������.
			prop.load(fis); // properties ������ �ε��Ѵ�.
		} catch (IOException e) {
			throw new ServletException(e);
		}

		Iterator keyIter = prop.keySet().iterator();
		/* PropertiesŬ������ HashTableŬ�����κ��� ��ӹ��� keySet() �޼��带 �̿��Ͽ�
		  Iterator Ŭ������ ��ü�� ���� �� �ִ�.*/
		
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();  //command=Ű
			// properties���Ͽ� ��ϵ� Ű�� command������ �����Ѵ�.
			
			String handlerClassName = prop.getProperty(command); //��
			 // getProperty() �޼��带 ����Ͽ� properties���Ͽ� ��ϵ� �ش�Ű�� ���� ��´�.
			
			try {
				// Class Ŭ������ �̿��Ͽ� �ش�Ŭ������ ��ü�� �����Ѵ�.
				Class<?> handlerClass = Class.forName(handlerClassName); //���̸����� Ŭ���� ����
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();	//Ŭ������ �ν��Ͻ�����
				
				// command�� command�� �ش��ϴ� handlerInstance�� Map�� Ű�� ������ �����Ѵ�.
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

	private void process(HttpServletRequest request, HttpServletResponse response)	//URL©�� �ڿ��ִ°� ������	//�ʿ��� �ν��Ͻ� ������ //���μ��� ���⸸ ��
			throws ServletException, IOException {
		String command = request.getRequestURI();  // ��û���� �Ѿ�� �Ķ���Ͱ��� ����.
		if (command.indexOf(request.getContextPath()) == 0) {
			command = command.substring(request.getContextPath().length());
		}
		
		/* �ʿ� ����� command�� �ش��ϴ� ��. ��,�ڵ鷯Ŭ������ ��ü�� �����ͼ� ��ĳ������,
		  CommandHandler �������̽��� ��ü ������ handler�� �����Ѵ�.*/
		CommandHandler handler = commandHandlerMap.get(command);
		
		/* �ʿ��� command�� �ش��ϴ� ��. ��, �ڵ鷯 Ŭ������ ��ü�� ���ٸ� NullHandlerŬ������
		  ��ü�� handler ��ü������ �����Ѵ�.*/
		if (handler == null) {
			handler = new NullHandler(); // Not Found Error�� Client���� ����
		}

		String viewPage = null;
		try {
			/* ��ĳ���ð� ����޼����� ������ ���� �������̽��� process()�޼��带 ������ 
			   �ش� Ŭ������ process() �޼��带 ȣ���Ͽ� ���������� ��θ� ���Ϲ޴´�.*/
			viewPage = handler.process(request, response);
		} catch (Throwable e) {
			throw new ServletException(e);
		}
		if (viewPage != null) {
			 // �ش� ���������� �������Ѵ�.
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}
}
