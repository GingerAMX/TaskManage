<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<main>
	<div class="margin_login">
		<div class="box4">
			<form action=/TaskManage/Login method=POST>
				<%
				out.println("<table border=0 align=center>");
					out.println("<tr><td>ID :</td>"
						+ "<td><input type=text name=ID maxlength=8"
						+ "placeholder=文字制限8字まで></td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td>パスワード :</td>"
						+ "<td><input type=password name=pass maxlength=16"
						+ "placeholder=文字制限16字まで></td>"
						+ "</tr>");
				out.println("</table>");
				out.println("<input type=submit value=ログイン class=square_btn>");
				%>
			</form>
			<p></p>
			<a href="/TaskManage/Register" class="square_btn">新規登録画面へ</a>
		</div>
	</div>
	</main>
</body>
</html>