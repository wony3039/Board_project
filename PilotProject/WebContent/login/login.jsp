<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<form action="/PilotProject/login.do" method="post">
		아이디: <input type="text" name="member_id"><br> 
		암호:<input type="password" name="password"><br> 
			<input type="submit" value="로그인">
			<a href='/PilotProject/login/passwordFind.jsp'>[암호찾기]</a>
		<c:if test="${param.errors =='noMatch'}">아이디 또는 비밀번호가 일치하지 않습니다.</c:if>
	</form>
</body>
</html>