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
	<table border="1" style="width:700px">
		<tr>
			<th>사번</th>
			<th>이름</th>
		</tr>
		
		<c:forEach var="row" items="${list}">
			<tr align="center">
				<td>${row.empno}</td>
				<td>${row.ename}</td>
			</tr>	
		</c:forEach>
		
		<!-- 페이지 네비게이션 출력 영역 -->
		<tr align="center">
			<td colspan="2">
			
				<!-- [처음] -->
				<c:if test="${page.curPage > 1}">
					<a href="#" onclick="list('1')">[처음]</a>
				</c:if>
				
				<!-- [이전] -->
				<c:if test="${page.curBlock > 1}">
					<a href="#" onclick="list('${page.prevPage}')">[이전]</a>
				</c:if>
				
				<c:forEach var="num" begin="${page.blockStart}" end="${page.blockEnd}">
					<c:choose>
					
						<c:when test="${num == page.curPage}">
							<!-- 현재 페이지 -->
							<span style="color:red">${num}</span>
						</c:when>
						
						<c:otherwise>
							<!-- 현재 페이지가 아닐 때 -->
							<a href="#" onclick="list('${num}')">${num}</a>
						</c:otherwise>
					
					</c:choose>
				</c:forEach>
				
				<!-- 다음 -->
				<c:if test="${page.curBlock < page.totBlock}">
					<a href="#" onclick="list('${page.nextPage}')">[다음]</a>
				</c:if>
				
				<!-- 마지막 -->
				<c:if test="${page.curPage < page.totPage}">
					<a href="#" onclick="list('${page.totPage}')">[마지막]</a>
				</c:if>
			
			</td>
		</tr>
	</table>
</body>
</html>