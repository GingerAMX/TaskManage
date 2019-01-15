<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>権限付与画面</title>
    <link rel="stylesheet" type="text/css" href="main.css">
</head>
<body>
    <header>
       <a href="#" class="square_btn"style="float: right">ログアウト</a>
       <h1>権限付与画面</h1>
       <hr>
    </header>
	<%
	request.setCharacterEncoding("UTF-8");
	String[] list = (String[])request.getAttribute("list");
	%>
	<form action="/TaskManage/UserIndex" method="POST">
		<input type="hidden" name="teacher" value="教員">
		<input type="submit" value="←">
	</form>
	<%
	if("".equals(list[2])){		//ユーザに管理者権限があった場合 %>
		<form action="/TaskManage/Authority" method="POST">
			<p>現在、<%=list[1]%>さんは権限が与えられていません。<br>
			管理者の権限を付与する場合、パスワードが必要となります。設定してください。</p>
			<p>管理者パスワード：<label><input type="text" name="mPass"></label></p>
			<input type="submit" value="権限の付与">
		</form>
	<%
	} else {					//ユーザに管理者権限がなかった場合 %>
		<form action="/TaskManage/Authority" method="POST">
			<p>現在、<%=list[1] %>さんは権限が与えられています。</p>
			<input type="hidden" value="true" name="takeover">
			<input type="submit" value="権限のはく奪">
		</form>
	<%
	}
	%>
    <header>
      <a href="#" class="square_btn"style="float: right">ログアウト</a>
      <h1>権限付与画面</h1>
      <hr>
    </header>
    <main>
      <div class="margin_box_manager">
        <div class="box_manager">
        <%
               	out.println("<form action=\"/TaskManage/UserIndex\" method=\"POST\">");
				out.println("<input type=\"hidden\" name=\"teacher\" value=\"教員\">");
				out.println("<input type=\"submit\" class=\"return\" value=\"←\">");
				out.println("</form>");
				%>
            <div class="text_box">
            	<%
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
            </div>
        </div>
      </div>
    </main>
</body>
</html>