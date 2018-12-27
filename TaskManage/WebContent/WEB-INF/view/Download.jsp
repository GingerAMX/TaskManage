<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.Download"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	String zipPath = (String)request.getAttribute("zipPath");
	%>
	<form action="/TaskManage/Download" method="post">

		<p>課題名）：<label><input type="text" name="taskName"></label></p>
		<p>学　年）：<label><input type="text" name="grade"></label></p>
		<p>クラス）：<label><input type="text" name="class"></label></p>
		<input type="submit" value="検索">
	</form>
	<%
		out.println("<a href=" + zipPath + " download>download</a>");
		out.println("<form action=\"/TaskManage/Status\" method=\"POST\">");
	    out.println("<input type=\"submit\" value=\"←\">");
	    out.println("</form>");
	%>
</body>
</html>