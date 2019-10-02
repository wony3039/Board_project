<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LogInSuccess</title>
</head>
<body>
	${sessionScope.loginVo.name}님, 안녕하세요
	<a href='/PilotProject/login.do?command=logout'>[로그아웃]</a>
	<a href='/PilotProject/login/passwordChange.jsp'>[암호변경하기]</a>
	<form>
		<br> <a href='/PilotProject/guestbook.do'>방명록</a><br> <a href='/PilotProject/board.do'>게시판</a><br>
	</form>
</body>
</html>