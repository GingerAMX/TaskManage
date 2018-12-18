<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
    window.onload = function() {
        var str = "";
        str += "保存内容 ： " + getCookie() + "<br>\n";
        document.getElementById("dat").innerHTML = str;
    }

    function getCookie() {
        var cookieStr = document.cookie + ";";
        var startNum = cookieStr.indexOf("cookie_data");
        var txt = "";
        if (startNum != -1) {
            var endNum = cookieStr.indexOf(";", startNum);

            txt = unescape(cookieStr.substring(startNum + "cookie_data".length + 1, endNum));
        }
        return txt;

    }

    function saveCookie() {
        //入力内容を取得
        var txt = document.getElementById("id").value;
        //日付データを作成
        var day = new Date();
        //クッキーの保存期間
        day.setTime(day.getTime() + 5 * 60 * 1000);
        //入力内容と日付データ

        document.cookie = "cookie_data=" + escape(txt) + ";expires=" + day.toGMTString();
        //ページをリロード
        //
        location.reload();
    }

    function delCookie() {
        exd4
        //日付データを作成
        var day = new Date();
        //過去の日付データ（1970年1月1日00:00:00）
        day.setTime(0);
        //有効期限を過去に設定
        document.cookie = "cookie_data=;expires=" + day.toGMTString();
        //ページをリロード
        location.reload();
    }

</script>
	<main>
	<div class="margin_login">
		<div class="box4">
			<form action=/TaskManage/Login method=POST>
				<%
				out.println("<table border=0 align=center>");
					out.println("<tr><td>ID :</td>"
						+ "<td><input type=text name=ID maxlength=8"
						+ "placeholder=文字制限8字まで id=id></td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td>パスワード :</td>"
						+ "<td><input type=password name=pass maxlength=16"
						+ "placeholder=文字制限16字まで></td>"
						+ "</tr>");
				out.println("</table>");
				out.println("<input type=submit value=ログイン class=square_btn onclick=return saveCookie()>");
				%>
			</form>
			<p></p>
			<a href="/TaskManage/Register" class="square_btn">新規登録画面へ</a>
		</div>
	</div>
	</main>
</body>
</html>