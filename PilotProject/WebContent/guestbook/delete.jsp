<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GuestBookDelete</title>
</head>
<body>
 <form action="/PilotProject/delete.do" method="post">
  <input type="hidden" name="message_id" value=" ${param.message_id}" >
    메시지를 삭제하시려면 암호를 입력하세요:<br />
    암호: <input type="password" name="password" > <br />
  <input type="submit" value="메시지 삭제하기" >
  </form>
  </body>
</html>