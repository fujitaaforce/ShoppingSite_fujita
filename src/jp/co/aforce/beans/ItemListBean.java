package jp.co.aforce.beans;

import java.io.OutputStream;

public class ItemListBean {

	String id = null;
	String indexno = null;
	String itemname = null;
	String itemprice = null;
	String stockno = null;
	OutputStream itemimage = null;

	public String getId() {
		return id;
	}
	public String getIndexno() {
		return indexno;
	}
	public String getItemname() {
		return itemname;
	}
	public String getItemprice() {
		return itemprice;
	}
	public String getStockno() {
		return stockno;
	}
	public OutputStream getItemimage() {
		return itemimage;
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
	public void setItemprice(String itemprice) {
		this.itemprice = itemprice;
	}
	public void setStockno(String stockno) {
		this.stockno = stockno;
	}
	public void setItemimage(OutputStream itemimage) {
		this.itemimage = itemimage;
	}


}
