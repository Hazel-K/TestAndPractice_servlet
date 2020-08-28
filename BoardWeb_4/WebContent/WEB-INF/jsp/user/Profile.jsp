<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필</title>
</head>
<body>
	<h1>프로필</h1>
	<div>${data.user_id}</div>
	<div>${data.user_nm}</div>
	<div><img width="100px" height="100px" src="${data.profile_img == null ? '/img/default_profile.jpg' : data.profile_img}"></div>
	<div>${data.email}</div>
</body>
</html>