<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dao.ManageDAO"%>
<%@page import="dto.DistributionIndex"%>
<%@page import="dto.Students"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta charset="UTF-8">
<title>教員ページ</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		ArrayList<DistributionIndex> resultList = (ArrayList<DistributionIndex>) request.getAttribute("resultList");
		String key = (String)request.getAttribute("key");
		//日付への変更
		int i = 0;
		String[] date;
		date = new String[30];
		while (i < resultList.size()) {
			DistributionIndex result = (DistributionIndex) resultList.get(i);
			String a = Integer.toString(result.getDeadline());
			String b = a.substring(0, 4) + "年" + a.substring(4, 6) + "月" + a.substring(6, 8) + "日";
			date[i] = b;
			i = i + 1;
		}
	%>
<header>
	<div class="header_teacher_main">
		<a href="/TaskManage/Login" class="square_btn"style="float:right">ログアウト</a>
		<h1>配布中の課題</h1>
		<hr>
	</div>
</header>
<%
	//課題一覧の表示
%>
<main>
	<div class="content_task">
		<div class="content_task2">
			<div class="box3">
				<table class="content_tablelock1">
					<thead class="task_thead">
						<tr>
							<th class="TaskID">課題ID</th>
							<th class="TaskName">課題名</th>
							<th class="DeadLine">期限</th>
							<th class="Grade_Class">配布先</th>
							<th class="BUTTON"></th>
						</tr>
					</thead>
					<tbody class="task_tbody">
						<%
							for(int j = 0 ; j < resultList.size() ; j++) {
								DistributionIndex result = resultList.get(j);
								int id = result.getTaskID();
								String name = result.getTaskName();
								String grade = result.getGrade() + "年" + result.getcName() + "組";
						%>
						<tr>
							<form action="/TaskManage/TaskContent" method="POST">
								<td class="TaskID"><%=id  %></td>
								<td class=TaskName><%=name %></td>
								<td class="DeadLine"><%=date[j]%></td>
								<td class="Grade_Class"><%=grade%></td>
	 							<input type="hidden" name="taskID" value=<%=id %>>
								<td class="BUTTON" ><input type="submit" value="＞"></td>
							</form>
						</tr>
						<%	} %>
					</tbody>
				</table>
			</div>
		</div>
		<div class="content_task3">
			<div class="task_distribution">
				<form action="/TaskManage/Distribute" method="POST">
					<input type="submit" class="square_btn" value="配布">
				</form>
			</div>
			<div class="task_acquisition">
				<form action="/TaskManage/Download" method="GET">
					<input type="submit" class="square_btn" value="ファイル取得">
					<input type="hidden" name="flg" value="true">
				</form>
			</div>
			<div class="manager_user">
			<form action="/TaskManage/UserIndex" method="POST">
				<input type="submit" class="square_btn" value="ユーザ管理">
			</form>
			</div>
		</div>
	</div>
</main>
</body>
</html>