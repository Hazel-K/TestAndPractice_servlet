<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="blog.hyojin4588.pjt.vo.BoardVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data.title}</title>
</head>
<body>
<div>${data.title}</div>
<div>${data.u_nm}</div>
<div>${data.r_dt}</div>
<div>${data.ctnt}</div>
<div>
<c:if test="${login_user.i_user == data.i_user}">
<form id="delFrm" action="delete" method="post">
	<input type="hidden" name="i_board" value="${data.i_board}">
	<span class="del" onclick="goToDel()">삭제</span>
</form>
    <span class="mod" onclick="location.href='regmod?i_board=' + ${data.i_board}">수정</span>
</c:if>
    <span class="gotolist" onclick="location.href='boardlist'">리스트로</span>
</div>
</body>
<script>
	function goToDel(){
		delFrm.submit();
	}
</script>
</html>