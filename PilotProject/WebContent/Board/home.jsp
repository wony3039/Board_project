<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("utf-8");%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardHome</title>
</head>

<body>
<form>
<table border="1" width="80%" align="center">
		 <tr height="3%">
		  <td align="left" colspan="4" height="10">
		   <input type="button" name="board" value="게시글 쓰기" onclick="location.href='boardadd.do'">
		  </td>
		 </tr>
		 
		 <tr height="10%">
		  <td align="center" width="10%" height="10">번호</td>
		  <td align="center" width="70%" height="10">제목</td>
		  <td align="center" width="10%" height="10">작성자</td>
		  <td align="center" width="10%" height="10">조회수</td> 
		 </tr>
		<c:forEach var="memberList" items="${viewinfo.printList}" >
		 <c:url var="url"  value="boardlook.do">
			<c:param  name="no" value="${memberList.article_no }" />
		 </c:url>
		 <tr height="82%">
		  <td align="center">${memberList.article_no }</td>
		  <td align="center"><a href="${url }">${memberList.title }</a></td>
		  <td align="center">${memberList.writer_name }</td>
		  <td align="center">${memberList.read_cnt }</td>
	 	 </tr>
		</c:forEach>
		<tr height="5%">
		<td colspan="4" align="center">
		 <c:forEach var="pageNum" begin="1" end="${viewinfo.pageTotalCount}">
		  <a href="board.do?page=${pageNum}">[${pageNum}]</a>
		 </c:forEach>
		</td>
		</tr>
</table>
		 
</form>
</body>
</html>