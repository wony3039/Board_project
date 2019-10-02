<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GuestBookHome</title>
</head>
<body>
	<form action="/PilotProject/guestbook.do" method="post">
		이름: <input type="text" name="guest_name"> <br> 암호: <input
			type="password" name="password"> <br> 메시지:
		<textarea name="message" cols="30" rows="3"></textarea>
		<br> <input type="submit" value="메시지 남기기" />
	</form>
	<hr>
	<table border="1">
		<c:forEach var="message" items="${viewinfo.printList}">
			<tr>
				<td>메시지 번호: ${message.message_id} <br /> 손님이름:
					${message.guest_name} <br /> 메시지: ${message.message} <br /> 
					<a href="/PilotProject/guestbook/delete.jsp?message_id=${message.message_id}">[삭제하기]</a>
					<a href="/PilotProject/guestbook/modify.jsp?message_id=${message.message_id}">[수정하기]</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<c:forEach var="pageNum" begin="1" end="${viewinfo.pageTotalCount}">
		<a href="/PilotProject/guestbook.do?page=${pageNum}">[${pageNum}]</a>
	</c:forEach>
</body>
</html>