package jp.co.aforce.beans;

import java.io.Serializable;

//JavaBeans の雛形
@SuppressWarnings("serial")
public class SignupBean implements Serializable {

 private String name;
 private String ruby;
 private String email;
 private String password;
 private String againpass;
 private String emailemsg;
 private String emsg;
 private String success;

 // セッター
 public String getName() {
     return name;
 }
 public String getRuby() {
     return ruby;
 }
 public String getEmail() {
     return email;
 }
 public String getPassword() {
     return password;
 }
 public String getAgainpass() {
     return againpass;
 }
 public String getEmailemsg() {
	 return emailemsg;
 }
 public String getEmsg() {
	 return emsg;
 }
 public String getSuccess() {
	 return success;
 }

 // ゲッター
 public void setName(String name) {
     this.name = name;
 }
 public void setRuby(String ruby) {
     this.ruby = ruby;
 }
 public void setEmail(String email) {
     this.email = email;
 }
 public void setPassword(String password) {
     this.password = password;
 }
 public void setAgainpass(String againpass) {
     this.againpass = againpass;
 }
 public void setEmailemsg(String emailemsg) {
	this.emailemsg = emailemsg;
 }
 public void setEmsg(String emsg) {
	 this.emsg = emsg;
 }
 public void setSuccess(String success) {
	 this.success = success;
 }
}