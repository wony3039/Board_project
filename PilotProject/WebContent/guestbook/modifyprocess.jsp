<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>modify</title>
</head>
<body>
	<form action="/PilotProject/modify.do?command=modifysuccess" method="post">
		<p>
			메시지:<br />
			<input type="text" name="message" placeholder="${printVo.message}">
			<input type="hidden" name="printVo.message_id" value="${printVo.message_id}">
		</p>
		<input type="submit" value="수정">
	</form>
</body>
</html>