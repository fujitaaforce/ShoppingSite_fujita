package jp.co.aforce.models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.UserBean;
import jp.co.aforce.util.DBUtil;

public class UserModel {

/**
 * 入力されたデータがDBに上に存在するかどうかを調べる。
 *
 * @param user_id ユーザID
 * @param password パスワード
 * @return ログイン成功=true, 失敗=false
 */

public List<UserBean> getAllUsers(String user_id, String password) {
    // 実行結果を格納する変数
    ResultSet rs = null;

    try {
        // DBに接続するための手続
        DBUtil.makeConnection();
        DBUtil.makeStatement();

        // SQLを実行
        String SQL = "SELECT * FROM `users` WHERE `email`='"+user_id+"' AND `password`='"+password+"'";
        rs = DBUtil.execute(SQL);

        List<UserBean> UserBeansList = new ArrayList<UserBean>();

        while(rs.next()){
        	UserBean userBean = new UserBean();
            userBean.setEmail( rs.getString("email"));
            userBean.setName(rs.getString("name"));
            userBean.setRuby(rs.getString("ruby"));
            UserBeansList.add(userBean);
          }
        return UserBeansList;


    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        DBUtil.closeConnection();
    }
	return null;
}
}