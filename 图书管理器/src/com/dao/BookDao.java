package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.Book;
import com.utils.ToolUtil;
/*
 * ����һ������ͼ���������BookDao��ר������ʵ��ͼ�������ɾ���ġ������
 * ͼ���ѯ�����ַ�����һ����Թ���Ա��list��һ�����ѧ����listcan
 * ͼ����ӡ�ɾ�����޸ķ���������Թ���Ա
 */
public class BookDao {
	// ͼ�����
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
		
		// ͼ����Ϣ��ѯ������Ա��
		public ResultSet list(Connection con,Book book)throws Exception{
			//��book���ݱ�book_type���ݱ��н���ͼ����Ϣ�Ĳ�ѯ���������������������û������
			StringBuffer sb=new StringBuffer("select b.*,bt.type_name from book b,book_type bt where b.type_id=bt.id");
			//�����û�������������в�ѯ
			if(!ToolUtil.isEmpty(book.getbookName())){
				sb.append(" and b.book_name like '%"+book.getbookName()+"%'");
			}
			//�����û���������߽��в�ѯ
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
		
		// ͼ����Ϣ��ѯ(ѧ��)
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
		
		//ͼ����Ϣɾ��
		public int delete(Connection con,String id)throws Exception{
			String sql="delete from book where id=?";
			PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
		}
		
		//ͼ����Ϣ�޸�
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
