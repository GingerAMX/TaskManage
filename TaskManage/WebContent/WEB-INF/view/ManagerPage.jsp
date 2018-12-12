<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dao.ManageDAO"%>
<%@page import="dto.DistributionIndex"%>
<%@page import="dto.Students"%>
<%@page import="dto.Manager" %>
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

	ArrayList<DistributionIndex> resultList = (ArrayList<DistributionIndex>)request.getAttribute("resultList");


	//日付への変更
	int i = 0;
	String[] date;
	date = new String[30];
	while(i < resultList.size()) {
		DistributionIndex result = (DistributionIndex)resultList.get(i);
		String a = Integer.toString(result.getDeadline());
		String b = a.substring(0, 4) + "年" + a.substring(4, 6) + "月" + a.substring(6, 8) + "日";
		date[i] = b;
		i = i + 1;
	}
	%>

	<% //課題一覧の表示 %>
	<table border="1" align="center" id="delete">
				<tr>
					<th>課題ID</th><th>課題名</th><th>期限</th><th>配布先</th><th></th>
				</tr>
				<%
				int j = 0;
				while(j < resultList.size()) {
					DistributionIndex result = (DistributionIndex)resultList.get(j);
					out.println("<tr>");
					out.println("<td>" + result.getTaskID() + "</td>" + "<td>" + result.getTaskName() + "</td>"
					+ "<td>" + date[j] + "</td> "
					+ "<td>"+ result.getGrade() + "年" + result.getcName() + "組" + "</td>"+ "<td>＞</td>");
					out.println("<tr>");
					j = j + 1;
				}
				%>
	</table>
</body>
</html>