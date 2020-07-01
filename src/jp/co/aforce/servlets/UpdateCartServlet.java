package jp.co.aforce.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.models.CartModel;

public class UpdateCartServlet extends HttpServlet {

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
		String id = request.getParameter("item_id");
		String itemname = request.getParameter("item_name");
		String buyno = request.getParameter("buy_no");
		if(cartModel.updateCart(email, id, itemname, buyno)) {
			RequestDispatcher rDispatcher = request.getRequestDispatcher("/views/Cart");
			rDispatcher.forward(request, response);
		}else {
			RequestDispatcher rDispatcher = request.getRequestDispatcher("/views/Cart");
			rDispatcher.forward(request, response);
		}
	}

}