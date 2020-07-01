package jp.co.aforce.models;

import java.sql.ResultSet;

import jp.co.aforce.util.DBUtil;

public class SignupModel {

	/**
	 * 入力されたデータがDBに上に存在するかどうかを調べる。
	 *
	 * @param user_id ユーザID
	 * @param password パスワード
	 * @return ログイン成功=true, 失敗=false
	 */
	public boolean emailCheck(String email) {
		ResultSet rs = null;

		try {
			DBUtil.makeConnection();
			DBUtil.makeStatement();

			String sql = "SELECT `email` FROM `users` WHERE `email`='"+email+"'";
			rs = DBUtil.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return rs != null;
	}

	public boolean setUserDate(String name,String ruby, String email, String password) {
		// 実行結果を格納する変数
		ResultSet rs = null;

		try {
			// DBに接続するための手続
			DBUtil.makeConnection();
			DBUtil.makeStatement();

				// SQLを実行
				String sql = "INSERT INTO `users`(`email`, `name`, `ruby`, `password`)"
						+ "VALUES ('" +email +"','" + name +"','" +ruby+"','" + password +"')";
				DBUtil.execute(sql);
				return true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return rs != null;
	}

public boolean setAddress(String address1, String address2, String address3, String address4, String address5,String email) {
	// 実行結果を格納する変数
	ResultSet rs = null;

	try {
		// DBに接続するための手続
		DBUtil.makeConnection();
		DBUtil.makeStatement();

			// SQLを実行
			String sql = "UPDATE `users`SET`ad_number`='"+address1+"',`address`='"+address2+address3+address4+address5+"' WHERE `email`='"+email+"'";
			DBUtil.execute(sql);
			return true;

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		DBUtil.closeConnection();
	}
	return rs != null;
}
}