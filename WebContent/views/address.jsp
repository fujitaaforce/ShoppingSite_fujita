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
<meta charset="UTF-8">
<title>お届け先住所変更</title>
<link href = "../css/sitemodel.css" rel = "stylesheet"></link>
</head>
<body class=background>
	<a href="/ShoppingSite"><button class=top>トップ</button></a>
	<div class=mypagetext>
	<form action="/ShoppingSite/views/Address" method="post">
	  <p>
        郵便番号<input type="text"name="address1"size="10">
  </p>
			<select name="address2" class=address1>
				<option value="" selected>都道府県</option>
<option value="北海道">北海道</option>
<option value="青森県">青森県</option>
<option value="岩手県">岩手県</option>
<option value="宮城県">宮城県</option>
<option value="秋田県">秋田県</option>
<option value="山形県">山形県</option>
<option value="福島県">福島県</option>
<option value="茨城県">茨城県</option>
<option value="栃木県">栃木県</option>
<option value="群馬県">群馬県</option>
<option value="埼玉県">埼玉県</option>
<option value="千葉県">千葉県</option>
<option value="東京都" selected>東京都</option>
<option value="神奈川県">神奈川県</option>
<option value="新潟県">新潟県</option>
<option value="富山県">富山県</option>
<option value="石川県">石川県</option>
<option value="福井県">福井県</option>
<option value="山梨県">山梨県</option>
<option value="長野県">長野県</option>
<option value="岐阜県">岐阜県</option>
<option value="静岡県">静岡県</option>
<option value="愛知県">愛知県</option>
<option value="三重県">三重県</option>
<option value="滋賀県">滋賀県</option>
<option value="京都府">京都府</option>
<option value="大阪府">大阪府</option>
<option value="兵庫県">兵庫県</option>
<option value="奈良県">奈良県</option>
<option value="和歌山県">和歌山県</option>
<option value="鳥取県">鳥取県</option>
<option value="島根県">島根県</option>
<option value="岡山県">岡山県</option>
<option value="広島県">広島県</option>
<option value="山口県">山口県</option>
<option value="徳島県">徳島県</option>
<option value="香川県">香川県</option>
<option value="愛媛県">愛媛県</option>
<option value="高知県">高知県</option>
<option value="福岡県">福岡県</option>
<option value="佐賀県">佐賀県</option>
<option value="長崎県">長崎県</option>
<option value="熊本県">熊本県</option>
<option value="大分県">大分県</option>
<option value="宮崎県">宮崎県</option>
<option value="鹿児島県">鹿児島県</option>
<option value="沖縄県">沖縄県</option>
				</select>
			市町村<input type="text"name="address3"size="10">
  <p>
         番地<input type="text"name="address4"size="20">
  </p>
    <p>
        建物名<input type="text"name="address5"size="20">
  </p>
  			<input type="submit" name="submit" value="変更" class=addresssubmit>
  </form>
  </div>
</body>
</html>