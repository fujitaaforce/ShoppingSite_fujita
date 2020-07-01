package jp.co.aforce.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.SignupBean;
import jp.co.aforce.models.SignupModel;

public class SignupServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		RequestDispatcher rDispatcher = request.getRequestDispatcher("/views/signup.jsp");
		rDispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// 文字のエンコードを UTF-8 とする。これがないと文字化け。
		request.setCharacterEncoding("UTF-8");

		// ユーザによって入力された情報を取り出す
		String name = (request.getParameter("name").strip());
		String ruby = (request.getParameter("ruby").strip());
		String email= (request.getParameter("email").strip());
		String password = (request.getParameter("password").strip());
		String againpass = (request.getParameter("againpass").strip());

		//SignupBeanへ格納
		SignupBean signupBean = new SignupBean();
		signupBean.setName(name);
		signupBean.setRuby(ruby);
		signupBean.setEmail(email);
		signupBean.setPassword(password);
		signupBean.setAgainpass(againpass);

		//SignupModelのインスタンス化
		SignupModel signupModel = new SignupModel();

		if (signupModel.emailCheck(email)) {
			// e-mailの重複
			signupBean.setEmailemsg("このe-mailアドレスはすでに使われています。");
			request.setAttribute("signupBean", signupBean);

		}else {

			if ((name.length()<=0)||(ruby.length()<=0)||(email.length()<=0)||(password.length()<=0)||(againpass.length()<=0)) {

				// 書き漏れ、間違いのあるときの処理
				signupBean.setEmsg("入力されていない項目があります。");
				request.setAttribute("signupBean", signupBean);

				// 書き漏れのないときの処理
			} else {
				//会員情報の登録
				if (signupModel.setUserDate(name,ruby,email,password)) {
					signupBean.setSuccess("登録に成功しました。");
					request.setAttribute("signupBean", signupBean);
				}else {
					signupBean.setEmsg("登録に失敗しました。");
					request.setAttribute("signupBean", signupBean);
				}
			}
		}
		// forwaed_jsp に設定されているJSPへディスパッチ
		RequestDispatcher rDispatcher = request.getRequestDispatcher("/views/signup.jsp");
		rDispatcher.forward(request, response);
	}
}
