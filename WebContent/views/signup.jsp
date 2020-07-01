<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link href = "../css/sitemodel.css" rel = "stylesheet"></link>
</head>
<body class=background>
	<a href="/ShoppingSite"><button class=top>トップ</button></a>
<div class=mypagetext>
	<p>
新規登録
<!-- 入力フォーム -->
  <form action="/ShoppingSite/views/Signup"method="post">

  <!-- 入力項目 -->
			<p>
				<span class=emsg>${requestScope.signupBean.emsg}</span><span class=smsg>${requestScope.signupBean.success}</span>
			</p>
			<p>
				<span class=font>名前</span><input type="text"  name="name" size="30">
			</p>
			<p>
				<span>フリガナ</span> <input type="text"  name="ruby" size="30">
			</p>
			<p>
			<span class=emsg>${requestScope.signupBean.emailemsg}</span>
			</p>
			<p>
				<span>e-mailアドレス</span><input type="text"  name="email" size="30">
			</p>
			<p>
			<span>パスワード</span><input type="password"  name="password" size="30">
			</p>
			<p>
			<span>パスワードの再入力</span><input type="password"  name="againpass" size="30">
			</p>
			<!-- 登録ボタン -->
<input type="submit" value="登録"  class=addresssubmit>
</form>
</div>
</html>