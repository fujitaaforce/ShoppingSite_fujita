package jp.co.aforce.models;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import jp.co.aforce.beans.ItemListBean;
import jp.co.aforce.beans.ProductBean;
import jp.co.aforce.util.DBUtil;

public class ItemModel {

	public List<ItemListBean> itemList(String title, String category, String search, String sort){
		ResultSet rs = null;
		List<ItemListBean> ItemList = new ArrayList<ItemListBean>();

		try {

			DBUtil.makeConnection();
			DBUtil.makeStatement();
			String titlesql = "";
			String categorysql = "";
			String searchsql = "";
			String sortsql = "ORDER BY `index_no` DESC";
			if((title).length()!=0) {
				titlesql = "WHERE `title`='"+title+"'";
			}
			if (((category).length()!=0)&((title).length()==0))  {
				categorysql = "WHERE `category`='"+category+"'";
			}else if (((category).length()!=0)&((title).length()!=0)) {
				categorysql = "AND `category`='"+category+"'";
			}
			if (((search.strip()).length()!=0)&((category).length()==0)&((title).length()==0)){
				searchsql = "WHERE `item_name`='"+search+"'";
			}else if ((((category).length()!=0)||((title).length()!=0))&((search.strip()).length()!=0)){
				searchsql = "AND `item_name` LIKE '%"+search+"%'";
			}
			if(sort!=null) {
				if(Integer.parseInt(sort)==0){
					sortsql = "ORDER BY `index_no` DESC";
				}else if (Integer.parseInt(sort)==1) {
					sortsql = "ORDER BY `index_no` ASC";
				}else if (Integer.parseInt(sort)==2) {
					sortsql = "ORDER BY `price` DESC";
				}else if (Integer.parseInt(sort)==3) {
					sortsql = "ORDER BY `price` ASC";
				}
			}
			String sql = "SELECT*FROM`item` "+titlesql+categorysql+searchsql+sortsql;
			rs = DBUtil.execute(sql);

			ItemListBean itemListBean = new ItemListBean();
			itemListBean.setId(rs.getString("id"));
			itemListBean.setIndexno(rs.getString("index_no"));
			itemListBean.setItemname(rs.getString("item_name"));
			itemListBean.setItemprice(rs.getString("price"));
			itemListBean.setStockno(rs.getString("stock_no"));
			ItemList.add(itemListBean);
			while (rs.next()) {
				ItemListBean itemListBean1 = new ItemListBean();
				itemListBean1.setId(rs.getString("id"));
				itemListBean1.setIndexno(rs.getString("index_no"));
				itemListBean1.setItemname(rs.getString("item_name"));
				itemListBean1.setItemprice(rs.getString("price"));
				itemListBean1.setStockno(rs.getString("stock_no"));
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

	public List<ProductBean> itemPage(String itemid){
		ResultSet rs = null;
		List<ProductBean> ItemList = new ArrayList<ProductBean>();

		try {

			DBUtil.makeConnection();
			DBUtil.makeStatement();

			String sql = "SELECT*FROM`item` WHERE `id`='"+itemid+"'";
			rs = DBUtil.execute(sql);

			ProductBean itemListBean = new ProductBean();
			itemListBean.setId(rs.getString("id"));
			itemListBean.setIndexno(rs.getString("index_no"));
			itemListBean.setItemname(rs.getString("item_name"));
			itemListBean.setPrice(rs.getString("price"));
			itemListBean.setExplanation(rs.getString("explanation"));
			itemListBean.setStockno(rs.getString("stock_no"));
			ItemList.add(itemListBean);

			return ItemList;



		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return ItemList;
	}

	public BufferedImage itemImage(String id){
		ResultSet rs = null;

		try {

			DBUtil.makeConnection();
			DBUtil.makeStatement();


			String sql = "SELECT`item_image`FROM`item` WHERE `id`='"+id+"'";
			rs = DBUtil.execute(sql);
			InputStream imageStream = rs.getBinaryStream("item_image");
			BufferedInputStream img = new BufferedInputStream(imageStream);

			return ImageIO.read(img);

		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return null;
	}

}
