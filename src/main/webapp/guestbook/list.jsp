<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script src="http://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	function gb_search() {
		document.form1.action="/mybatis_webstudy/guestbook_servlet/list.do";
		document.form1.submit();
	}
</script>
</head>
<body>
	<h2>방명록</h2>
	<hr>
	<form name="form1" method="post">
		<select name="searachkey">
			<c:if test="${searchkey == 'name'}">
				<option value="name" selected>이름</option>
				<option value="content">내용</option>
				<option value="name_content">이름+내용</option>
			</c:if>
			<c:if test="${searchkey == 'content'}">
				<option value="name">이름</option>
				<option value="content" selected>내용</option>
				<option value="name_content">이름+내용</option>
			</c:if>
			<c:if test="${serachkey == 'name_content'}">
				<option value="name">이름</option>
				<option value="content">내용</option>
				<option value="name_content" selected>이름+내용</option>
			</c:if>
		</select>
		<input type="text" name="searach" value="${searach}">
		<input type="button" value="조회" onclick="gb_search()">
	</form>
	${count}개의 글이 있습니다. &nbsp;
	<input type="button" value="글쓰기"
	onclick="location.href='/mybatis_webstudy/guestbook/write.jsp'">
	<c:forEach var="dto" items="${list}">
		<form action="/mybatis_webstudy/guestbook_servlet/passwd_check.do" method="post">
			<!-- 화면에 표시할 필요는 없지만 꼭 필요한 값 hidden 처리 -->
			<input type="hidden" name="idx" value="${dto.idx}">
			<table border="1" style="width:600px;">
				<tr>
					<td style="text-align: center; background: cyan; width="20%">이름</td>
					<td style="text-align: center; width="30%">${dto.name}</td>
					<td style="text-align: center; background: cyan; width="20%">날짜</td>
					<td style="text-align: center; width="30%">${dto.post_date}</td>
				</tr>
				<tr>
					<td style="text-align: center; background: cyan">이메일</td>
					<td colspan="3">${dto.email}</td>	<!-- 가로방향 셀 병합 -->
				</tr>
				<tr>
					<td colspan="4">${dto.content}</td>
				</tr>
				<tr>
					<td colspan="4">
						비밀번호 <input type="password" name="passwd">
						<input type="submit" value="수정/삭제">
					</td>
				</tr>
			</table>
		</form>
	</c:forEach>
</body>
</html>