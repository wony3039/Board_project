<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PasswordFind</title>
</head>
<body>
	<form action="/PilotProject/find.do" method="post">
		아이디: <input type="text" name="member_id"><br> 
		<c:if test="${errors.member_id}">아이디를 입력하세요.<br></c:if>
		<br>
		가장 좋아하는 동물은?<br>
		<input type="text" name="answer"><br> 
		<c:if test="${errors.answer}">찾기값을 입력하세요.<br></c:if>
		 <input type="submit" value="찾기">
	</form>
	<br>
	<c:if test="${errors.noMatch}">아이디 또는 찾기값이 올바르지 않습니다<br></c:if>
	<b><c:if test="${check}">비밀번호는 ${gurantee} 입니다.<br></c:if></b>	
		<a href ="/PilotProject/login/login.jsp">로그인창으로</a>
</body>
</html>