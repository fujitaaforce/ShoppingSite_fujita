package jp.co.aforce.beans;

import java.io.Serializable;

//JavaBeans の雛形
@SuppressWarnings("serial")
public class LoginBean implements Serializable {

 private String user_id;
 private String password;
 private String emsg;

 // セッター
 public String getUser_id() {
     return user_id;
 }
 public String getPassword() {
     return password;
 }
 public String getEmsg() {
     return emsg;
 }

 // ゲッター
 public void setUser_id(String user_id) {
     this.user_id = user_id;
 }
 public void setPassword(String password) {
     this.password = password;
 }
 public void setEmsg(String emsg) {
     this.emsg = emsg;
 }
}