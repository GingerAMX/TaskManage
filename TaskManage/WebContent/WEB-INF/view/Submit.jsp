<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
</head>

<body>
	<form action="/TaskManage/Submit" method="post" enctype="multipart/form-data">
		<p>ファイル：
		<input id="file" name="file" type="file" required>
		</p>

		<input type="submit" value="提出">
	</form>
</body>
</html>