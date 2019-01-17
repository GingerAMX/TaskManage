<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dto.Download"%>
<html>
<head>
<meta charset="UTF-8">
<title>ファイル取得</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Download.css">
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String zipPath = (String) request.getAttribute("zipPath");
		String flg = (String) request.getAttribute("flg");
	%>
	<header>
		<a href="#" class="square_btn" style="float: right">ログアウト</a>
		<h1>ファイル取得</h1>
		<hr>
	</header>
	<main>
	<div class="file_acquisition">
		<div class="box">
			<form action="/TaskManage/Download" method="post">
				<p>
					課題名 ：<label class="download_file"><input type="text"
						name="taskName" class="file_input"></label>
				</p>
				<p>
					学 年 ：<label class="download_file"><input type="text"
						name="grade" class="file_input"></label>
				</p>
				<p>
					クラス ：<label class="download_file"><input type="text"
						name="class" class="file_input"></label>
				</p>
				<div class="margin_bottom">
					<div class="under_margin">
						<input type="hidden" name="flg" value="<%=flg%>"> <input
							type="submit" value="取得" class="square_btn">
					</div>
				</div>
			</form>
			<div class="margin_bottom">
			<div class="download_margin">
				<a href="<%=zipPath%>"  class="square_btn">ダウンロード</a>
			</div>
			<div class="under_margin">
				<%
					if ("true".equals(flg)) {
				%>
				<form action="/TaskManage/ManagerPage" method="POST">
					<input type="submit" value="←" class="square_btn"
						style="height: 60px;">
				</form>
				<%
					} else if ("false".equals(flg)) {
				%>
				<form action="/TaskManage/TeacherPage" method="POST">
					<input type="submit" value="←" class="square_btn"
						style="height: 60px;">
				</form>
				<%
					}
				%>
			</div>
			</div>
		</div>
	</div>
	</main>
</body>
</html>