<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
	<main>
	<header>
		<h1>ログイン</h1>
		<hr>
	</header>
	<div class="margin_login">
		<div class="box4">
			<form action="/TaskManage/Login" method="POST">
				<table border="0" align="center">
					<tr>
						<td>ID :</td>
						<td><input type="text" name="ID" maxlength="8" placeholder="文字制限8字まで"></td>
					</tr>
					<tr>
						<td>パスワード :</td>
						<td><input type="password" name="pass" maxlength="16" placeholder="文字制限16字まで"></td>
					</tr>
				</table>
				<input type="submit" value="ログイン" class="square_btn">
			</form>
			<p></p>
			<a href="/TaskManage/Register" class="square_btn" style="width:150px;">新規登録</a>
		</div>
	</div>
	</main>
</body>
</html>