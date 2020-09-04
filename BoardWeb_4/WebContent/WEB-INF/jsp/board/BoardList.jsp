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
	<span onclick="location.href='profile'">프로필</span>
	<div>
		<span onclick="location.href='regmod'">글쓰기</span> <span
			onclick="location.href='logout'">로그아웃</span>
	</div>
	<h1>게시판</h1>
	<table>
		<tr>
			<th>글번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>조회수</th>
			<th>좋아요</th>
			<th>게시일</th>
		</tr>
		<c:if test="${empty showPage}">
			<tr>
				<td colspan=5 style="text-align: center;">작성된 게시글이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach items="${showPage}" var="item">
			<tr
				onclick="location.href='detail?i_board=${item.i_board}&searchText=${param.searchText}'">
				<td>${item.i_board}</td>
				<td>
				<c:choose>
						<c:when test="${item.profile_img != null}">
							<img width="30px" height="30px"
								src="/img/user/${login_user.i_user}/${item.profile_img}">
							<!-- 세션과 서블릿에 설정한 attribute 사용 -->
						</c:when>
						<c:otherwise>
							<img width="30px" height="3s0px"
								src="`">
							<!-- web-inf 폴더 안에 있는거 사용 -->
						</c:otherwise>
				</c:choose>
				${item.u_nm}
				</td>
				<td>${item.title} &#40;${item.cmt_cnt}&#41;</td>
				<td>${item.hits}</td>
				<td>${item.like_cnt}</td>
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
				<span
					onclick="location.href='boardlist?page=${cnt == null ? 1 : cnt}&record_cnt=${param.record_cnt == null ? 5 : param.record_cnt}&searchText=${param.searchText}'">${cnt}</span>
			</c:if>
		</c:forEach>
	</div>
	<div>
		<form action="/boardlist" method="get" id="selFrm">
			<input type="hidden" name="page" value="1"> <input
				type="hidden" name="searchText" value="${param.searchText}">
			레코드 수 <select name="record_cnt" onchange="selFrm.submit()">
				<c:forEach begin="5" end="15" step="5" var="item">
					<c:choose>
						<c:when test="${param.record_cnt == item}">
							<option value="${item}" selected>${item}개</option>
						</c:when>
						<c:otherwise>
							<option value="${item}">${item}개</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</form>
	</div>
	<div>
		<form action="/boardlist" id="searchFrm" method="get">
			<input type="search" name="searchText" value="${param.searchText}">
			<span onclick="searchFrm.submit()">검색</span>
		</form>
	</div>
</body>
</html>