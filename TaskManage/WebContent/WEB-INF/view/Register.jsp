<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
	<header>
		<h1>新規登録</h1>
		<hr>
	</header>
	<main>
	<center><span style="color: red;">${message}</span></center>
	<div class="margin_registration">
		<p id="tabcontrol">
			<a href="#tabpage1">学生</a> <a href="#tabpage2">教員</a>
		</p>
		<div id="tabbody">
			<form action="/TaskManage/Register" method="POST">
				<div id="tabpage1">
					<table>
						<tr>
							<td>学生ID</td>
							<td><input type="text" name="sID" class="registration_text"></td>
							<td>パスワード</td>
							<td><input type="password" name="sPass"
								class="registration_text"></td>
						</tr>
						<tr>
							<td>名前</td>
							<td><input type="text" name="sName"
								class="registration_text"></td>
						</tr>
					</table>
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
						<option value="3">3</option>
					</select> <sub> <input type="submit" class="square_btn"
						style="font-size: 50px;height:100px;" value="登録">
						<input type="hidden" name="flg" value="student">
					</sub>
				</div>
			</form>
			<form action="/TaskManage/Register" method="POST">
				<div id="tabpage2" style="background-color: #CEF6CE;">
					<table>
						<tr>
							<td>教員ID</td>
							<td><input type="text" name="tID" class="registration_text"></td>
							<td>パスワード</td>
							<td><input type="password" name="tPass"
								class="registration_text"></td>
						</tr>
						<tr>
							<td>名前</td>
							<td><input type="text" name="tName"
								class="registration_text"></td>
						</tr>
					</table>
					<sub> <input type="submit" class="square_btn"
						style="font-size: 50px;height:100px;" value="登録">
						<input type="hidden" name="flg" value="teacher">
					</sub>
				</div>
			</form>
		</div>
		<script type="text/javascript">
			var tabs = document.getElementById('tabcontrol')
					.getElementsByTagName('a');
			var pages = document.getElementById('tabbody')
					.getElementsByTagName('div');

			function changeTab() {
				// ▼href属性値から対象のid名を抜き出す
				var targetid = this.href.substring(this.href.indexOf('#') + 1,
						this.href.length);

				// ▼指定のタブページだけを表示する
				for (var i = 0; i < pages.length; i++) {
					if (pages[i].id != targetid) {
						pages[i].style.display = "none";
					} else {
						pages[i].style.display = "block";
					}
				}

				// ▼クリックされたタブを前面に表示する
				for (var i = 0; i < tabs.length; i++) {
					tabs[i].style.zIndex = "0";
				}
				this.style.zIndex = "10";

				// ▼ページ遷移しないようにfalseを返す
				return false;
			}

			// ▼すべてのタブに対して、クリック時にchangeTab関数が実行されるよう指定する
			for (var i = 0; i < tabs.length; i++) {
				tabs[i].onclick = changeTab;
			}

			// ▼最初は先頭のタブを選択
			tabs[0].onclick();
		</script>
	</div>
	</main>
</body>
</html>
