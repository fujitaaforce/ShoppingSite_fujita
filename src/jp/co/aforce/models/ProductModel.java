package jp.co.aforce.models;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;

import jp.co.aforce.beans.ProductBean;
import jp.co.aforce.util.DBUtil;

public class ProductModel {

	private static Connection conn = null;	// コネクション
	private static Statement stmt = null;	// ステートメント

	private static String sqlDriver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/";
	private static String database = "database?useUnicode=true&characterEncoding=utf8";
	private static String user = "root";
	private static String password = "";

	public boolean newProduct(String itemname, String title, String category,String itemprice, String stockno, String explanation, InputStream itemimage) {
		ResultSet rs = null;
		//現在時刻の取得
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddhhmmss");
		String strDate = dateFormat.format(date);
		//商品idの生成
		String id ="P"+strDate;

		// DBに接続するための手続
		try {
			Class.forName(sqlDriver);
			conn = DriverManager.getConnection(url+database+"&user="+user+"&password="+password);

			String sql = "INSERT INTO `item`(`id`, `item_name`, `title`, `category`, `price`, `stock_no`, `explanation`, `item_image`) VALUES ('"
					+id+"','"+itemname+"','"+title+"','"+category+"','"+itemprice+"','"+stockno+"','"+explanation+"',?)";
			 PreparedStatement stmt = conn.prepareStatement( sql );
			 stmt.setBytes(1,IOUtils.toByteArray(itemimage));

			 stmt.executeUpdate();

			 stmt.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs != null;
	}

	public List<ProductBean> selectProduct(String id) {
		ResultSet rs = null;

		try {
			DBUtil.makeConnection();
			DBUtil.makeStatement();
			String sql = "SELECT * FROM `item` WHERE `id`='"+id+"'";
	        rs = DBUtil.execute(sql);

			List<ProductBean> productList = new ArrayList<ProductBean>();
			ProductBean productBean = new ProductBean();
			productBean.setId(rs.getString("id"));
			productBean.setIndexno(rs.getString("index_no"));
			productBean.setItemname(rs.getString("itemname"));
			productBean.setPrice(rs.getString("price"));
			productBean.setStockno(rs.getString("stock_no"));
			productBean.setExplanation(rs.getString("explanation"));
			Blob is = rs.getBlob("itemimage");
			productBean.setItemimage( is.getBinaryStream());
			productList.add(productBean);

			return productList;

		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return null;
	}

	public boolean updateProduct(String id, String indexno, String itemname, String price, String stockno,String explanation, InputStream itemimage) {
		ResultSet rs = null;

		try {
			DBUtil.makeConnection();
			DBUtil.makeStatement();
			String sql = "UPDATE `item` SET `index_no`='"+indexno+"',`item_name`='"+itemname+"',`price`='"+price+"',`stock_no`='"+stockno+"',`explanation`='"+explanation+"',`item_image`='"+itemimage+"' WHERE `id`='"+id+"'";
	        rs = DBUtil.execute(sql);
	        return true;

		}catch (Exception e) {
			e.printStackTrace();
		}
		return rs != null;
	}

	public boolean deleteProduct(String id) {
		ResultSet rs = null;

		try {
			DBUtil.makeConnection();
			DBUtil.makeStatement();
			String sql = "DELETE FROM `item` WHERE `id`='"+id+"'";
	        rs = DBUtil.execute(sql);
	        return true;

		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return rs != null;
	}

	public List<ProductBean> listProduct(String search) {
		ResultSet rs = null;

		try {
			DBUtil.makeConnection();
			DBUtil.makeStatement();
			String sql = "SELECT * FROM `item`";
	        rs = DBUtil.execute(sql);

			List<ProductBean> productList = new ArrayList<ProductBean>();
			ProductBean productBean = new ProductBean();
			productBean.setId(rs.getString("id"));
			productBean.setIndexno(rs.getString("index_no"));
			productBean.setItemname(rs.getString("itemname"));
			productBean.setPrice(rs.getString("price"));
			productBean.setStockno(rs.getString("stock_no"));
			productBean.setExplanation(rs.getString("explanation"));
			Blob is = rs.getBlob("itemimage");
			productBean.setItemimage( is.getBinaryStream());
			productList.add(productBean);
			while (rs.next()) {
				productBean.setId(rs.getString("id"));
				productBean.setIndexno(rs.getString("index_no"));
				productBean.setItemname(rs.getString("itemname"));
				productBean.setPrice(rs.getString("price"));
				productBean.setStockno(rs.getString("stock_no"));
				productBean.setExplanation(rs.getString("explanation"));
				is = rs.getBlob("itemimage");
				productBean.setItemimage( is.getBinaryStream());
				productList.add(productBean);
			}

			return productList;

		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return null;
	}
}
