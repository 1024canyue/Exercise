package com.utils;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;
import com.model.User;
public class ToolUtil {
	public static boolean isEmpty(String str){
		if(str != null && !"".equals(str.trim())){   //输入有效性判断：判断空或一堆空格,顺带取头尾空格
			return false;
		}return true;
	}
	
	public static Long getTime(){    //获取当前时间戳
		long t = System.currentTimeMillis();
		return t;
	}
	
	public static String getDateByTime(Long time){     //长整形时间戳的格式化
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str =format.format(new Date(time));
		return str;
	}
	
	public static User getUser(HttpSession session){   //
		User u = (User) session.getAttribute("user");  //获取登录对象
		return u;
	}
	
	public static void setUser(HttpSession session,User u){
		session.setAttribute("user", u);    //存储登录对象
	}
}
