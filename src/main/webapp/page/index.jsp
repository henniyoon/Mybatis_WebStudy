<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	$(function() {	// 자동 실행
		list("1");
	});
	function list(curPage) {
		var param = "curPage=" + curPage;
		console.log(param);
		$.ajax({
			type: "post",
			url: "/mybatis_webstudy/page_servlet/list.do",
			data: param,
			success: function(result) {
				$("#result").html(result);
			}
		});
	}
</script>
</head>
<body>
<h2>페이지 나누기</h2>
<div id="result"></div>
</body>
</html>