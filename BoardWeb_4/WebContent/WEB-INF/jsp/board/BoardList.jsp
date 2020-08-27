<%@page import="com.sun.org.apache.xpath.internal.operations.Div"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="blog.hyojin4588.pjt.vo.PageVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<style>
span {
	cursor: pointer;
	margin-right: 10px;
}
</style>
</head>
<body>
	<div>${login_user.getUser_nm()}님환영합니다.</div>
	<div>
		<span onclick="location.href='regmod'">글쓰기</span><span
			onclick="location.href='logout'">로그아웃</span>
	</div>
	<h1>게시판</h1>
	<table>
		<tr>
			<th>글번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>조회수</th>
			<th>게시일</th>
		</tr>
		<c:if test="${empty showPage}">
			<tr>
				<td colspan=5 style="text-align: center;">작성된 게시글이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach items="${showPage}" var="item">
			<tr onclick="location.href='detail?i_board=${item.i_board}'">
				<td>${item.i_board}</td>
				<td>${item.u_nm}</td>
				<td>${item.title}</td>
				<td>${item.hits}</td>
				<td>${item.r_dt}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<c:forEach var="cnt" begin="1" end="${page}" step="1">
			<c:if test="${cnt == currentPage}">
				<span style="color: red;">${cnt}</span>
			</c:if>
			<c:if test="${cnt != currentPage}">
				<span onclick="location.href='boardlist?page=${cnt}'">${cnt}</span>
			</c:if>
		</c:forEach>
	</div>
	<div>
		<form action="/boardlist?page=${page}">
			레코드 수 <select id="select1" onchange="changeRecordCnt()'">
				<c:forEach begin="10" end="30" step="10" var="item">
					<c:choose>
						<c:when test="${para.record_cnt == item || (param.record_cnt == null && item == 10)}">
							<option value="${item}" selected>${item}개</option>
						</c:when>
					</c:choose>
				</c:forEach>
			</select>
		</form>
	</div>
</body>
</html>