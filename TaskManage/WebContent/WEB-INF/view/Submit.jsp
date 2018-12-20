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
        	<div class="submission_margin">
		        <a href="#" class="square_btn" style="text-align: left">←</a>
		        <% out.println("<input type=\"hidden\" name=\"userID\" value=" + key[0] + ">"); %>
		        <% out.println("<input type=\"hidden\" name=\"taskID\" value=" + key[1] + ">"); %>
		        <input class="square_btn" type="submit" style="width:150px;height:78px;" value="提出">
        	</div>
        </form>
      </div>
     </div>
    </main>
</body>
</html>