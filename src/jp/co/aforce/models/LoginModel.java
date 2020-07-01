package jp.co.aforce.models;

import java.sql.ResultSet;

import jp.co.aforce.util.DBUtil;

public class LoginModel {

    /**
     * 入力されたデータがDBに上に存在するかどうかを調べる。
     *
     * @param user_id ユーザID
     * @param password パスワード
     * @return ログイン成功=true, 失敗=false
     */
    public boolean loginCheck(String email, String password) {
        // 実行結果を格納する変数
        ResultSet rs = null;

        try {
            // DBに接続するための手続
            DBUtil.makeConnection();
            DBUtil.makeStatement();

            // SQLを実行
            String SQL = "SELECT * FROM `users` WHERE `email`='"+email+"' AND `password`='"+password+"'";
            rs = DBUtil.execute(SQL);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection();
        }
        return rs != null;
    }

    public boolean adminCheck(String email, String password) {
        // 実行結果を格納する変数
        ResultSet rs = null;

        try {
            // DBに接続するための手続
            DBUtil.makeConnection();
            DBUtil.makeStatement();

            // SQLを実行
            String SQL = "SELECT `admin_flg` FROM `users` WHERE `email`='"+email+"' AND `password`='"+password+"'";
            rs = DBUtil.execute(SQL);

            int f = rs.getInt("admin_flg");

            return f==0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection();
        }
        return rs != null;
    }

    public String loginName(String email, String password) {
        // 実行結果を格納する変数
        ResultSet rs = null;

        try {
            // DBに接続するための手続
            DBUtil.makeConnection();
            DBUtil.makeStatement();

            // SQLを実行
            String SQL = "SELECT * FROM `users` WHERE `email`='"+email+"' AND `password`='"+password+"'";
            rs = DBUtil.execute(SQL);

            return rs.getString("name");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection();
        }
		return null;
    }

    public String loginRuby(String email, String password) {
        // 実行結果を格納する変数
        ResultSet rs = null;

        try {
            // DBに接続するための手続
            DBUtil.makeConnection();
            DBUtil.makeStatement();

            // SQLを実行
            String SQL = "SELECT * FROM `users` WHERE `email`='"+email+"' AND `password`='"+password+"'";
            rs = DBUtil.execute(SQL);

            return rs.getString("ruby");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection();
        }
		return null;
    }

public String loginAdnumber(String email, String password) {
    // 実行結果を格納する変数
    ResultSet rs = null;

    try {
        // DBに接続するための手続
        DBUtil.makeConnection();
        DBUtil.makeStatement();

        // SQLを実行
        String SQL = "SELECT * FROM `users` WHERE `email`='"+email+"' AND `password`='"+password+"'";
        rs = DBUtil.execute(SQL);

        return rs.getString("ad_number");

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        DBUtil.closeConnection();
    }
	return null;
}

public String loginAddress(String email, String password) {
    // 実行結果を格納する変数
    ResultSet rs = null;

    try {
        // DBに接続するための手続
        DBUtil.makeConnection();
        DBUtil.makeStatement();

        // SQLを実行
        String SQL = "SELECT * FROM `users` WHERE `email`='"+email+"' AND `password`='"+password+"'";
        rs = DBUtil.execute(SQL);

        return rs.getString("address");

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        DBUtil.closeConnection();
    }
	return null;
}
}