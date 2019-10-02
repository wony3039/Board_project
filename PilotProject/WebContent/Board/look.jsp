<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("utf-8");%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardLook</title>
</head>
<body>
 <body>
 
<table width="80%" cellpadding="0" cellspacing="0" border="2" align="center">
	<c:forEach var="memberList" items="${viewinfo}" >
	
	<c:url var="url"  value="boardupdate.do">
	 <c:param  name="article_no" value="${memberList.article_no}" />
	 <c:param  name="title" value="${memberList.title  }" />
	 <c:param  name="command" value="update" />
 	</c:url>
 	
 	<c:url var="url2"  value="boarddelete.do">
	 <c:param  name="article_no" value="${memberList.article_no}" />
	 <c:param  name="title" value="${memberList.title  }" />
	 <c:param  name="command" value="delete" />
 	</c:url>
 	
   <tr>
    <td width="74" >번호</td>
    <td colspan="2" align = "center">${memberList.article_no }</td>
   </tr>
   
   <tr>
   <td width="74">작성자</td>
   <td colspan="2" align = "center">${memberList.writer_name }</td>
   </tr>
   
   <tr>
    <td width="74">제목</td>
    <td colspan="2" align = "center">${memberList.title }</td>
   </tr>
   
   <tr>
   <td width="74">내용</td>
   <td colspan="2" align = "center">${memberList.content }</td>
   </tr>
   
   <tr><td colspan="3" height="5"></td></tr>
   </c:forEach>
   <tr colspan="3" align="center">
    <td><input type=button value="목록" onclick="location.href='board.do'"></td>
    <td><input type=button value="게시글수정" onclick="location.href='${url }'"></td>
    <td><input type=button value="게시글삭제" onclick="location.href='${url2 }'"></td>
  </tr>
 </table>
 
<table width="100%" cellpadding="0" cellspacing="0" border="0">
  
</table>
</body> 
</html>