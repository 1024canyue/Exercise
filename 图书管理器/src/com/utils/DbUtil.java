package com.utils;
import java.sql.Connection;
import java.sql.DriverManager;
public class DbUtil {    //由于进行数据库的连接与断开
	private String dbDriver= "com.mysql.cj.jdbc.Driver";
	private String dbUrl="jdbc:mysql://localhost:3306/bookmanager?characterEncoding=utf-8";  //本地访问固定格式
	private String dbUserName = "root";  //数据库用户
	private String dbPassword = "123456";  //数据库登入密码
	
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
