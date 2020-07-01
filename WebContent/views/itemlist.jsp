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
String jspText ="/ShoppingSite/views/mypage.jsp";
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
	jspText ="/ShoppingSite/views/admin.jsp";
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
<meta charset="UTF-8">
<title>商品一覧</title>
<link href = "../css/sitemodel.css" rel = "stylesheet"></link>
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
	<div>
	<form action="/ShoppingSite/views/ItemList" method="get" class=sortform id=submit_form>
			<select name="sort" id=submit_select class=sort>
				<option value="0" ${sort0}>新しい順</option>
				<option value="1" ${sort1}>古い順</option>
				<option value="2" ${sort2}>価格が高い順</option>
				<option value="3" ${sort3}>価格が低い順</option>
				</select>
				<input type=hidden name="title" value="${searchList[0]}">
				<input type=hidden name="category" value="${searchList[1]}">
				<input type=hidden name="search" value="${searchList[2]}">
	</form>
	</div>
	<div class=itemlist id=itemlist>
	<c:forEach items="${ requestScope.itemList}" var="item">
		<form action=<%=servText%> method="get">
			<p>
				<a href="/ShoppingSite/views/ItemPage?id=${item.id} ">
				<img style="width: 100px; height: 140px"
					src="/ShoppingSite/ImageServlet?id=${item.id }"></a>
				<a class=listitemname href="/ShoppingSite/views/ItemPage?id=${item.id} "><c:out value="${ item.itemname }" /></a>
				<p class=listtext>
				 <a><c:out value="${ item.itemprice }" />円(税込み)</a>
				<input type=hidden name="item_id" value="${item.id}">
				<input type=hidden name="item_name" value="${item.itemname}">
				<input type=hidden name="price" value="${item.itemprice}">
				数量：<input type=number name="buy_no" value=1 min="0" size="1" class=number>
				<input type="submit" value=<%=buttonText%>>
				</form>
				</c:forEach>
				</div>
				</body>
</html>