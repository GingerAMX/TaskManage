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
				<p style="font-size:35px; margin: 0;">
					学　年 ：<label class="download_file">
						<select name="grade" class="select_btn">
							<option value="" selected>学年</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
					</label>
				</p>
				<p style="font-size:35px; margin: 10px 0;">
					クラス ：<label class="download_file">
						<select name="class" class="select_btn">
								<option value="" selected>クラス</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
						</select>
					</label>
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
				<thead>
					<tr>
						<th>提出者名</th>
						<th>提出ファイル</th>
					</tr>
				</thead>
				<tbody>
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
				</tbody>
			</table>
		</div>
	</div>
	</main>
</body>
</html>