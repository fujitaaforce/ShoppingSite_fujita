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
<title>メッセージ</title>
</head>
<body class=background>
	<a href="/ShoppingSite"><button class=top>トップ</button></a>
<a class=msg>
${requestScope.msg}</a>
