<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dto.Submitted"%>
<%@ page import="dto.UnSubmitted"%>
<html>
<head>
<meta charset="UTF-8">
<title>課題提出状況</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		ArrayList<Submitted> submitted = (ArrayList<Submitted>) request.getAttribute("submitted");
		ArrayList<UnSubmitted> unSubmitted = (ArrayList<UnSubmitted>) request.getAttribute("unSubmitted");
		//日付への変更
		int i = 0;
		String[] date;
		date = new String[30];
		while (i < submitted.size()) {
			Submitted result = (Submitted) submitted.get(i);
			String a = Integer.toString(result.getDate());
			String b = a.substring(0, 4) + "年" + a.substring(4, 6) + "月" + a.substring(6, 8) + "日";
			date[i] = b;
			i = i + 1;
		}
	%>
	<header>
		<a href="#" class="square_btn" style="float: right">ログアウト</a>
		<h1>課題提出状況</h1>
		<hr>
	</header>
	<main>
	<div class="margin_box2">
		<div class="box2">
			<%
				//提出者の一覧
			%>
			<table class="task">
				<tr>
					<th>課題名</th>
					<td>###</td>
				</tr>
				<tr>
					<th>期限</th>
					<td>###</td>
				</tr>
				<tr>
					<th>提出先</th>
					<td>###</td>
				</tr>
			</table>
			<div class="task_status">
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
									int j = 0;
									while (j < submitted.size()) {
										Submitted result = (Submitted) submitted.get(j);
										out.println("<tr>");
										out.println("<td class=\"Distribution_Number\">" + result.getsID() + "</td>"
												+ "<td class=\"Task_Distribution\">" + result.getsName() + "</td>"
												+ "<td class=\"Submission_Day\">" + date[j] + "</td>");
										out.println("</tr>");
										j = j + 1;
									}
								%>
							</tbody>
						</table>
					</div>
				</div>
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
									UnSubmitted result = (UnSubmitted) unSubmitted.get(j);
									out.println("<tr>");
									out.println("<td class=\"No_Submission_Number\">" + result.getsID() + "</td>"
											+ "<td class=\"No_Submission\">" + result.getsName() + "</td>");
									out.println("</tr>");
									j = j + 1;
								}
							%>
						</tbody>
					</table>
				</div>
			</div>
			<div class="margin_top">
				<a href="#" class="square_btn" style="float: left">←</a> <a href="#"
					class="square_btn" style="float: left">ファイル取得</a>
			</div>
		</div>
	</div>
	</main>
</body>
</html>