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
	<script type="text/javascript">
		<!--
		function AllChecked(){
		  var check =  document.checkform.checkbox.checked;

		  for (var i=0; i<document.checkform.userID.length; i++){
		    document.form.useID[i].checked = check;
		  }
		}
		//-->
	</script>
	<form action="/TaskManage/UserIndex" method="POST">
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
	</form>
	<form action="/TaskManage/UserIndex" method="POST" name="checkform">
		<label><input type="checkbox" name="checkbox" onClick="AllChecked();">全選択</label>
		<table>
		<%
			int j = 0;
			if(resultList != null){
			while(j < resultList.size()) {
				UserIndex result = (UserIndex)resultList.get(j);
				System.out.println(result.getUserID() + "," + result.getUser());
					//ユーザの判定
					int valLen = String.valueOf(result.getUserID()).length();
					if(valLen == 8){
						//教員
						out.println("<tr>");
						out.println("<input type=hidden name=tID value=" + result.getUserID() + ">");
						out.println("<td><input type=submit value=" + result.getUser() + "></td>"
							+ "<td><input class=select_delete type=checkbox name=userID value="
							+ result.getUserID() + "onClick=DisChecked();></td> ");
						out.println("</tr>");
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
		<input type="submit" class="square_btn" value="選択削除"><br>
		</form>
	</main>
</body>
</html>