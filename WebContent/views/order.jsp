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
String userAdNumber ="";
String userAddress ="";
if ( null == map ) {
// ログアウト中.
hiddenLogout = "hidden";
} else {
// ログイン中.
userEmail = (String)map.get( "Email" );
userName = (String)map.get( "Name" );
userRuby = (String)map.get( "Ruby" );
userPassword = (String)map.get( "Password" );
userAdNumber = (String)map.get("adnumber");
userAddress = (String)map.get("address");
hiddenLogin = "hidden";
}
%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script src="../js/itemlist.js"></script>
<link href = "../css/sitemodel.css" rel = "stylesheet"></link>
<meta charset="UTF-8">
</head>
<title>注文詳細</title>
<body class=background>
	<a href="/ShoppingSite"><button class=top>トップ</button></a>
ご注文内容
<div class=itemlist id=itemlist>
<c:forEach items="${ requestScope.itemList}" var="item">
			<p>
				<img style="width: 100px; height: 140px"
					src="/ShoppingSite/ImageServlet?id=${item.id }">
					<a class=listitemname><c:out value="${ item.itemname }" /></a>
					<p class=listtext>
				小計：${ item.itemprice }" 円(税込み)
				数量：${item.stockno}
			</p>
	</c:forEach>
	<p>
	合計：${requestScope.total}円(税込み)
	<p>
	お届け先住所
	<p>
	郵便番号<%=userAdNumber%>
	<p>
	<%=userAddress%>
	<p>
	決済方法
	<p>
	代金引換
	<form action="/ShoppingSite/views/OrderSale" method="get">
	<input type="submit" value="注文確定">
</form>
</div>
</body>
</html>