<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dao.ManageDAO"%>
<%@page import="dto.TaskContent"%>
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
	ArrayList<TaskContent> resultList = (ArrayList<TaskContent>)request.getAttribute("resultList");

	//日付への変更
	int i = 0;
	String[] date;
	date = new String[1];
	TaskContent result = (TaskContent)resultList.get(0);
	String a = Integer.toString(result.getDeadline());
	String b = a.substring(0, 4) + "年" + a.substring(4, 6) + "月" + a.substring(6, 8) + "日";
	date[i] = b;

	//課題内容詳細

	out.println(result.getTaskName());
	out.println(result.gettName());
	out.println(date[i]);
	out.println(result.getText());
	%>
</body>
</html>