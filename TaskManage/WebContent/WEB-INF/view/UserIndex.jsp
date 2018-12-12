<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dao.ManageDAO"%>
<%@page import="dto.DistributionIndex"%>
<%@page import="dto.UserIndex"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");

	ArrayList<UserIndex> resultList = (ArrayList<UserIndex>)request.getAttribute("resultList");
	%>
	<main>
	<div class="margin_box">
		<div class="box">
			<form action="/TaskManage/UserIndex" method="POST">
				<div class="select_font">
					<select name="grade" class="select_btn">
						<option value="0" selected>学年</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
					</select> <select name="cName" class="select_btn">
						<option value="0" selected>クラス</option>
						<option value="1">1</option>
						<option value="2">2</option>
					</select>
					<input type="submit" value="更新">
					<input type="submit" class="square_btn" value="教員" name="teacher">
					<a href="#" class="square_btn">←</a>
				</div>
			</form>
			<form action="/TaskManage/UserIndex" method="POST">
				<div class="namelist_margin">
					<div class="namelist1">
						<div class="namelist_box">
							<table>
								<%
								int j = 0;
								System.out.println("1");
								if(resultList != null){
									while(j < resultList.size()) {
										UserIndex result = (UserIndex)resultList.get(j);

										//ユーザの判定
										int valLen = String.valueOf(result.getUserID()).length();
										if(valLen == 8){
											System.out.println("2");
											//教員
											out.println("<tr>");
											out.println("<td><input type=submit value=" + result.getUser() + "></td>"
											+ "<td><input class=select_delete type=checkbox name=userID value="
											+ result.getUserID() + "></td> ");
											out.println("<tr>");
											j = j + 1;

										}else if(valLen == 7){
											//学生
											out.println("<tr>");
											out.println("<td>" + result.getUser() + "</td>"
											+ "<td><input class=select_delete type=checkbox name=userID value="
											+ result.getUserID() + "></td> ");
											out.println("<tr>");
											j = j + 1;
										}
									}
								}
								%>
							</table>
						</div>
					</div>
					<div class="namelist2">
						<input type="submit" class="square_btn" value="選択削除"><br>
						<div class="delete_font_margin">
							<input type="submit" class="square_btn" value="一括削除">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	</main>
</body>
</html>