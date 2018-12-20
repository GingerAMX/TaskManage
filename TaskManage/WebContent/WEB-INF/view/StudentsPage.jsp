<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dao.ManageDAO"%>
<%@page import="dto.TaskIndex"%>
<%@page import="dto.Students"%>
<%@page import="java.util.ArrayList" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>課題一覧</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
		<%
	request.setCharacterEncoding("UTF-8");
	ArrayList<TaskIndex> resultList = (ArrayList<TaskIndex>)request.getAttribute("resultList");
	String key = (String)request.getAttribute("key");
	//日付への変更
	int i = 0;
	String[] date;
	date = new String[30];
	while(i < resultList.size()) {
		TaskIndex result = (TaskIndex)resultList.get(i);
		String a = Integer.toString(result.getDeadline());
		String b = a.substring(0, 4) + "年" + a.substring(4, 6) + "月" + a.substring(6, 8) + "日";
		date[i] = b;
		i = i + 1;
	}
		%>
    <header>
        <a href="/TaskManage/Login" class="square_btn"style="float:right">ログアウト</a>
        <h1>課題一覧</h1>
        <hr>
    </header>
    <main>
      <div class="margin_box2">
        <div class="box2">
            <table class="content_tablelock1">
                <thead class="task_thead">
                    <tr>
                      <th class="TaskID">課題ID</th>
                      <th class="TaskName">課題名</th>
                      <th class="DeadLine">期限</th>
                      <th class="SubMission">提出先</th>
                      <th class="BUTTON"></th>
                    </tr>
                </thead>
                <tbody class="task_tbody">
                  <%
				int j = 0;
				while(j < resultList.size()) {
					TaskIndex result = (TaskIndex)resultList.get(j);

					if(j == 0) {
						out.println("<form action=\"/TaskManage/TaskContent\" method=\"POST\"></form>");
					}
					out.println("<form action=\"/TaskManage/TaskContent\" method=\"POST\">");
					out.println("<input type=\"hidden\" name=\"taskID\" value=" + result.getTaskID() + ">");
					out.println("<input type=\"hidden\" name=\"userID\" value=" + key + ">");
					out.println("<tr>");
						out.println("<td class=\"TaskID\">" + result.getTaskID() + "</td>"
						+ "<td class=\"TaskName\">"+ result.getTaskName() + "</td>"
						+ "<td class=\"DeadLine\">" + date[j] + "</td>"
						+ "<td class=\"SubMission\">"+ result.gettName() + "</td>"
						+ "<th class=\"BUTTON\" ><input type=\"submit\" value=\"＞\"></th>");
					out.println("<tr>");
					out.println("</from>");

					j = j + 1;
				}
				%>
                </tbody>
            </table>
        </div>
      </div>
    </main>
</body>
</html>