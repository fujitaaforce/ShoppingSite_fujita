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
String userAdNumber ="";
String userAddress ="";
String admin ="";
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
userAdNumber = (String)map.get("adnumber");
userAddress = (String)map.get("address");
if(userAdNumber==null){
	userAdNumber="";
	userAddress="";
}
hiddenLogin = "hidden";
}
%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script src="../js/itemlist.js"></script>
<meta charset="UTF-8">
<title>マイページ</title>
<link href = "../css/sitemodel.css" rel = "stylesheet"></link>
</head>
<body class=background>
	<a href="/ShoppingSite"><button class=top>トップ</button></a>
	<div class=mypagetext>
  <p>
        <span id = username>ユーザーID：<%=userEmail%></span>
  </p>
  <p>
			<span>氏名：<%=userName%></span>
  </p>
    <p>
			<span>住所：<%=userAdNumber%>　<%=userAddress%></span>
  </p>
  <form action="/ShoppingSite/views/address.jsp"method="post">
		<input type="submit" value="お届け先住所を変更する">
</form>
</div>
</body>
</html>