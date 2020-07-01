package jp.co.aforce.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.ItemListBean;
import jp.co.aforce.models.CartModel;

public class CartServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession( false );

		CartModel cartModel = new CartModel();
		String email = null;
		try {
			String object = (session.getAttribute("login_user")).toString();
			int index1 = object.indexOf("=");
			String obj = object.substring(index1+1);
			int index2 = obj.indexOf(",");
			String o = obj.substring(0,index2);
			email = o;
		}catch (Exception e) {
		}
		List<ItemListBean> itemList = new ArrayList<ItemListBean>();
		itemList = cartModel.itemList(email);
		int total = cartModel.totalPrice(email);

		request.setAttribute("itemList", itemList);
		request.setAttribute("total", total);

		RequestDispatcher rDispatcher = request.getRequestDispatcher("/views/cart.jsp");
		rDispatcher.forward(request, response);
	}
}