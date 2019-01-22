<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.Submitted" %>
<%@ page import="dto.UnSubmitted" %>
<%@ page import="dto.TaskContent" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>課題提出状況</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
	<%
		request.setCharacterEncoding("UTF-8");

		ArrayList<Submitted> submitted = (ArrayList<Submitted>)request.getAttribute("submitted");
		ArrayList<UnSubmitted> unSubmitted = (ArrayList<UnSubmitted>)request.getAttribute("unSubmitted");
		ArrayList<TaskContent> resultList = (ArrayList<TaskContent>)request.getAttribute("resultList");
		String flg = (String)request.getAttribute("flg");

		//日付への変更(課題内容)
		int i = 0;
		String[] date;
		date = new String[1];
		TaskContent result1 = (TaskContent)resultList.get(0);
		String a = Integer.toString(result1.getDeadline());
		String b = a.substring(0, 4) + "年" + a.substring(4, 6) + "月" + a.substring(6, 8) + "日";
		date[i] = b;

		//日付への変更(提出日)
		int j = 0;
		String[] date2;
		date2 = new String[60];
		while(j < submitted.size()) {
			Submitted result2 = (Submitted)submitted.get(j);
			a = Integer.toString(result2.getDate());
			b = a.substring(0, 4) + "年" + a.substring(4, 6) + "月" + a.substring(6, 8) + "日";
			date2[i] = b;
			j = j + 1;
		}
	%>
	<header>
		<a href="/TaskManage/Login" class="square_btn" style="float: right">ログアウト</a>
		<h1>課題提出状況</h1>
		<hr>
	</header>
	<main>
	<%//提出者の一覧 %>
	<div class="margin_box2" style="height:520px;">
		<div class="box2">
			<table class="task">
				<tr>
					<th>課題名</th>
					<td>
						<%
							out.println(result1.getTaskName());
						%>
					</td>
				</tr>
				<tr>
					<th>配布先</th>
					<td>
						<%
							out.println(result1.getName());
						%>
					</td>
				</tr>
				<tr>
					<th>期限</th>
					<td>
						<%
							out.println(date[0]);
						%>
					</td>
				</tr>
			</table>
			<div class="tasl_status">
				<div class="intro_margin">
					<div class="introduction">
						<table class="content_tablelock2">
							<thead class="task_thead2">
								<tr>
									<th class="Distribution_Number">学籍番号</th>
									<th class="Task_Distribution">課題提出者</th>
									<th class="Submission_Day">提出日時</th>
								</tr>
							</thead>
							<tbody class="task_tbody2">
								<%
									j = 0;
									while(j < submitted.size()) {
										Submitted result = (Submitted)submitted.get(j);%>
										<tr>
											<td class="Distribution_Number"><%=result.getsID()%></td>
											<td class="Task_Distribution"><%=result.getsName() %></td>
											<td class="Submission_Day"><%=date2[j] %></td>
										<tr>
									<%	j = j + 1;
									}
								%>
							</tbody>
						</table>
					</div>
				</div>
				<% //未提出者の一覧 %>
				<div class="no_introduction">
					<table class="content_tablelock3">
						<thead class="task_thead2">
							<tr>
								<th class="No_Submission_Number">学籍番号</th>
								<th class="No_Submission">課題未提出者</th>
							</tr>
						</thead>
						<tbody class="task_tbody3">
							<%
								j = 0;
								while (j < unSubmitted.size()) {
									UnSubmitted result = (UnSubmitted) unSubmitted.get(j);%>
									<tr>
										<td class="No_Submission_Number"><%=result.getsID()%></td>
										<td class="No_Submission"><%=result.getsName()%></td>
									<tr>
								<%	j = j + 1;
								}%>
					</table>
				</div>
			</div>
			<div class="margin_top">
				<form action="/TaskManage/TaskContent" method="POST">
				    <input type="submit" value="←" class="square_btn" style="float:left; margin:0 0 10 100;">
				    <input type="hidden" value="<%=flg%>" name="flg">
				</form>
			</div>
		</div>
	</div>
	</main>
</body>
</head>
</html>