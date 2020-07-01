package jp.co.aforce.servlets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.models.ItemModel;

public class ImageServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ItemModel itemModel = new ItemModel();
		String id = request.getParameter("id");
		response.setContentType("image/jpeg");
		BufferedImage img = itemModel.itemImage(id);
		OutputStream os = response.getOutputStream();
		ImageIO.write(img, "jpg", os);
		os.flush();
	}

}
