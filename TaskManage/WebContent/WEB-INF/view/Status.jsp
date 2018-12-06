<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.Submitted" %>
<%@ page import="dto.UnSubmitted" %>
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

		//日付への変更
		int i = 0;
		String[] date;
		date = new String[30];
		while(i < submitted.size()) {
			Submitted result = (Submitted)submitted.get(i);
			String a = Integer.toString(result.getDate());
			String b = a.substring(0, 4) + "年" + a.substring(4, 6) + "月" + a.substring(6, 8) + "日";
			date[i] = b;
			i = i + 1;
		}
	%>

	<% //提出者の一覧 %>
	<table border="1" align="center">
		<tr>
			<th>学籍番号</th><th>学生名</th><th>提出日</th>
		</tr>
		<%
			int j = 0;
			while(j < submitted.size()) {
				Submitted result = (Submitted)submitted.get(j);
				out.println("<tr>");
				out.println("<td>" + result.getsID() +
						"</td>" + "<td>" + result.getsName() + "</td>" + "<td>" + date[j] + "</td>");
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