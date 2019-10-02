<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PwdChange</title>
</head>
<body>
	<form method="post" action="/PilotProject/change.do">
		<p>
			현재암호: <input type="text" name="inputpassword">

			<c:if test="${errors.noMatch }">암호가 일치하지 않습니다</c:if>

			<br>
		</p>
		<p>
			새암호: <input type="password" name="newpassword"><br>
			<c:if test="${errors.password }">암호를 입력하세요</c:if>
		</p>
		<input type="submit" value="암호변경">
	</form>
</body>
</html>