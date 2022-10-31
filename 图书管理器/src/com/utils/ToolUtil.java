package com.utils;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;
import com.model.User;
public class ToolUtil {
	public static boolean isEmpty(String str){
		if(str != null && !"".equals(str.trim())){   //������Ч���жϣ��жϿջ�һ�ѿո�,˳��ȡͷβ�ո�
			return false;
		}return true;
	}
	
	public static Long getTime(){    //��ȡ��ǰʱ���
		long t = System.currentTimeMillis();
		return t;
	}
	
	public static String getDateByTime(Long time){     //������ʱ����ĸ�ʽ��
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str =format.format(new Date(time));
		return str;
	}
	
	public static User getUser(HttpSession session){   //
		User u = (User) session.getAttribute("user");  //��ȡ��¼����
		return u;
	}
	
	public static void setUser(HttpSession session,User u){
		session.setAttribute("user", u);    //�洢��¼����
	}
}
