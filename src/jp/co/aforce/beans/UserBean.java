package jp.co.aforce.beans;

import java.io.Serializable;

public class UserBean implements Serializable {

		 private String email;
		 private String name;
		 private String ruby;
		 private String password;

		 // セッター
		 public String getEmail() {
		     return email;
		 }
		 public String getName() {
		     return name;
		 }
		 public String getRuby() {
		     return ruby;
		 }
		 public String getPassword() {
			return password;
		 }

		 // ゲッター
		 public void setEmail(String email) {
		     this.email = email;
		 }
		 public void setName(String name) {
		     this.name = name;
		 }
		 public void setRuby(String ruby) {
		     this.ruby = ruby;
		 }
		 public void setPassword(String password) {
		     this.password = password;
		 }
}
