package jp.co.aforce.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.ItemListBean;
import jp.co.aforce.models.ItemModel;

public class ItemListServlet extends HttpServlet{

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

        String title = request.getParameter("title");
        String category = request.getParameter("category");
        String search = request.getParameter("search");
        List<String> searchList = new ArrayList<String>();
        searchList.add(title);
        searchList.add(category);
        searchList.add(search);
        String sort = request.getParameter("sort");
        // 文字のエンコードを UTF-8 とする。
        request.setCharacterEncoding("UTF-8");

        List<ItemListBean> itemList = new ArrayList<ItemListBean>();
        itemList = itemModel.itemList(title, category, search,sort);

		String selected = "selected";
        if(sort!=null) {
			if(Integer.parseInt(sort)==0){
				request.setAttribute("sort0", selected);
			}else if (Integer.parseInt(sort)==1) {
				request.setAttribute("sort1", selected);
			}else if (Integer.parseInt(sort)==2) {
				request.setAttribute("sort2", selected);
			}else if (Integer.parseInt(sort)==3) {
				request.setAttribute("sort3", selected);
			}
		}

        request.setAttribute("itemList", itemList);
        request.setAttribute("searchList", searchList);

		RequestDispatcher rDispatcher = request.getRequestDispatcher("/views/itemlist.jsp");
		rDispatcher.forward(request, response);
	}

}
