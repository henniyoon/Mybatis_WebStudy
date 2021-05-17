<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
	<h3>설문조사</h3>
	<h3>${dto.question}</h3>
	<form method="post" action="mybatis_webstudy/survey_servlet/insert.do">
		<input type="radio" name="num" value="1" checked>${dto.ans1}<br>
		<input type="radio" name="num" value="2">${dto.ans2}<br>
		<input type="radio" name="num" value="3">${dto.ans3}<br>
		<input type="radio" name="num" value="4">${dto.ans4}<br>
		<input type="hidden" name="survey_idx" value="${dto.survey_idx}">
		<input type="submit" value="확인">
	</form>
</body>
</html>