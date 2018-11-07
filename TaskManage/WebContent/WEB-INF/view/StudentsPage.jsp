<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dao.ManageDAO"%>
<%@page import="dto.TaskIndex"%>
<%@page import="dto.Students"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");

	ArrayList<TaskIndex> resultList = (ArrayList<TaskIndex>)request.getAttribute("resultList");


	//日付への変更
	int i = 0;
	String[] date;
	date = new String[30];
	while(i < resultList.size()) {
		TaskIndex result = (TaskIndex)resultList.get(i);
		String a = Integer.toString(result.getDeadline());
		String b = a.substring(0, 4) + "年" + a.substring(4, 6) + "月" + a.substring(6, 8) + "日";
		date[i] = b;
		i = i + 1;
	}
	%>

	<% //課題内容一覧の表示 %>
	<table border="1" align="center" id="delete">
				<tr>
					<th>課題名</th><th>期限</th><th>提出先</th>
				</tr>
				<%
				int j = 0;
				while(j < resultList.size()) {
					TaskIndex result = (TaskIndex)resultList.get(j);
					out.println("<tr>");
					out.println("<td>" + result.getTaskName() + "</td>" + "<td>" + date[j] + "</td>"
					+ "<td>"+ result.gettName() + "</td>");
					out.println("<tr>");
					j = j + 1;
				}
				%>
	</table>
</body>
</html>