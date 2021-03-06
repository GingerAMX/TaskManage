<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dao.ManageDAO"%>
<%@page import="dto.TaskContent"%>
<%@page import="dto.Students"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>課題詳細</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
	<header>
		<a href="/TaskManage/Login" class="square_btn"style="float:right">ログアウト</a>
		<h1>課題詳細</h1>
		<hr>
	</header>
	<main>
		<%
		request.setCharacterEncoding("UTF-8");
		ArrayList<TaskContent> resultList = (ArrayList<TaskContent>) request.getAttribute("resultList");
		String[] key = (String[])request.getAttribute("key");

		//日付への変更
		int i = 0;
		String[] date;
		date = new String[1];
		TaskContent result = (TaskContent) resultList.get(0);
		String a = Integer.toString(result.getDeadline());
		String b = a.substring(0, 4) + "年" + a.substring(4, 6) + "月" + a.substring(6, 8) + "日";
		date[i] = b;
	%>
	<div class="margin_box">
		<div class="box">
			<table class="task">
				<tr>
					<th>課題名</th>
					<td>
						<%
							out.println(result.getTaskName());
						%>
					</td>
				</tr>
				<tr>
					<th>期限</th>
					<td>
						<%
							out.println(date[i]);
						%>
					</td>
				</tr>
				<tr>
					<th>提出先</th>
					<td>
						<%
							out.println(result.getName());
						%>
					</td>
				</tr>
			</table>
			<p class="detail">
				<label>内容<br>
					<textarea class="textarea" rows="8" cols="45" readonly>
						<%
							out.println(result.getText());
						%>
					</textarea></label>
			</p>
			<div class="teacher_detail_margin">
				<div class="teacher_detail1">
				<form action="/TaskManage/StudentsPage" method="POST">
	        		<input type="submit" value="←" class="square_btn" style="float: left;width: 30%;font-size: 40px;margin-left:40px;">
	        	</form>
	        	</div>
	        	<div class="teacher_detail3">
				<form action="/TaskManage/Submit" method="GET">
					<input type="submit" value="提出画面へ" class="square_btn" style="float: left; width: 80%;font-size: 30px;margin-left:200px;">
				</form>
				</div>
			</div>
		</div>
	</div>
	</main>
</body>
</html>