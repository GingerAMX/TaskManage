<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="POST" enctype="multipart/form-data" action="/TaskManage/UploadServlet">
<input type="file" name="file"/><br />
<input type="submit" value="アップロード" />
</form>
</body>
</html>