package com.model;

public class BookType {
	private Integer typeid;
	private String typeName;
	private String remark;
	
	public void settypeId(Integer id){     // ÈID
		this.typeid=id;
	}
	public Integer gettypeId(){
		return this.typeid;
	}
	public void settypeName(String name){     // ÈID
		this.typeName=name;
	}
	public String gettypeName(){
		return this.typeName;
	}
	public void setremark(String rmk){     // ÈID
		this.remark=rmk;
	}
	public String getremark(){
		return this.remark;
	}
}
