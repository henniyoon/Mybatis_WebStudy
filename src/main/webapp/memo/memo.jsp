<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	$(function() {
		list();
		$("#btnSave").click(function() {
			insert();
		});
		$("#btnSearch").click(function() {
			list();
		});
	});
 	function list() {
		var param = "searchkey=" + $("#searchkey").val() + "&search=" + $("#search").val(); 
		$.ajax({
			type : "post",
			data : param,
			url : "/mybatis_webstudy/memo_servlet/list.do",
			success : function(result) {
				$("#result").html(result);
			}
		});
	}
	function insert() {
		var writer = $("#writer").val();
		var memo = $("#memo").val();
		var param = "writer=" + writer + "&memo=" + memo;
		$.ajax({
			type: "post",
			url: "/mybatis_webstudy/memo_servlet/insert.do",
			data: param,
			success: function() {
				list("search=");
				$("#writer").val("");	// 이름 초기화
				$("#memo").val("");		// 메모 초기화
			}
		});
	}
</script>
</head>
<body>
	<!-- memo insert -->
	이름 : <input id="writer" size="10"><br>
	메모 : <input id="memo" size="40">
	<input type="button" id="btnSave" value="확인"><br>
	<!-- memo search -->
	<select id="searchkey">
		<option value="writer">이름</option>
		<option value="memo">메모</option>
		<option value="writer_memo">이름+메모</option>
	</select>
	<input type="text" id="search" value="${search}"/>
	<input type="button" id="btnSearch" value="조회"/>
	<!-- memo list -->
	<div id="result"></div>
</body>
</html>