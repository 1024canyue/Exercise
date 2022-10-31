package com.JFrame;

//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dao.UserDao;
import com.model.User;
import com.utils.DbUtil;
import com.utils.ToolUtil;

//import java.awt.Dialog.ModalExclusionType;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.SystemColor;
//import java.awt.Window.Type;
import java.awt.Point;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrm extends JFrame {
	public static User currentUser;   //当前用户(公有静态)成员变量
	private JPanel contentPane;
	private JTextField uNameField;
	private JPasswordField uPassField;
	private JButton loginButton;    //登入按钮
	private JButton zcButton;
	private JLabel label_2;
	private JLabel label_3;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrm frame = new LoginFrm();
					frame.setLocationRelativeTo(null);//窗体居中
					frame.setVisible(true);   //窗体显示
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrm() {
		setType(Type.UTILITY);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrm.class.getResource("/tupian/ison.png")));
		setFont(new Font("微软雅黑", Font.PLAIN, 14));
		setTitle("\u6B22\u8FCE\u4F7F\u7528\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF  V1.1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("用户名：");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(new Color(30, 144, 255));
		label.setBounds(101, 158, 112, 24);
		label.setFont(new Font("华文新魏", Font.PLAIN, 28));
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("密码：");
		label_1.setForeground(new Color(30, 144, 255));
		label_1.setBounds(129, 195, 84, 24);
		label_1.setFont(new Font("华文新魏", Font.PLAIN, 28));
		contentPane.add(label_1);
		
		label_2 = new JLabel("\u597D\u597D\u5B66\u4E60  \u5929\u5929\u5411\u4E0A");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(UIManager.getColor("Button.background"));
		label_2.setFont(new Font("华文楷体", Font.BOLD, 35));
		label_2.setBounds(101, 56, 335, 53);
		contentPane.add(label_2);
		
		JLabel lblGoodGoodStudy = new JLabel("good good study      day  day  up  !!");
		lblGoodGoodStudy.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 18));
		lblGoodGoodStudy.setForeground(SystemColor.menu);
		lblGoodGoodStudy.setBounds(116, 104, 301, 24);
		contentPane.add(lblGoodGoodStudy);
		
		JLabel label_4 = new JLabel("\u00A9 \u8F6F\u4E8C\u6797\u6CF0\u5723");
		label_4.setForeground(Color.LIGHT_GRAY);
		label_4.setFont(new Font("宋体", Font.PLAIN, 12));
		label_4.setBounds(227, 325, 84, 18);
		contentPane.add(label_4);
		
		uNameField = new JTextField();
		uNameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				loginButton.setText("登  录");
			}
		});
		uNameField.setBackground(SystemColor.menu);
		uNameField.setSelectedTextColor(Color.LIGHT_GRAY);
		uNameField.setToolTipText("");
		uNameField.setBounds(227, 158, 185, 30);
		uNameField.setFont(new Font("华文新魏", Font.PLAIN, 17));
		contentPane.add(uNameField);
		uNameField.setColumns(10);
		
		uPassField = new JPasswordField();    //密码框
		uPassField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				loginButton.setText("登  录");
			}
		});
		uPassField.setHorizontalAlignment(SwingConstants.LEFT);
		uPassField.setEchoChar('*');
		uPassField.setBackground(SystemColor.menu);
		uPassField.setBounds(227, 198, 185, 30);
		uPassField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 29));
		uPassField.setColumns(10);
		contentPane.add(uPassField);
		
		loginButton = new JButton("\u767B  \u5F55");     //登入按钮
		loginButton.setBackground(SystemColor.menu);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {   //响应事件
				loginButton.setText("登录中。。。");    //没达到预期的效果
				checkLogin(e);//点击“登录”按钮后，对用户输入的信息进行检测
			}
		});
		loginButton.setBounds(101, 259, 119, 44);
		loginButton.setFont(new Font("宋体", Font.BOLD, 16));
		contentPane.add(loginButton);
		
		zcButton = new JButton("\u6CE8  \u518C");     //注册按钮
		zcButton.setToolTipText("\u6CA1\u6709\u7528\u6237\uFF1F\uFF1F");
		zcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();  //关闭当前窗口
				RegFrm rf = new RegFrm();   //实例化新窗口
				rf.setLocationRelativeTo(null);//新窗体居中
				rf.setVisible(true);    //显示新窗口
			}
		});
		zcButton.setBackground(SystemColor.menu);
		zcButton.setBounds(300, 259, 119, 44);
		zcButton.setFont(new Font("宋体", Font.BOLD, 16));
		contentPane.add(zcButton);
		
		label_3 = new JLabel("");
		label_3.setLocation(new Point(500, 300));
		label_3.setIcon(new ImageIcon(LoginFrm.class.getResource("/tupian/BG.png")));
		label_3.setBounds(0, 0, 529, 343);
		contentPane.add(label_3);
	}
	protected void checkLogin(ActionEvent e) {    //点击登入按钮的方法
		String userName = uNameField.getText();    //获取文本框用户名
		String password = String.valueOf(uPassField.getPassword());    //获取密码(哈希值)-->(字符串)
		//用事先定义好的ToolUtil类的静态的判空方法isEmpty(String str)方法对用户名和用户密码的输入进行判空操作
		if (ToolUtil.isEmpty(userName) || ToolUtil.isEmpty(password)) {   //判空
			JOptionPane.showMessageDialog(null, "用户名和密码不能为空");
			return;
		}
		
		User user = new User();
		user.setuserName(userName);
		user.setPassword(password);
		DbUtil dbUtil=new DbUtil();   //数据库工具包对象
		UserDao userDao=new UserDao();
		Connection con = null;
		
		try {   //有任何其他问题
			con = dbUtil.getConnection();//先获得与数据库bookmanager的连接
			User login = userDao.login(con, user);//通过数据库连接和用户实例user进行登录操作（UserDao返回的就是给它）
			currentUser = login;//静态变量currentUser用来向用户窗口传递登录的用户对象，并传递用户信息
			if (login == null) {    //链接上数据库，但结果为空
				JOptionPane.showMessageDialog(null, "登录失败:请核对用户名或密码！");
			} else {
				//登录成功，打开用户界面
				dispose();//关闭当前登录窗口
//				System.out.println("登录成功！！");
				UserMenuFrm frame=new UserMenuFrm();//打开用户界面
				frame.setLocationRelativeTo(null);//窗体居中
				frame.setVisible(true);
			}
		} catch (Exception e21) {
			e21.printStackTrace();
			JOptionPane.showMessageDialog(null, "哦吼！登录异常。请联系系统管理员！");
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e31) {

				e31.printStackTrace();
			}
		}
		
	 }
}
