package jp.co.aforce.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.models.ProductModel;

public class DeleteProductServlet extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ProductModel productModel = new ProductModel();
		String msg ="";
		String id = request.getParameter("item_id");
		if(productModel.deleteProduct(id) ){
			msg="削除完了しました。";

		}else {
			msg="削除できませんでした。";
		}
		request.setAttribute("msg", msg);
		RequestDispatcher rDispatcher = request.getRequestDispatcher("/views/success.jsp");
		rDispatcher.forward(request, response);
	}
}
