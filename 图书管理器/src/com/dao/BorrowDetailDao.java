package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.BorrowDetail;
/*
 * 定义一个关于图书借阅操作的类BorrowDetailDao，专门用于实现借阅记录的查询、添加、修改，其中借阅记录的修改仅仅针对管理员
 */
public class BorrowDetailDao {
    /**
     * 该方法用于借书操作前，根据用户输入的书号、书名进行图书是否可借的信息查询
     * @param con
     * @param borrowDetail
     * @return
     * @throws Exception
     */
	public ResultSet list(Connection con,BorrowDetail borrowDetail)throws Exception{
		//编辑查询图书借阅数据表borrowdetail、图书数据表和用户数据表的SQL语句，查询语句中“bd”表示borrowdetail数据表；“u”表示user数据表；“b”表示book数据表
		StringBuffer sb=new StringBuffer("SELECT bd.*,u.username,b.book_name from borrowdetail bd,user u,book b where u.id=bd.user_id and b.id=bd.book_id");
		
		if(borrowDetail.getuserId() != null){
			sb.append(" and u.id = ?");
		}
		//getStatus()获得图书状态的方法  1表示在借  2表示已还
		if(borrowDetail.getstatus() != null){
			sb.append(" and bd.status = ?");
		}
		if(borrowDetail.getbookId() != null){
			sb.append(" and bd.book_id = ?");
		}
		//将查询结果根据图书的id号进行排序
		sb.append("  order by bd.id");
		//用PreparedStatement类实例化一个对象pstmt，通过该对象实现图书信息查询
		PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sb.toString());
		//对sb字符串中第一个问号？的内容进行设置
		if(borrowDetail.getuserId() != null){
			pstmt.setInt(1, borrowDetail.getuserId());
		}
		if(borrowDetail.getstatus() != null && borrowDetail.getbookId() != null){
			pstmt.setInt(2, borrowDetail.getstatus());
			pstmt.setInt(3, borrowDetail.getbookId());
		}
		//返回查询结果
		return pstmt.executeQuery();
	}
    /**
     * 该方法用于借书操作后，将借阅记录添加到借阅数据表borrowdetail中
     * @param con
     * @param borrowDetail
     * @return
     * @throws Exception
     */
	public int add(Connection con, BorrowDetail borrowDetail) throws Exception {
		String sql = "insert into borrowdetail (user_id,book_id,status,borrow_time) values (?,?,?,?)";
		PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
		pstmt.setInt(1, borrowDetail.getuserId());
		pstmt.setInt(2, borrowDetail.getbookId());
		pstmt.setInt(3, borrowDetail.getstatus());
		pstmt.setLong(4, borrowDetail.getborrowTime());
		return pstmt.executeUpdate();//返回添加结果
	}
    /**
     * 该方法用于修改借阅信息
     * @param con
     * @param detail
     * @return
     * @throws Exception
     */
	public int returnBook(Connection con,BorrowDetail detail)throws Exception {
		String sql = "update borrowdetail set status = ? ,return_time = ? where id = ?";
		PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
		pstmt.setInt(1, detail.getstatus());
		pstmt.setLong(2, detail.getreturnTime());
		pstmt.setInt(3, detail.getborrowId());
		return pstmt.executeUpdate();
	}
}
