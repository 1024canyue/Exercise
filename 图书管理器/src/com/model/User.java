package com.model;

public class User {
	//˽�л�����
	private Integer userID;
	private String userName;
	private String password;
	private String sex;
	private String phone;
	
	
	public void setuserID(Integer id){     //�û�ID
		this.userID=id;
	}
	public Integer getuserID(){
		return this.userID;
	}
	public void setuserName(String name){  //�û���
		this.userName=name;
	}
	public String getuserName(){
		return this.userName;
	}
	public void setPassword(String passwd){  //����
		this.password=passwd;
	}
	public String getPassword(){
		return this.password;
	}
	public void setSex(String s){          //�û��Ա�
		this.sex=s;
	}
	public String getSex(){
		return this.sex;
	}
	public void setPhone(String ph){      //�û��绰
		this.phone=ph;
	}
	public String getPhone(){
		return this.phone;
	}
	

}
