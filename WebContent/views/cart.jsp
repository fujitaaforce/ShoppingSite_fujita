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
hiddenLogin = "hidden";
}
%>
<!DOCTYPE html>
<html>
<head>
<link href = "../css/sitemodel.css" rel = "stylesheet"></link>
<meta charset="UTF-8">
<title>ショッピングカート</title>
</head>
<body class=background>
	<a href="/ShoppingSite"><button class=top>トップ</button></a>
	<div class=itemlist>
<c:forEach items="${ requestScope.itemList}" var="item">
		<form action="/ShoppingSite/views/UpdateCart" method="get">
			<p>
				<a href="/ShoppingSite/views/Item?id=${item.id} ">
				<img style="width: 100px; height: 140px"
					src="/ShoppingSite/ImageServlet?id=${item.id }"></a>
					<a href="/ShoppingSite/views/Item?id=${item.id} "><c:out value="${ item.itemname }" /></a>
				小計：<c:out value="${ item.itemprice }" />円(税込み)
				<input type=hidden name="item_id" value="${item.id}">
				<input type=hidden name="item_name" value="${item.itemname}">
				数量：<input type=number name="buy_no" value="${item.stockno}" min="0" size="1" class=number>
				<input type="submit" value="商品数変更">
			</p>
		</form>
	</c:forEach>
	合計：${requestScope.total}円(税込み)
	<form action="/ShoppingSite/views/Order" method="get">
	<input type="submit" value="注文する">
	</form>
	</div>
</body>
</html>