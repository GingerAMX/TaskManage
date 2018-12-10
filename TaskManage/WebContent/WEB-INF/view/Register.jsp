<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/TaskManage/Register" method="POST">
		<p>学籍番号：<label><input type="text" name="sID"></label></p>
		<p>名前：<label><input type="text" name="sName"></label></p>
		<p>学年：<label>
					<select name="grade">
						<option value="0" selected>-----------</option>
						<option value="1">１</option>
						<option value="2">２</option>
						<option value="3">３</option>
						<option value="4">４</option>
					</select>
		</label></p>
		<p>クラス：<label>
					<select name="cName">
						<option value="0" selected>-----------</option>
						<option value="1">１</option>
						<option value="2">２</option>
						<option value="3">３</option>
					</select>
		</label></p>
		<p>パスワード：<label><input type="text" name="sPass"></label></p>
		<input type="submit" value="登録">
	</form>
	<form action="/TaskManage/Register" method="POST">
		<p>教員ID：<label><input type="text" name="tID"></label></p>
		<p>名前：<label><input type="text" name="tName"></label></p>
		<p>パスワード：<label><input type="text" name="tPass"></label></p>
		<input type="submit" value="登録">
	</form>
</body>
</html>