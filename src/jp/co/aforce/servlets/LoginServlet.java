package jp.co.aforce.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.LoginBean;
import jp.co.aforce.models.LoginModel;

// 親クラスに HttpServlet を指定する
@SuppressWarnings("serial") // これがないと waring がでる
public class LoginServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
    	HttpSession session = request.getSession( false );
    	if ( null != session ) {
    		// セッションを終了.
    		session.invalidate();
    		}
        // GETリクエストはあり得ないので、無条件でログイン画面に飛ばす
        RequestDispatcher rDispatcher = request.getRequestDispatcher("/views/login.jsp");
        rDispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        // 文字のエンコードを UTF-8 とする。これがないと文字化け。
        request.setCharacterEncoding("UTF-8");

        // ユーザによって入力された情報を取り出す
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 取り出した情報を loginBean に格納する
        LoginBean loginBean = new LoginBean();
        loginBean.setUser_id(email);
        loginBean.setPassword(password);

        // モデルをインスタンス化する
        LoginModel loginModel = new LoginModel();
        String forward_jsp = "/views/login.jsp";

        // 入力された情報がDBに存在するか調べる
        if (loginModel.loginCheck(email, password)) {

        	HttpSession session = request.getSession( true );
        	Map<String, String> map = new HashMap<String, String>();
        	map.put( "Email", email );
        	map.put("Name",loginModel.loginName(email, password));
        	map.put("Ruby",loginModel.loginRuby(email, password));
        	map.put( "Password", password );
        	map.put("adnumber",loginModel.loginAdnumber(email, password));
            map.put("address",loginModel.loginAddress(email, password));

        	if(loginModel.adminCheck(email, password)){

        		map.put("admin", "admin");

            	// 管理者情報をセッションに保存.
            	session.setAttribute( "login_user", map );

                // 管理ログインに成功した先の JSP を指定
        		forward_jsp = "/views/admin.jsp";

        	}else {

        	// ログイン情報をセッションに保存.
        	session.setAttribute( "login_user", map );
        	request.setAttribute("login", "../");

            // ログインに成功した先の JSP を指定
            forward_jsp = "/views/top.jsp";
        	}
        // ログインが失敗したときの処理
        } else {
            // エラーメッセージを設定
            loginBean.setEmsg("ユーザ名またはパスワードが違います");
            request.setAttribute("loginBean", loginBean);
        }
        // forwaed_jsp に設定されているJSPへディスパッチ
        RequestDispatcher rDispatcher = request.getRequestDispatcher(forward_jsp);
        rDispatcher.forward(request, response);

    }
}
