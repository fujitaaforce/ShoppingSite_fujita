<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link href = "../css/sitemodel.css" rel = "stylesheet"></link>
</head>
<body class=background>
	<a href="/ShoppingSite"><button class=top>トップ</button></a>
	<div class=mypagetext>
<!-- 入力フォーム -->
<form action="/ShoppingSite/views/Login"method="post">

  <!-- 入力項目 -->
    <p>
        <span id = emsg>${requestScope.loginBean.emsg}</span>
  </p>
  <p>
        <span id = username>ユーザーID</span><input type="text"name="email"size="20">
  </p>
  <p>
			<span id  = password>パスワード:</span> <input type="password"name="password"size="20">
  </p>
  <p>

  <!-- ログインボタン -->
		<input type="submit" value="ログイン"  class=loginsubmit>
</form>
<p>
会員登録がまだの方
</p>
<p>
		<a href="./signup.jsp"><button  class=loginsubmit>新規登録</button></a>
</p>
<a href ="/ShoppingSite"><input type=button value = "トップへ戻る"   class=loginsubmit></a>
</div>
</body>
</html>