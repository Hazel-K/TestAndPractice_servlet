<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <span class="del" onclick="location.href='delete?i_board=' + ${data.i_board}">삭제</span>
    <span class="mod" onclick="location.href='regmod?i_board=' + ${data.i_board}">수정</span>
    <span class="gotolist" onclick="location.href='boardlist'">리스트로</span>
</div>
</body>
</html>