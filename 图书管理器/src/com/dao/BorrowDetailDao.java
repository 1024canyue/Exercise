package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.BorrowDetail;
/*
 * ����һ������ͼ����Ĳ�������BorrowDetailDao��ר������ʵ�ֽ��ļ�¼�Ĳ�ѯ����ӡ��޸ģ����н��ļ�¼���޸Ľ�����Թ���Ա
 */
public class BorrowDetailDao {
    /**
     * �÷������ڽ������ǰ�������û��������š���������ͼ���Ƿ�ɽ����Ϣ��ѯ
     * @param con
     * @param borrowDetail
     * @return
     * @throws Exception
     */
	public ResultSet list(Connection con,BorrowDetail borrowDetail)throws Exception{
		//�༭��ѯͼ��������ݱ�borrowdetail��ͼ�����ݱ���û����ݱ��SQL��䣬��ѯ����С�bd����ʾborrowdetail���ݱ���u����ʾuser���ݱ���b����ʾbook���ݱ�
		StringBuffer sb=new StringBuffer("SELECT bd.*,u.username,b.book_name from borrowdetail bd,user u,book b where u.id=bd.user_id and b.id=bd.book_id");
		
		if(borrowDetail.getuserId() != null){
			sb.append(" and u.id = ?");
		}
		//getStatus()���ͼ��״̬�ķ���  1��ʾ�ڽ�  2��ʾ�ѻ�
		if(borrowDetail.getstatus() != null){
			sb.append(" and bd.status = ?");
		}
		if(borrowDetail.getbookId() != null){
			sb.append(" and bd.book_id = ?");
		}
		//����ѯ�������ͼ���id�Ž�������
		sb.append("  order by bd.id");
		//��PreparedStatement��ʵ����һ������pstmt��ͨ���ö���ʵ��ͼ����Ϣ��ѯ
		PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sb.toString());
		//��sb�ַ����е�һ���ʺţ������ݽ�������
		if(borrowDetail.getuserId() != null){
			pstmt.setInt(1, borrowDetail.getuserId());
		}
		if(borrowDetail.getstatus() != null && borrowDetail.getbookId() != null){
			pstmt.setInt(2, borrowDetail.getstatus());
			pstmt.setInt(3, borrowDetail.getbookId());
		}
		//���ز�ѯ���
		return pstmt.executeQuery();
	}
    /**
     * �÷������ڽ�������󣬽����ļ�¼��ӵ��������ݱ�borrowdetail��
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
		return pstmt.executeUpdate();//������ӽ��
	}
    /**
     * �÷��������޸Ľ�����Ϣ
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
