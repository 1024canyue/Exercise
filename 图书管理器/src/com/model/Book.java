package com.model;

public class Book {
	private Integer bookid;
	private String bookName;
	private String author;
	private Integer status;
	private Integer bookTypeId;
	private String publish;
	private Integer number;
	private Double price;
	private String remark;
	
	public void setbookId(Integer id){     //书ID
		this.bookid=id;
	}
	public Integer getbookId(){
		return this.bookid;
	}
	public void setbookname(String name){     //书名
		this.bookName=name;
	}
	public String getbookName(){
		return this.bookName;
	}
	public void setauthor(String au){     //作者
		this.author=au;
	}
	public String getauthor(){
		return this.author;
	}
	public void setstatus(Integer st){     //状态
		this.status=st;
	}
	public Integer getstatus(){
		return this.status;
	}
	public void setbookTypeId(Integer tid){     //类型ID
		this.bookTypeId=tid;
	}
	public Integer getbookTypeId(){
		return this.bookTypeId;
	}
	public void setpublish(String pub){     //出版社
		this.publish=pub;
	}
	public String getpublish(){
		return this.publish;
	}
	public void setnumber(Integer num){     //数量
		this.number=num;
	}
	public Integer getnumber(){
		return this.number;
	}
	public void setprice(Double price){     //价格
		this.price=price;
	}
	public Double getprice(){
		return this.price;
	}
	public void setremark(String rmk){     //评论
		this.remark=rmk;
	}
	public String getremark(){
		return this.remark;
	}
	
}
