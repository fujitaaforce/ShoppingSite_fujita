<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Map" %>
<%
Map<String, String> map = (Map<String, String>)session.getAttribute( "login_user" );
String userEmail = "";
String userName = "";
String userRuby = "";
String userPassword = "";
String jspText ="/ShoppingSite/views/mypage.jsp";
String hiddenLogin = "";
String hiddenLogout = "";
if ( null == map ) {
// ログアウト中.
hiddenLogout = "hidden";
} else {
// ログイン中.
userEmail = (String)map.get( "Email" );
userName = (String)map.get( "Name" );
userRuby = (String)map.get( "Ruby" );
userPassword = (String)map.get( "Password" );
if((String)map.get("admin")!=null){
	jspText ="/ShoppingSite/views/admin.jsp";
}
hiddenLogin = "hidden";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href = "${requestScope.login}css/sitemodel.css" rel = "stylesheet">
<title>TOP</title>
</head>
<body class=background>
	<a href="/ShoppingSite"><button class=top>トップ</button></a>
	<form action="/ShoppingSite/views/ItemList" method="get" class=search>
			<select name="title" class=title>
				<option value="" selected>タイトル</option>
				<option value="DM">デュエマ</option>
				<option value="PCG">ポケカ</option>
				<option value="MTG">MTG</option>
				</select>
				<select name="category" class=category>
				<option value="" selected>カテゴリ</option>
				<option value="single">シングルカード</option>
				<option value="supply">カードサプライ</option>
				<option value="puck">拡張パック</option>
				<option value="deck">構築済みデッキ</option>
				</select>
				<input type="search" name="search" placeholder="キーワードを入力" class=textbox size="40">
			<input type="submit" name="submit" value="検索" class=submit>
</form>
	<div <%=hiddenLogin%> class=log>
		<a href="/ShoppingSite/views/Signup"><button class=user>新規会員登録</button></a>
		<a href="/ShoppingSite/views/Login"><button class=logbutton>ログイン</button></a>
	</div>
	<div <%=hiddenLogout%> class=log>
		<a href=<%=jspText%>><button class=user><%=userName%>さん</button></a>
		<a href="/ShoppingSite/views/Login"><button class=logbutton>ログアウト</button></a>
		<a href="/ShoppingSite/views/Cart"><button class=cart>カート</button></a>
	</div>
</body>
</html>