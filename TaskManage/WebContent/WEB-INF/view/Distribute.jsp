<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>課題配布</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/task_distribution.css">
</head>
<body>
<header>
	<a href="/TaskManage/Login" class="square_btn"style="float:right">ログアウト</a>
	<%
	request.setCharacterEncoding("UTF-8");
	String flg = (String)request.getAttribute("flg");
		if ("true".equals(flg)) {
		%>
			<form action="/TaskManage/ManagerPage" method="POST">
				<input type="submit" value="←" class="square_btn" style="height: 60px;">
			</form>
		<%
		} else if ("false".equals(flg)) {
		%>
			<form action="/TaskManage/TeacherPage" method="POST">
				<input type="submit" value="←" class="square_btn" style="height: 60px;">
			</form>
		<%
		}
	%>
	<h1>課題配布</h1>
	<hr>
</header>
	<main>
		<div class="task_distribution_margin">
			<div class="box">
				<form action="/TaskManage/Distribute" method="POST">
					<p class="task_name"><label>課題名 :<input type="text" class="name" name="taskName"></label></p>
					<textarea class ="textarea2" name="content" rows="10" cols="75" placeholder="課題内容"></textarea>
					<p class="Distribution" style="font-size: 32px;margin: 10px 100px 10px 0px;">
					<label for="pref">配布先 : </label>
	                <select name="grade" class="pref">
	                    <option value="" selected>学年</option>
	                    <option value="1">1</option>
	                    <option value="2">2</option>
	                    <option value="3">3</option>
	                    <option value="4">4</option>
	                </select>
	                <select name="cName" class="pref">
	                    <option value="" selected>クラス</option>
	                    <option value="1">1</option>
	                    <option value="2">2</option>
	                </select>
	            	<div class="under_margin">
	                	<div class="deadline_margin">
	            			<p class="Thedeadline"><label>期　限 :
	            			<input type="text" class="pref" name="deadline" placeholder="例）20180101"></label></p>
	            		</div>
		                <div class="btn_margin">
		                    <input type="submit" value="配布" class="square_btn">
		                    <input type="hidden" value="<%=flg%>" name="flg">
		                </div>
	            	</div>
	            </form>
			</div>
		</div>
	</main>
</body>
</html>