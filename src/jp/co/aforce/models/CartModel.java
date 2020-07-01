package jp.co.aforce.models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.ItemListBean;
import jp.co.aforce.util.DBUtil;

public class CartModel {

	public List<ItemListBean> itemList(String email){
		ResultSet rs = null;
		List<ItemListBean> ItemList = new ArrayList<ItemListBean>();

		try {

			DBUtil.makeConnection();
			DBUtil.makeStatement();


			String sql = "SELECT*FROM`cart` WHERE `email`='"+email+"' AND `delete_flg`='0'";
			rs = DBUtil.execute(sql);

			ItemListBean itemListBean = new ItemListBean();
			itemListBean.setId(rs.getString("id"));
			itemListBean.setItemname(rs.getString("item_name"));
			Integer subtotal = (rs.getInt("price"))*(rs.getInt("buy_no"));
			itemListBean.setItemprice((subtotal).toString());
			itemListBean.setStockno(rs.getString("buy_no"));
			ItemList.add(itemListBean);
			while (rs.next()) {
				ItemListBean itemListBean1 = new ItemListBean();
				itemListBean1.setId(rs.getString("id"));
				itemListBean1.setItemname(rs.getString("item_name"));
				Integer subtotal1 = (rs.getInt("price"))*(rs.getInt("buy_no"));
				itemListBean1.setItemprice((subtotal1).toString());
				itemListBean1.setStockno(rs.getString("buy_no"));
				ItemList.add(itemListBean1);
			}

			return ItemList;

		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return ItemList;
	}

	public int totalPrice(String email){
		ResultSet rs = null;

		try {

			DBUtil.makeConnection();
			DBUtil.makeStatement();


			String sql = "SELECT `price`, `buy_no` FROM`cart` WHERE `email`='"+email+"' AND `delete_flg`='0'";
			rs = DBUtil.execute(sql);
			int price = rs.getInt("price");
			int buyno = rs.getInt("buy_no");
			int total = price*buyno;
			while (rs.next()) {
				price = rs.getInt("price");
				buyno = rs.getInt("buy_no");
				int subtotal = price*buyno;
				total = subtotal +total;

			}

			return total;

		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return 0;
	}

	public boolean insertCart(String email, String id, String itemname, String price, String buyno) {

		ResultSet rs = null;

		if(email == null) {
			return false;
		}else {
			try {
				DBUtil.makeConnection();
				DBUtil.makeStatement();

				String sql = "SELECT * FROM `cart` WHERE `email`='"+email+"' AND `id`='"+id+"'";
				rs = DBUtil.execute(sql);

				if (rs == null) { //新規でカートに登録する場合

					sql =  "INSERT INTO `cart`(`email`, `id`,`item_name`,`price`,`buy_no`,`delete_flg` ) VALUES ('"
							+email+"','"+id+"','"+itemname+"','"+price+"','"+buyno+"','"+0+"')";
					DBUtil.execute(sql);

					return true;

				}else if (1==rs.getInt("delete_flg")) {

					sql =  "INSERT INTO `cart`(`email`, `id`,`item_name`,`price`,`buy_no`,`delete_flg` ) VALUES ('"
							+email+"','"+id+"','"+itemname+"','"+price+"','"+buyno+"','"+0+"')";
					DBUtil.execute(sql);

					return true;

				}else {
					//もともとカートに入っていた場合

					//もともとカートに入れている個数を増やす
					int nowbuyno = Integer.parseInt(buyno)+rs.getInt("buy_no");

					sql =  "UPDATE `cart` SET `buy_no`='"+nowbuyno+"'='0' WHERE `email`='"+email+"' AND `id`='"+id+"'";
					DBUtil.execute(sql);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeConnection();
			}
			return false;
		}
	}

	public boolean updateCart(String email, String id, String itemname,String buyno) {
		ResultSet rs = null;
		String sql = "";

		try {
			DBUtil.makeConnection();
			DBUtil.makeStatement();
			if(Integer.parseInt(buyno) >= 1){
				sql =  "UPDATE `cart` SET `buy_no`='"+buyno+"' WHERE `email`='"+email+"' AND `id`='"+id+"'";
			}else{
				sql =  "DELETE FROM `cart` WHERE `email`='"+email+"' AND `id`='"+id+"'";
			}
			rs = DBUtil.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return rs != null;
	}

	public boolean deleteCart(String email, String id, String itemname,String buyno) {
		ResultSet rs = null;

		try {
			DBUtil.makeConnection();
			DBUtil.makeStatement();
			String sql = "DELETE FROM `cart` WHERE `email`='"+email+"' AND `id`='"+id+"'";
			rs = DBUtil.execute(sql);

		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return rs != null;
	}

	public boolean orderCart(String email) {
		ResultSet rs = null;

		try {
			DBUtil.makeConnection();
			DBUtil.makeStatement();
			String sql ="SELECT `cart`.`buy_no`,`item`.`stock_no`,`id` FROM`cart`NATURAL JOIN `item` WHERE `email`='"+email+"' AND`delete_flg`='0'";
			rs = DBUtil.execute(sql);
			int c =rs.getInt("stock_no")-rs.getInt("buy_no");
			List <String> idString = new ArrayList<String>();
			idString.add(rs.getString("id"));
			List <String> cString = new ArrayList<String>();
			cString.add(String.valueOf(c));
			while (rs.next()) {
				c =rs.getInt("stock_no")-rs.getInt("buy_no");
				idString.add(rs.getString("id"));
				cString.add(String.valueOf(c));
			}
			int i =0;
			sql = "UPDATE `item`SET `stock_no`='"+cString.get(i)+"' WHERE `id`='"+idString.get(i)+"'";
			DBUtil.execute(sql);
			while (cString.size()>=i) {
				i+=1;
				DBUtil.execute(sql);
			}

			sql = "UPDATE `cart`SET `delete_flg`='1' WHERE `email`='"+email+"' AND`delete_flg`='0'";
			rs = DBUtil.execute(sql);
			return true;

		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return rs != null;
	}

}
