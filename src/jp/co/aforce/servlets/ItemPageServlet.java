package jp.co.aforce.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.ProductBean;
import jp.co.aforce.models.ItemModel;

public class ItemPageServlet extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        RequestDispatcher rDispatcher = request.getRequestDispatcher("");
        rDispatcher.forward(request, response);
    }

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
        ItemModel itemModel =new ItemModel();

        String  itemid = request.getParameter("id");

        // 文字のエンコードを UTF-8 とする。
        request.setCharacterEncoding("UTF-8");

        List<ProductBean> itemList = new ArrayList<ProductBean>();
        itemList = itemModel.itemPage(itemid);

        request.setAttribute("item", itemList);

		RequestDispatcher rDispatcher = request.getRequestDispatcher("/views/item.jsp");
		rDispatcher.forward(request, response);
	}

}