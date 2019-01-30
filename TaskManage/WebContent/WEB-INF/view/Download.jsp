<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dto.Download"%>
<%@page import="java.util.ArrayList"%>
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
		ArrayList<Download> result = (ArrayList<Download>)request.getAttribute("result");
		String flg = (String) request.getAttribute("flg");
	%>
	<header>
		<a href="/TaskManage/Login" class="square_btn" style="float: right">ログアウト</a>
		<h1>ファイル取得</h1>
		<hr>
	</header>
	<main>
	<div class="file_acquisition">
		<div class="box">
			<form action="/TaskManage/Download" method="post">
				<p style="font-size:35px;">
					課題名 ：<label class="download_file"><input type="text"
						name="taskName" class="file_input"></label>
				</p>
				<p style="font-size:35px;">
					学　年 ：<label class="download_file"><input type="text"
						name="grade" class="file_input"></label>
				</p>
				<p style="font-size:35px;">
					クラス ：<label class="download_file"><input type="text"
						name="class" class="file_input"></label>
				</p>
				<div class="margin_bottom">
					<div class="under_margin">
						<input type="hidden" name="flg" value="<%=flg%>">
						<input type="submit" value="抽出" class="square_btn" style="text-align: center;">
					</div>
				</div>
			</form>
			<div class="margin_bottom">
				<div class="under_margin">
					<%
						if ("true".equals(flg)) {
					%>
					<form action="/TaskManage/ManagerPage" method="POST">
						<input type="submit" value=" ← " class="square_btn"
							style="height: 60px; text-align: center;">
					</form>
					<%
						} else if ("false".equals(flg)) {
					%>
					<form action="/TaskManage/TeacherPage" method="POST">
						<input type="submit" value=" ← " class="square_btn"
							style="height: 60px; text-align: center;">
					</form>
					<%
						}
					%>
				</div>
			</div>
		</div>
		<div class="sub_content">
			<table border="1">
				<tr>
					<th>提出者名</th>
					<th>提出ファイル</th>
				</tr>
			<%	if(result == null || result.size() == 0){
				}else{
					for(int i = 0; i < result.size(); i++){
						Download mid = result.get(i);
						if("".equals(mid.getsName()) || mid.getsName() == null){
							continue;
						}else{
						%>
						<tr>
							<td><%=mid.getsName()%></td>
							<td><a href="<%=mid.getPath()%>" download>ダウンロード</a></td>
						</tr>
					<%	}
					}
				}%>
			</table>
		</div>
	</div>
	</main>
</body>
</html>