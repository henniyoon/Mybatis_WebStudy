<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(function() {
		$("#btnUpdate").click(function() {
			var writer = $("#writer");
			var memo = $("#memo");
			if(writer.val() == "") {			// 이름칸이 비어있으면
				alert("이름을 입력하세요.");	// 이름을 입력하세요
				writer.focus();
				return;
			}
			if(memo.val() == "") {				// 메모칸이 비어있으면
				alert("메모를 입력하세요.");	// 메모를 입력하세요
				memo.focus();
				return;
			}
			document.form1.action = "/ex_memo/memo_servlet/update.do";
			document.form1.submit();
		});
		$("#btnDelete").click(function() {
			if(confirm("삭제하시겠습니까?")) {
				document.form1.action = "/ex_memo/memo_servlet/del.do";
				document.form1.submit();
			}
		});
		
	});
</script>
</head>
<body>
	<h2>메모 수정</h2>
	<form name="form1" method="post">
		<table border="1" style="width:550px;">
			<tr>
				<td>이름</td>
				<td><input type="text" name="writer" id="writer" value="${dto.writer}"></td>
			</tr>
			<tr>
				<td>메모</td>
				<td><input type="text" name="memo" id="memo" value="${dto.memo}"></td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<input type="hidden" name="idx" value="${dto.idx}">
					<input type="button" value="수정" id="btnUpdate">
					<input type="button" value="삭제" id="btnDelete">
				</td>
			</tr>
		</table> 
	</form>
</body>
</html>