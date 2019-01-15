<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>課題提出</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>

<body>
<% String[] key = (String[])request.getAttribute("key"); %>
    <header>
          <a href="#" class="square_btn"style="float:right">ログアウト</a>
          <h1>課題提出</h1>
    <hr>
    </header>
	<main>
		<div class="margin_login">
			<div class="box4">
				<form action="/TaskManage/Submit" method="post" enctype="multipart/form-data">
		            <p>
		                <label for="task_submission" class="submission_font">提出ファイル<br>
		                    <input type="file" id="task_submission"name="file" required >
		                </label>
		            </p>
					<input class="square_btn" type="submit" style="width:130px;height:70px;" value="提出">
				</form>
		        <div class="submission_margin">
				<%
					out.println("<form action=\"/TaskManage/TaskContent\" method=\"POST\">");
				    out.println("<input type=\"submit\" class=\"square_btn\" style=\"width:130px;height:70px;\" value=\"←\">");
				    out.println("</form>");
				%>
					<form action="/TaskManage/TaskContent" method="POST">
						<input type="submit" value="←">
					</form>
		        </div>
			</div>
		</div>
	</main>
</body>
</html>