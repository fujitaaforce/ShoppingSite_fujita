package jp.co.aforce.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.SignupBean;
import jp.co.aforce.models.SignupModel;

public class AddressServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		RequestDispatcher rDispatcher = request.getRequestDispatcher("/views/login.jsp");
		rDispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// 文字のエンコードを UTF-8 とする。これがないと文字化け。
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession( false );

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

		// ユーザによって入力された情報を取り出す
		String address1=(request.getParameter("address1"));
		String address2=(request.getParameter("address2"));
		String address3=(request.getParameter("address3"));
		String address4=(request.getParameter("address4"));
		String address5=(request.getParameter("address5"));

		//SignupModelのインスタンス化
		SignupModel signupModel = new SignupModel();
		SignupBean signupBean = new SignupBean();

			if ((address1.length()<=0)||(address2.length()<=0)||(address3.length()<=0)||(address4.length()<=0)) {

				// 書き漏れ、間違いのあるときの処理
				signupBean.setEmsg("入力されていない項目があります。");
				request.setAttribute("signupBean", signupBean);

				// 書き漏れのないときの処理
			} else {
				//会員情報の登録
				if (signupModel.setAddress(address1,address2,address3,address4,address5,email)) {
					signupBean.setSuccess("変更に成功しました。");
					request.setAttribute("signupBean", signupBean);
				}else {
					signupBean.setEmsg("変更に失敗しました。");
					request.setAttribute("signupBean", signupBean);
				}
			}
		// forwaed_jsp に設定されているJSPへディスパッチ
		RequestDispatcher rDispatcher = request.getRequestDispatcher("/views/mypage.jsp");
		rDispatcher.forward(request, response);
	}
}
