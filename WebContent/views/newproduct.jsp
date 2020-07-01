<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/newproduct.js"></script>
<meta charset="UTF-8">
<title>新規商品登録</title>
<link href = "../css/sitemodel.css" rel = "stylesheet"></link>
</head>
<body class=background>
<h1>新規商品登録</h1>
<form action="/ShoppingSite/views/Product" id="form" method="post" enctype="multipart/form-data">
		<p>
			<span>商品名</span><input type="text" id="item_name" name="item_name" size="30">
		</p>
				タイトル
				<select name="title" class=select>
				<option value="DM">デュエマ</option>
				<option value="PCG">ポケカ</option>
				<option value="MTG">MTG</option></select>
		<p>
				商品カテゴリ
				<select name="category" class=select>
				<option value="single">シングルカード</option>
				<option value="supply">カードサプライ</option>
				<option value="puck">拡張パック</option>
				<option value="deck">構築済みデッキ</option>
				</select>
		</p>
		<p>
			<span>価格</span> <input type="text" id="item_price" name="item_price" size="20">※半角数字
		</p>
		<p>
			<span>在庫数</span><input type="text" id="stock_no" name="stock_no" size="20">※半角数字
		</p>
		<p>
			<span>商品説明文</span><textarea id="explanation" name="explanation"></textarea>
	</p>
		<p>
			<span>商品イメージ</span><input type="file" id="item_image" name="item_image">※JPEG画像
		</p>
		<input type="submit" value="登録">
	</form>

</body>
</html>