package jp.co.aforce.beans;

import java.io.InputStream;
import java.io.Serializable;

public class ProductBean implements Serializable{

	String id = null;
	String indexno = null;
	String itemname = null;
	String title =null;
	String category =null;
	String price = null;
	String stockno = null;
	String explanation = null;
	InputStream itemimage = null;
	String emsg = null;

	public String getId () {
		return id;
	}
	public String getIndexno() {
		return indexno;
	}
	public String getItemname () {
		return itemname;
	}
	public String getTitle () {
		return title;
	}
	public String category () {
		return category;
	}
	public String getPrice () {
		return price;
	}
	public String getStockno () {
		return stockno;
	}
	public String getExplanation () {
		return explanation;
	}
	public InputStream getItemimage () {
		return itemimage;
	}
	public String getEmsg() {
		return emsg;
	}

	public void setId(String id) {
		this.id = id;
	}
	public void setIndexno(String indexno) {
		this.indexno = indexno;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public void setStockno(String stockno) {
		this.stockno = stockno;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public void setItemimage(InputStream itemimage) {
		this.itemimage = itemimage;
	}
	public void setEmsg(String emsg) {
		this.emsg = emsg;
	}

}
