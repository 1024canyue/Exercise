package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.Book;
import com.utils.ToolUtil;
/*
 * 定义一个关于图书操作的类BookDao，专门用于实现图书的增、删、改、查操作
 * 图书查询有两种方法，一种针对管理员叫list，一种针对学生叫listcan
 * 图书添加、删除、修改方法仅仅针对管理员
 */
public class BookDao {
	// 图书添加
		public int add(Connection con,Book book)throws Exception{
			String sql="insert into book (book_name,type_id,author,publish,price,number,status,remark) values(?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, book.getbookName());
			pstmt.setInt(2, book.getbookTypeId());
			pstmt.setString(3, book.getauthor());
			pstmt.setString(4, book.getpublish());
			pstmt.setDouble(5, book.getprice());
			pstmt.setInt(6, book.getnumber());
			pstmt.setInt(7, book.getstatus());
			pstmt.setString(8, book.getremark());
			return pstmt.executeUpdate();
		}
		
		// 图书信息查询（管理员）
		public ResultSet list(Connection con,Book book)throws Exception{
			//在book数据表、book_type数据表中进行图书信息的查询，其中书名或作者是由用户输入的
			StringBuffer sb=new StringBuffer("select b.*,bt.type_name from book b,book_type bt where b.type_id=bt.id");
			//根据用户输入的书名进行查询
			if(!ToolUtil.isEmpty(book.getbookName())){
				sb.append(" and b.book_name like '%"+book.getbookName()+"%'");
			}
			//根据用户输入的作者进行查询
			if(!ToolUtil.isEmpty(book.getauthor())){
				sb.append(" and b.author like '%"+book.getauthor()+"%'");
			}
			if(book.getbookTypeId()!=null && book.getbookTypeId()!=0){
				sb.append(" and b.type_id="+book.getbookTypeId());
			}
			if(book.getstatus()!=null){
				sb.append(" and b.status="+book.getstatus());
			}
			if(book.getbookId() != null){
				sb.append(" and b.id="+book.getbookId());
			}
			sb.append(" ORDER BY b.status");
			PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sb.toString());
			return pstmt.executeQuery();
			
			
		}
		
		// 图书信息查询(学生)
		public ResultSet listCan(Connection con,Book book)throws Exception{
			StringBuffer sb=new StringBuffer("select b.*,bt.type_name from book b,book_type bt where type_id=bt.id and b.status = 1");
			if(!ToolUtil.isEmpty(book.getbookName())){
				sb.append(" and b.book_name like '%"+book.getbookName()+"%'");
			}
			if(!ToolUtil.isEmpty(book.getauthor())){
				sb.append(" and b.author like '%"+book.getauthor()+"%'");
			}
			if(book.getbookId() != null){
				sb.append(" and b.id="+book.getbookId());
			}
			PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sb.toString());
			return pstmt.executeQuery();
		}
		
		//图书信息删除
		public int delete(Connection con,String id)throws Exception{
			String sql="delete from book where id=?";
			PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
		}
		
		//图书信息修改
		public int update(Connection con,Book book)throws Exception{
			String sql="update book set book_name=?,type_id=?,author=?,publish=?,price=?,number=?,status=?,remark=? where id=?";
			PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, book.getbookName());
			pstmt.setInt(2, book.getbookTypeId());
			pstmt.setString(3, book.getauthor());
			pstmt.setString(4, book.getpublish());
			pstmt.setDouble(5, book.getprice());
			pstmt.setInt(6, book.getnumber());
			pstmt.setInt(7, book.getstatus());
			pstmt.setString(8, book.getremark());
			pstmt.setInt(9, book.getbookId());
			return pstmt.executeUpdate();
		}
}
