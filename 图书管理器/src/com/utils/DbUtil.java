package com.utils;
import java.sql.Connection;
import java.sql.DriverManager;
public class DbUtil {    //���ڽ������ݿ��������Ͽ�
	private String dbDriver= "com.mysql.cj.jdbc.Driver";
	private String dbUrl="jdbc:mysql://localhost:3306/bookmanager?characterEncoding=utf-8";  //���ط��ʹ̶���ʽ
	private String dbUserName = "root";  //���ݿ��û�
	private String dbPassword = "123456";  //���ݿ��������
	
	public Connection getConnection()throws Exception{
		Class.forName(dbDriver);
		Connection con = (Connection) DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		return con;
	}
	public void closeCon(Connection con)throws Exception{
		if(con!=null){
			con.close();
		}
	}
}
