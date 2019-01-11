<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dao.ManageDAO"%>
<%@page import="dto.DistributionIndex"%>
<%@page import="dto.UserIndex"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ管理</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	ArrayList<UserIndex> resultList = (ArrayList<UserIndex>)request.getAttribute("resultList");
%>
<header>
	<a href="/TaskManage/Login" class="square_btn" style="float: right">ログアウト</a>
	<h1>ユーザ管理</h1>
	<hr>
</header>
<main>
<div class="margin_box">
	<div class="box">
		<div class="select_font">
			<form action="/TaskManage/UserIndex" method="POST">
				<select name="grade" class="select_btn">
					<option value="" selected>学年</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select> <select name="cName" class="select_btn">
					<option value="" selected>クラス</option>
					<option value="1">1</option>
					<option value="2">2</option>
				</select>
				<input type="submit" class="square_btn" value="更新" style="width: 140px; height: 60px;">
				<input type="submit" class="square_btn" value="教員" style="width: 140px; height: 60px;">
			</form>
		</div>
		<form action="/TaskManage/UserIndex" method="POST" name="checkform">
			<div class="namelist_margin">
				<div class="namelist1">
					<label><input type="checkbox" name="checkbox"
						class="all_check" id="AllCheck">全選択</label>
					<div class="namelist_box">
						<table>
							<%
							if(resultList != null){
								for(int j = 0; j < resultList.size(); j++) {
									UserIndex result = resultList.get(j);
										//表示するユーザの判定
										int valLen = String.valueOf(result.getUserID()).length();
										if(valLen == 8){
											//教員
									%>
											<tr>
												<td>
													<input type=hidden name="tID" value="<%=result.getUserID()%>">
													<input type="button" value="<%=result.getUser()%>" onclick="authority(<%=result.getUserID()%>);">
												</td>
												<td>
													<input class=select_delete type=checkbox name=userID value=<%=result.getUserID()%>>
												</td>
											</tr>
										<%
										}else if(valLen == 7){
											//学生
									%>
											<tr>
												<td><%=result.getUser() %></td>
												<td>
													<input class=select_delete type=checkbox name=userID value=<%=result.getUserID()%>>
												</td>
											</tr>
									<%
										}
									}
								}
							%>
						</table>
					</div>
				</div>
				<div class="namelist2">
					<input type="submit" class="square_btn" value="選択削除"
						style="width: 145px; height: 60px;"><br>
					<div class="delete_font_margin">
						<input type="submit" value="←" formaction="/TaskManage/ManagerPage" formmethod="POST">
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
</main>
<form name="authorityForm" action="/TaskManage/Authority" method="POST">
	<input type="hidden" name="tID" value="">
</form>
<script type="text/javascript">
	var checkall = document.getElementById('AllCheck');
    checkall.addEventListener('click', function () {
    	var checkboxes = document.getElementsByClassName('select_delete');
        for (i in checkboxes) {
        	checkboxes[i].checked = this.checked;
        }
    });

    function authority(userID) {
    	document.forms.authorityForm.elements.tID.value = userID;
        document.forms.authorityForm.submit();
    }
</script>
</body>
</html>