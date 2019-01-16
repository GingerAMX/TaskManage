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
	String flg = (String)request.getAttribute("flg");
	%>
	<form action="/TaskManage/Download" method="post">

		<p>課題名）：<label><input type="text" name="taskName"></label></p>
		<p>学　年）：<label><input type="text" name="grade"></label></p>
		<p>クラス）：<label><input type="text" name="class"></label></p>
		<input type="hidden" name="flg" value="<%=flg%>">
		<input type="submit" value="取得">
	</form>
	<a href="<%=zipPath%>" download>download</a>
	<% if("true".equals(flg)){ %>
		<form action="/TaskManage/ManagerPage" method="POST">
		    	<input type="submit" value="←">
		</form>
	<% }else if("false".equals(flg)){ %>
		<form action="/TaskManage/TeacherPage" method="POST">
		    	<input type="submit" value="←">
		</form>
	<% } %>

</body>
</html>