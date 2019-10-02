<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("utf-8");%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardInsert</title>
<script>
	function formCheck(){
		var frmforms = document.forms;
		
		var article_no = parseInt(frmforms.article_no.value);
		var title = frmforms.title.value;
		var content = frmforms.content.value;
		
		if(frmforms.title.value == null || frmforms.title.value == ""){ //글 입력이 되어있나 체크
			alert("제목을 입력하세요.");
			frmforms.title.focus();
			return false;
		}
		
		if(frmforms.content.value == null || frmforms.content.value == ""){
			alert("내용을 입력하세요");
			frmforms.content.focus(); // 해당 태그 포커스
			return false;
		}

		frmforms.method = "get";
		frmforms.action="boardupdate.do";
		frmforms.submit();
				
	}
	
</script>
</head>
<body>
<form name="forms">
<table width="80%" cellpadding="0" cellspacing="0" border="2" align="center">
<c:forEach var="memberList" items="${viewinfo}" >
<tr>
<td align="center">번호</td>
<td>${memberList.article_no }</td>
</tr>
<tr>
 <td align="center">제목</td>
 <td><input type="text" name="title" value="${memberList.title }"></td>
 </tr>
 <tr>
 <td align="center">내용</td><br>
 <td><textarea name="content"  style="resize:none; width:300px; height:100px" >${memberList.content }</textarea></td>
 </tr>
 <tr>
 <input type="hidden" name="article_no" value="${memberList.article_no }">
 <td colspan="2" align="right"><input type="button" onclick="formCheck()" value="글 수정" ></td>
 </tr>
 </c:forEach>
 </table>
 <!--
textarea			텍스트 박스
resize				사이즈 고정

text-align          인풋박스 안의 텍스트 정렬
width               인풋박스의 넓이
height              인풋박스 높이
letter-spacing      텍스트 자간
 -->
 </form>
</body>
</html>