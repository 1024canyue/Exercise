package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.User;

public class UserDao {
	//�����ݿ��������
	public int addUser(Connection con,User user) throws Exception{
		//��ѯע���û����Ƿ����
		String sql = "select * from user where userName=? ";//�����û������û����ݱ�user�н��в���
	    PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
		pstmt.setString(1,user.getuserName());
	    ResultSet rs = pstmt.executeQuery();
	    //����û��Ѵ��ڣ�֤��֮ǰ�Ѿ�ע����ˣ��ͷ�����������2
	    if(rs.next()){
	    	return 2;   //���û��Ѵ���
	    }
	    //��������û���������û��������Ϣ���û���Ϣ��ӵ��û����ݰ�user��
	    sql="insert into user (username,password,sex,phone) values (?,?,?,?)";
	    PreparedStatement pstmt2=(PreparedStatement) con.prepareStatement(sql);
		pstmt2.setString(1, user.getuserName());     //��һ���ʺ� 
		pstmt2.setString(2, user.getPassword());    //�ڶ���
		pstmt2.setString(3,user.getSex());     //������
		pstmt2.setString(4,user.getPhone());   //���ĸ�
		return pstmt2.executeUpdate();//���ظ��½��
	}
	//��������û������в��ҵķ���
	public User login(Connection con,User user)throws SQLException {
		User resultUser = null;//����¼�ɹ����򷵻ؽ���û�����
		String sql = "select * from user where username=? and password=?";    //SQL:��ѯ�û�������ı������ݲ�ѯMysql���ݿ�����
	    PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);//ͨ��Connection����󣬸����û��������룬��ɫ��ֵ�����û����ݱ�user���в�ѯ
		//����ͨ�����������ֵ
	    pstmt.setString(1,user.getuserName()); //��һ���ʺ�
		pstmt.setString(2,user.getPassword());  //�ڶ����ʺ�
		//ִ�в�ѯ
	    ResultSet rs = pstmt.executeQuery();     //��ѯ���ResultSet�����set����
	    //����ҵ����û�����User�������ʽ����
	    if(rs.next()){   //���ϵ�next���������������ݿ�
	    	resultUser = new User();
	    	resultUser.setuserID(rs.getInt("id"));     //��ȡָ���ֶε����ݸ�Ҫ���صĶ���resultUser��
	    	resultUser.setuserName(rs.getString("username"));
	    	resultUser.setSex(rs.getString("sex"));
	    	resultUser.setPhone(rs.getString("phone"));
	    }
		return resultUser;    //���ظ������ߣ���¼��ť��
	}

}
