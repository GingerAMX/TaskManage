<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	String[] list = (String[])request.getAttribute("list");
	out.println("<form action=\"/TaskManage/UserIndex\" method=\"POST\">");
	out.println("<input type=\"hidden\" name=\"teacher\" value=\"教員\">");
	out.println("<input type=\"submit\" value=\"←\">");
	out.println("</form>");
	if(list[2] != null){		//ユーザに管理者権限があった場合
		out.println("<form action=/TaskManage/Authority method=POST>");
		out.println("<p>現在、" + list[1] + " さんは権限が与えられています。</p>");
		out.println("<input type=hidden value=true name=takeover>");
		out.println("<input type=submit value=権限のはく奪>");
		out.println("</form>");

	} else {					//ユーザに管理者権限がなかった場合
		out.println("<form action=/TaskManage/Authority method=POST>");
		out.println("<p>現在、" + list[1] + " さんは権限が与えられていません。<br>"
						+ "管理者の権限を付与する場合、パスワードが必要となります。設定してください。</p>");
		out.println("<p>管理者パスワード：<label><input type=text name=mPass></label></p>");	//パスワード
		out.println("<input type=submit value=権限の付与>");
		out.println("</form>");
	}
	%>
</body>
</html>