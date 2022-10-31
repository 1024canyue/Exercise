package com.model;

public class User {
	//私有化属性
	private Integer userID;
	private String userName;
	private String password;
	private String sex;
	private String phone;
	
	
	public void setuserID(Integer id){     //用户ID
		this.userID=id;
	}
	public Integer getuserID(){
		return this.userID;
	}
	public void setuserName(String name){  //用户名
		this.userName=name;
	}
	public String getuserName(){
		return this.userName;
	}
	public void setPassword(String passwd){  //密码
		this.password=passwd;
	}
	public String getPassword(){
		return this.password;
	}
	public void setSex(String s){          //用户性别
		this.sex=s;
	}
	public String getSex(){
		return this.sex;
	}
	public void setPhone(String ph){      //用户电话
		this.phone=ph;
	}
	public String getPhone(){
		return this.phone;
	}
	

}
