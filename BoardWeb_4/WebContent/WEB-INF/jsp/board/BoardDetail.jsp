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
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>
<body>
	<div>${data.title}</div>
	<div>
		<span><img alt=""
			src="${data.profile_img == null ? '' : data.profile_img}"></span>
		<div>
			<c:choose>
				<c:when test="${data.profile_img != null}">
					<img width="30px" height="30px"
						src="/img/user/${data.i_user}/${data.profile_img}">
					<!-- 세션과 서블릿에 설정한 attribute 사용 -->
				</c:when>
				<c:otherwise>
					<img width="30px" height="30px" src="${'/img/default_profile.jpg'}">
					<!-- web-inf 폴더 안에 있는거 사용 -->
				</c:otherwise>
			</c:choose>
			<span>${data.u_nm}</span>
		</div>
	</div>
	<div>${data.r_dt}</div>
	<div>${data.ctnt}</div>
	<div>
		<c:if test="${login_user.i_user == data.i_user}">
			<form id="delFrm" action="delete" method="post">
				<input type="hidden" name="i_board" value="${data.i_board}">
				<span class="del" onclick="delFrm.submit()">삭제</span>
			</form>
			<span class="mod"
				onclick="location.href='regmod?i_board=' + ${data.i_board}">수정</span>
		</c:if>
		<form id="likeFrm" action="detail" method="post">
			<input type="hidden" name="page" value="${param.page}"> <input
				type="hidden" name="record_cnt" value="${param.record_cnt}">
			<input type="hidden" name="searchText" value="${param.searchText}">
			<input type="hidden" name="i_board" value="${data.i_board}">
			<span onclick="likeFrm.submit()">좋아요 <c:if test="${like == 1}">
					<span class="material-icons" style="color: pink;">favorite</span>
				</c:if> <c:if test="${like == 0}">
					<span class="material-icons" style="color: pink;">favorite_border</span>
				</c:if>
			</span>
		</form>
		<span class="gotolist"
			onclick="location.href='boardlist?page=${page}&record_cnt=${record_cnt}&searchText=${param.searchText}'">리스트로</span>
	</div>
	<div>
		<form id="cmtFrm" action="coment" method="post">
			<input type="hidden" name="i_cmt" value="0"> <input
				type="hidden" name="i_board" value="${data.i_board}">
			<div>
				<input type="text" name="cmt" value="" placeholder="댓글"> <span
					id="cmtbtnsubmit" onclick="cmtFrm.submit()">전송</span> <span id=""
					onclick="cancelCmt()">취소</span>
			</div>
		</form>
	</div>
	<div>
		<table>
			<c:forEach items="${list}" var="item">
				<tr>
					<td><c:choose>
							<c:when test="${item.profile_img != null}">
								<img width="30px" height="30px"
									src="/img/user/${item.i_user}/${item.profile_img}">
								<!-- 세션과 서블릿에 설정한 attribute 사용 -->
							</c:when>
							<c:otherwise>
								<img width="30px" height="30px"
									src="${'/img/default_profile.jpg'}">
								<!-- web-inf 폴더 안에 있는거 사용 -->
							</c:otherwise>
						</c:choose> ${item.u_nm}</td>
					<td>${item.cmt}</td>
					<td>${item.r_dt}</td>
					<c:if test="${login_user.i_user == item.i_user}">
						<td onclick="clkCmtMod(${item.i_cmt}, '${item.cmt}')">수정</td>
						<td
							onclick="location.href='coment?i_cmt=${item.i_cmt}&i_board=${item.i_board}'">삭제</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script>
	function cancelCmt() {
		cmtFrm.i_cmt.value = 0;
		cmtFrm.cmt.value = '';
		cmtbtnsubmit.innerText = '전송';
	}
	
	function clkCmtMod(i_cmt, cmt) {
		cmtFrm.i_cmt.value = i_cmt;
		cmtFrm.cmt.value = cmt;
		cmtbtnsubmit.innerText = '수정';
	}
</script>
</body>
</html>