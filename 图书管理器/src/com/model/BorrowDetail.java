package com.model;

public class BorrowDetail {
	private Integer borrowId;
	private Integer userId;
	private Integer bookId;
	private Integer status;
	private long borrowTime;
	private long returnTime;
	
	public void setborrowId(Integer bid){     //���ļ�¼
		this.borrowId=bid;
	}
	public Integer getborrowId(){
		return this.borrowId;
	}
	public void setuserId(Integer uid){     //�û�ID
		this.userId=uid;
	}
	public Integer getuserId(){
		return this.userId;
	}
	public void setbookId(Integer bid){     //��ID
		this.bookId=bid;
	}
	public Integer getbookId(){
		return this.bookId;
	}
	public void setstatus(Integer sta){     //����״̬
		this.status=sta;
	}
	public Integer getstatus(){
		return this.status;
	}
	public void setborrowTime(long bt){     //����ʱ��
		this.borrowTime=bt;
	}
	public long getborrowTime(){
		return this.borrowTime;
	}
	public void setreturnTime(long rt){     //�黹
		this.returnTime=rt;
	}
	public long getreturnTime(){
		return this.returnTime;
	}
}

