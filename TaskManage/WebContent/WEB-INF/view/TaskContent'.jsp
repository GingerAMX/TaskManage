<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dao.ManageDAO"%>
<%@page import="dto.TaskContent"%>
<%@page import="dto.Students"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
    <meta charset=utf-8">
    <title>課題詳細</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	ArrayList<TaskContent> resultList = (ArrayList<TaskContent>)request.getAttribute("resultList");
	String[] key = (String[])request.getAttribute("key");
	String flg = (String)request.getAttribute("flg");

	//日付への変更
	int i = 0;
	String[] date;
	date = new String[1];
	TaskContent result = (TaskContent)resultList.get(0);
	String a = Integer.toString(result.getDeadline());
	String b = a.substring(0, 4) + "年" + a.substring(4, 6) + "月" + a.substring(6, 8) + "日";
	date[i] = b;
	%>
    <header>
        <a href="/TaskManage/Login" class="square_btn" style="float:right">ログアウト</a>
        <h1>課題詳細</h1>
        <hr>
    </header>
    <main>
      <div class="margin_box">
        <div class="box">
            <table class="task">
                <tr>
                    <th>課題名</th>
                    <td>
                    	<% out.println(result.getTaskName()); %>
                    </td>
                </tr>
                <tr>
                    <th>配布先</th>
                    <td>
                    	<% out.println(result.getName()); %>
                    </td>
                </tr>
                <tr>
                    <th>期限</th>
                    <td>
                    	<% out.println(date[i]); %>
                    </td>
                </tr>
            </table>
			<p class="detail">
				<label>内容<br>
				<textarea class="textarea" name="task_detail" rows="8" cols="45" readonly>
					<% out.println(result.getText()); %>
				</textarea></label>
			</p>
            <div class="teacher_detail_margin">
                <div class="teacher_detail1">
                <%
					if ("true".equals(flg)) {
						%>
						<form action="/TaskManage/ManagerPage" method="POST">
							<input type="submit" value="←" class="square_btn" style="float: left; width: 30%; font-size: 40px;" >
						</form>
						<%
							} else if ("false".equals(flg)) {
						%>
						<form action="/TaskManage/TeacherPage" method="POST">
							<input type="submit" value="←" class="square_btn" style="float: left; width: 30%; font-size: 40px;">
						</form>
						<%
					}
				%>
                </div>
                <div class="teacher_detail2">
	                <form action="/TaskManage/Status" method="POST">
		                <input type="hidden" value="<%=flg%>" name="flg">
		                <input type="submit" value="提出状況へ" class="square_btn" style="float: left; width: 100% ;font-size: 30px;">
	                </form>
                </div>
                <div class="teacher_detail3">
                <form action="/TaskManage/Delete" method="POST">
					<input type="submit" value="×" class="square_btn" style="float: right; width: 30%; font-size: 40px;">
					<input type="hidden" name="taskID" value="<%=result.getTaskID()%>">
				</form>
                </div>
            </div>
        </div>
      </div>
    </main>
</body>
</html>