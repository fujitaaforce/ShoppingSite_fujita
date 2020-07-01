package jp.co.aforce.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import jp.co.aforce.beans.ProductBean;
import jp.co.aforce.models.ProductModel;

@MultipartConfig
public class ProductServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// GETリクエストはあり得ないので、無条件でトップ画面に飛ばす
		RequestDispatcher rDispatcher = request.getRequestDispatcher("/views/top.jsp");
		rDispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

        // 文字のエンコードを UTF-8 とする。これがないと文字化け。
        request.setCharacterEncoding("UTF-8");

        //ディスパッチ先の定義
        String forward_jsp = "/views/newproduct.jsp";

        // ユーザによって入力された情報を取り出す
        String itemname = request.getParameter("item_name");
        String price = request.getParameter("item_price");
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        String stockno = request.getParameter("stock_no");
        String explanation = request.getParameter("explanation");
        InputStream itemimage= null;
        Part filePart = request.getPart("item_image");
        itemimage = filePart.getInputStream();

        //ProductBeanへ格納
        ProductBean productBean = new ProductBean();
        productBean.setItemname(itemname);
        productBean.setTitle(title);
        productBean.setCategory(category);
        productBean.setPrice(price);
        productBean.setStockno(stockno);
        productBean.setExplanation(explanation);
        productBean.setItemimage(itemimage);

        ProductModel productModel = new ProductModel();
        if (productModel.newProduct(itemname, title, category,price,stockno,explanation,itemimage)) {
			productBean.setEmsg("登録に成功しました。");
			request.setAttribute("productBean", productBean);
			forward_jsp = "/views/admin.jsp";
		}else {
			productBean.setEmsg("登録に失敗しました。");
			request.setAttribute("productBean", productBean);
		}
		RequestDispatcher rDispatcher = request.getRequestDispatcher(forward_jsp);
		rDispatcher.forward(request, response);
	}
}
