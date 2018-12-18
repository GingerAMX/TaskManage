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
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");

		ArrayList<Submitted> submitted = (ArrayList<Submitted>)request.getAttribute("submitted");
		ArrayList<UnSubmitted> unSubmitted = (ArrayList<UnSubmitted>)request.getAttribute("unSubmitted");
		ArrayList<TaskContent> resultList = (ArrayList<TaskContent>)request.getAttribute("resultList");

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

	<%
	//課題の情報
		out.println(result1.getTaskName());
		out.println(result1.getName());
		out.println(date[0]);
	//提出者の一覧 %>
	<table border="1" align="center">
		<tr>
			<th>学籍番号</th><th>学生名</th><th>提出日</th>
		</tr>
		<%
			j = 0;
			while(j < submitted.size()) {
				Submitted result = (Submitted)submitted.get(j);
				out.println("<tr>");
				out.println("<td>" + result.getsID() +
						"</td>" + "<td>" + result.getsName() + "</td>" + "<td>" + date2[j] + "</td>");
				out.println("<tr>");
				j = j + 1;
			}
		%>
	</table>

	<% //未提出者の一覧 %>
	<table border="1" align="center">
		<tr>
			<th>学籍番号</th><th>学生名</th>
		</tr>
	<%
		j = 0;
		while(j < unSubmitted.size()) {
			UnSubmitted result = (UnSubmitted)unSubmitted.get(j);
			out.println("<tr>");
			out.println("<td>" + result.getsID() +
					"</td>" + "<td>" + result.getsName() + "</td>");
			out.println("<tr>");
			j = j + 1;
		}
	%>
	</table>
</body>
</html>