package com.model;

public class BookType {
	private Integer typeid;
	private String typeName;
	private String remark;
	
	public void settypeId(Integer id){     //��ID
		this.typeid=id;
	}
	public Integer gettypeId(){
		return this.typeid;
	}
	public void settypeName(String name){     //��ID
		this.typeName=name;
	}
	public String gettypeName(){
		return this.typeName;
	}
	public void setremark(String rmk){     //��ID
		this.remark=rmk;
	}
	public String getremark(){
		return this.remark;
	}
}
