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
		
		if(frmforms.password.value == null || frmforms.password.value == ""){
			alert("내용을 입력하세요");
			frmforms.password.focus(); // 해당 태그 포커스
			return false;
		}
		
		frmforms.action="boardadd.do";
		frmforms.submit();
				
	}
	
</script>
</head>
<body>
<form name="forms">
 제목<br>
 <input type="text" name="title"><br><br> 
 내용<br>
 <textarea name="content"  style="resize:none; width:300px; height:100px"></textarea><br><br>
 암호<br> <input type="password" name="password"> <br><br>
 <input type="button" onclick="formCheck()" value="새 글 등록" >
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