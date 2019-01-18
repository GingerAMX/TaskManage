<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
    <title>権限付与画面</title>
    <link rel="stylesheet" type="text/css" href="main.css">
</head>
<body>
    <header>
      <a href="/TaskManage/Login" class="square_btn"style="float: right">ログアウト</a>
      <h1>権限付与画面</h1>
      <hr>
    </header>
    <main>
      <div class="margin_box_manager">
        <div class="box_manager">
          <div class="back_btn">
          	<input type="submit" class="square_btn" value="←">
          </div>
            <div class="text_box">
				<%
				request.setCharacterEncoding("UTF-8");
		        String[] list = (String[])request.getAttribute("list");
				if("".equals(list[2])){		//ユーザに管理者権限があった場合 %>
					<form action="/TaskManage/Authority" method="POST">
						<p>現在、<%=list[1]%>さんは権限が与えられていません。<br>
						管理者の権限を付与する場合、パスワードが必要となります。設定してください。</p>
						<p>管理者パスワード<br>
						<label><input type="password" name="mPass"></label></p>
						<input type="submit" value="権限の付与" class="square_btn">
					</form>
				<%
				} else {					//ユーザに管理者権限がなかった場合 %>
					<form action="/TaskManage/Authority" method="POST">
						<p>現在、<%=list[1] %>さんは権限が与えられています。</p>
						<input type="hidden" value="true" name="takeover">
						<input type="submit" value="権限のはく奪" class="square_btn">
					</form>
				<%
				}
				%>
            </div>
        </div>
      </div>
    </main>
</body>
</html>