<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GuestBookDelete</title>
</head>
<body>
	<form action="/PilotProject/modify.do" method="post">
		<input type="hidden" name="message_id" value=" ${param.message_id}">
		메시지를 수정하시려면 암호를 입력하세요:<br /> 
		암호: <input type="password" name="password"> <br />
		<c:if test="${errors.password}">비밀번호를 입력하세요<br>
		</c:if>
		<c:if test="${errors.noMatch}">비밀번호가 일치하지 않습니다.<br>
		</c:if>
		<input type="submit" value="메시지 수정하기">
	</form>
</body>
</html>