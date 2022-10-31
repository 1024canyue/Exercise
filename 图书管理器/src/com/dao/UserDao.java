package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.User;

public class UserDao {
	//向数据库添加数据
	public int addUser(Connection con,User user) throws Exception{
		//查询注册用户名是否存在
		String sql = "select * from user where userName=? ";//根据用户名在用户数据表user中进行查找
	    PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
		pstmt.setString(1,user.getuserName());
	    ResultSet rs = pstmt.executeQuery();
	    //如果用户已存在，证明之前已经注册过了，就返回整型数字2
	    if(rs.next()){
	    	return 2;   //该用户已存在
	    }
	    //如果是新用户，则根据用户输入的信息将用户信息添加到用户数据包user中
	    sql="insert into user (username,password,sex,phone) values (?,?,?,?)";
	    PreparedStatement pstmt2=(PreparedStatement) con.prepareStatement(sql);
		pstmt2.setString(1, user.getuserName());     //第一个问号 
		pstmt2.setString(2, user.getPassword());    //第二个
		pstmt2.setString(3,user.getSex());     //第三个
		pstmt2.setString(4,user.getPhone());   //第四个
		return pstmt2.executeUpdate();//返回更新结果
	}
	//定义根据用户名进行查找的方法
	public User login(Connection con,User user)throws SQLException {
		User resultUser = null;//若登录成功，则返回结果用户对象
		String sql = "select * from user where username=? and password=?";    //SQL:查询用户输入的文本框数据查询Mysql数据库数据
	    PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);//通过Connection类对象，根据用户名，密码，角色的值，在用户数据表user进行查询
		//设置通配符“？”的值
	    pstmt.setString(1,user.getuserName()); //第一个问号
		pstmt.setString(2,user.getPassword());  //第二个问号
		//执行查询
	    ResultSet rs = pstmt.executeQuery();     //查询结果ResultSet（结果set）集
	    //如果找到该用户，以User对象的形式返回
	    if(rs.next()){   //集合的next方法用来访问数据库
	    	resultUser = new User();
	    	resultUser.setuserID(rs.getInt("id"));     //获取指定字段的数据给要返回的对象（resultUser）
	    	resultUser.setuserName(rs.getString("username"));
	    	resultUser.setSex(rs.getString("sex"));
	    	resultUser.setPhone(rs.getString("phone"));
	    }
		return resultUser;    //返回给调用者（登录按钮）
	}

}
