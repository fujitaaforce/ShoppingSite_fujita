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
String admin ="";
String hiddenLogin = "";
String hiddenLogout = "";
String servText ="/ShoppingSite/views/InsertCart";
String buttonText ="カートに入れる";
if ( null == map ) {
// ログアウト中.
hiddenLogout = "hidden";
} else {
// ログイン中.
userEmail = (String)map.get( "Email" );
userName = (String)map.get( "Name" );
userRuby = (String)map.get( "Ruby" );
userPassword = (String)map.get( "Password" );
hiddenLogin = "hidden";
if((String)map.get("admin")!=null){
	servText = "/ShoppingSite/views/DeleteProduct";
	buttonText = "商品情報削除";
}
}
%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script src="../js/itemlist.js"></script>
<link href = "../css/sitemodel.css" rel = "stylesheet"></link>
<meta charset="UTF-8">
<title>${item[0].itemname}</title>
</head>
<body class=background>
	<a href="/ShoppingSite"><button class=top>トップ</button></a>
	<form action="/ShoppingSite/views/ItemList" method="get" class=searchitem>
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
		<a href="/ShoppingSite/views/mypage.jsp"><button class=user><%=userName%>さん</button></a>
		<a href="/ShoppingSite/views/Login"><button class=logbutton>ログアウト</button></a>
		<a href="/ShoppingSite/views/Cart"><button class=cart>カート</button></a>
	</div>
	<div class=itempage>
	<p>
				<img style="width: 300px; height: 420px" src="/ShoppingSite/ImageServlet?id=${item[0].id }">
					<a class=itemname><c:out  value="${ item[0].itemname }" /></a>
	</p>
					<div class=explanation>
					<h3>商品説明</h3>
					<p>${item[0].explanation}</p>
					</div>
				<form action=<%=servText%> method="get">
				<p class=cartbutton>
				<c:out value="${ item[0].price }" />円(税込み)
				<input type=hidden name="item_id" value="${item[0].id}">
				<input type=hidden name="item_name" value="${item[0].itemname}">
				<input type=hidden name="price" value="${item[0].price}">
				数量：<input type=number name="buy_no" value=1 min="0" size="1" class=number>
				<input type="submit" value=<%=buttonText%>>
				</p>
				</form>
	</div>
</body>
</html>