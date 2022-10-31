package com.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dao.UserDao;
import com.model.User;
import com.utils.DbUtil;
import com.utils.ToolUtil;
import com.JFrame.ValidCode;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.awt.color.*;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class RegFrm extends JFrame {

	private JPanel contentPane;
	private JTextField uNameField;   //用户名输入框
	private JTextField passField;    //密码输入框
	private JTextField telField;     //电话号码输入框
	private JLabel uNameLabel;       //用户名验证提示
	private JLabel uPassLabel;       //密码验证提示
	private JLabel uTelLabel;       //电话号码验证提示
	private JTextField textField;
	private JRadioButton nvButton;    //性别单选框
	private JRadioButton nanButton;
	private ButtonGroup bg;   //单选项打包组
	private DbUtil dbUtil;
	private UserDao userDao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					beautyeye.
					
//					RegFrm frame = new RegFrm();
//					frame.setLocationRelativeTo(null);
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegFrm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegFrm.class.getResource("/tupian/bg2.png")));
		setTitle("\u65B0\u7528\u6237\u6CE8\u518C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel("\u65B0\u7528\u6237\u6CE8\u518C");     //新用户注册
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("华文新魏", Font.PLAIN, 37));
		label_1.setBounds(141, 25, 225, 63);
		contentPane.add(label_1);
		
		uNameField = new JTextField();      //用户名输入
		uNameField.setToolTipText("\u7528\u6237\u540D\u5FC5\u987B\u4E3A1\u3001\u4E2D\u6587\uFF1B2\u3001\u5927\u5C0F\u5199\u5B57\u6BCD\u7EC4\u5408\uFF1B3\u3001\u5927\u5C0F\u5199\u5B57\u6BCD\u4E0E\u6570\u5B57\u7684\u7EC4\u5408\uFF0C\u4E14\u5B57\u6BCD\u5F00\u5934");
		uNameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String text = uNameField.getText();
				if(ToolUtil.isEmpty(text)){
					uNameLabel.setText("用户名不能为空");
					uNameLabel.setForeground(Color.RED);
				}else{
					//对用户名进行审核
					boolean flag = text.matches("^[a-zA-z]|{1,}+\\w*|[\u4e00-\u9fa5]{2,4}$");//用户名必须为1、中文；2、大小写字母组合；3、大小写字母与数字的组合，且字母开头
					if (flag){
						uNameLabel.setText("√");     //通过提示
						uNameLabel.setForeground(Color.GREEN);
			            }else{
							JOptionPane.showMessageDialog(null, "用户名必须为1、中文；2、大小写字母组合；3、大小写字母与数字的组合，且字母开头");
							uNameLabel.setText("");
					}
				}
			}
		});
		uNameField.setBounds(151, 90, 210, 38);
		contentPane.add(uNameField);
		uNameField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u7528\u6237\u540D:");  //用户名
		label_2.setFont(new Font("华文宋体", Font.BOLD, 22));
		label_2.setBounds(52, 96, 78, 21);
		contentPane.add(label_2);
		
		passField = new JTextField();
		passField.setToolTipText("\u5BC6\u7801\u9700\u4E3A6-16\u4F4D\u6570\u5B57\u548C\u5B57\u6BCD\u7684\u7EC4\u5408");
		passField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String text = passField.getText();
				if(ToolUtil.isEmpty(text)){
					uPassLabel.setText("密码不能为空");
					uPassLabel.setForeground(Color.RED);
				}else{
					//对用户名进行审核
					boolean flag = text.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");//密码需为6-16位数字和字母的组合
					if (flag){
						uPassLabel.setText("√");
						uPassLabel.setForeground(Color.GREEN);
			            }else{
							JOptionPane.showMessageDialog(null, "密码需为6-16位数字和字母的组合");
							uPassLabel.setText("");
			            }
				}
			}
		});
		passField.setColumns(10);
		passField.setBounds(151, 141, 210, 38);
		contentPane.add(passField);
		
		JLabel label_2_1 = new JLabel("\u5BC6 \u7801:");
		label_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_2_1.setFont(new Font("华文宋体", Font.BOLD, 22));
		label_2_1.setBounds(52, 147, 78, 21);
		contentPane.add(label_2_1);
		
		telField = new JTextField();
		telField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String text = telField.getText();
				if(ToolUtil.isEmpty(text)){
					uTelLabel.setText("手机号不能为空");
					uTelLabel.setForeground(Color.RED);
				}else{
					//对用户名进行审核
					boolean flag = text.matches("^[1][3,7,9][0-9]{9}|[1][4][5-7]{9}|[1][5][0-3][5-9][0-9]{7}|[1][8][0,3,5-9][0-9]{8}$");//判断电话号码是否合规
					if (flag){
						uTelLabel.setText("√");
						uTelLabel.setForeground(Color.GREEN);
			            }else{
							JOptionPane.showMessageDialog(null, "请输入正确的手机号格式");
							uTelLabel.setText("");
			            }
				}
			
			}
		});
		telField.setColumns(10);
		telField.setBounds(151, 192, 210, 38);
		contentPane.add(telField);
		
		JLabel label_2_1_1 = new JLabel("\u624B\u673A\u53F7:");
		label_2_1_1.setFont(new Font("华文宋体", Font.BOLD, 22));
		label_2_1_1.setBounds(52, 198, 78, 21);
		contentPane.add(label_2_1_1);
		
		uNameLabel = new JLabel("");      //用户名验证显示
		uNameLabel.setBounds(363, 90, 120, 38);
		contentPane.add(uNameLabel);
		
		uPassLabel = new JLabel("");     //用户名密码显示
		uPassLabel.setBounds(363, 141, 120, 38);
		contentPane.add(uPassLabel);
		
		uTelLabel = new JLabel("");     //用户电话验证显示
		uTelLabel.setBounds(363, 192, 120, 38);
		contentPane.add(uTelLabel);
		
		nvButton = new JRadioButton("男");      //单选项男
		nvButton.setForeground(Color.BLACK);
		nvButton.setSelected(true);
		nvButton.setBackground(Color.WHITE);
		nvButton.setFont(new Font("华文楷体", Font.PLAIN, 21));
		nvButton.setBounds(152, 248, 57, 27);
		contentPane.add(nvButton);
		
		nanButton = new JRadioButton("女");      //单选项女
		nanButton.setBackground(Color.WHITE);
		nanButton.setFont(new Font("华文楷体", Font.PLAIN, 21));
		nanButton.setBounds(304, 250, 57, 27);
		contentPane.add(nanButton);
		
		bg=new ButtonGroup();    //将两个但选项打包成一个组
		bg.add(nanButton);
		bg.add(nvButton);
		
		JLabel sexLable = new JLabel("\u6027 \u522B:");  
		sexLable.setHorizontalAlignment(SwingConstants.LEFT);
		sexLable.setFont(new Font("华文宋体", Font.BOLD, 22));
		sexLable.setBounds(52, 250, 78, 21);
		contentPane.add(sexLable);
		
		JLabel sexLable_1 = new JLabel("\u9A8C\u8BC1\u7801:");
		sexLable_1.setHorizontalAlignment(SwingConstants.RIGHT);
		sexLable_1.setFont(new Font("华文宋体", Font.BOLD, 22));
		sexLable_1.setBounds(52, 295, 78, 21);
		contentPane.add(sexLable_1);
		
		textField = new JTextField();
		textField.setBounds(151, 292, 131, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button_1 = new JButton("\u8FD4\u56DE\u767B\u5F55\u754C\u9762");
		button_1.setBackground(new Color(220, 220, 220));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();    //关闭当前窗口
				LoginFrm lf = new LoginFrm();   //实例化新窗口
				lf.setLocationRelativeTo(null);//新窗体居中
				lf.setVisible(true);      //显示新窗口
			}
		});
		
		ValidCode vcode = new ValidCode(); //ValidCode类为事先自定义好的类，用于产生验证码
		vcode.setLocation(299, 289);
		contentPane.add(vcode);
		button_1.setFont(new Font("宋体", Font.BOLD, 15));
		button_1.setBounds(271, 338, 131, 33);
		contentPane.add(button_1);
		
		JButton regbutton = new JButton("\u6CE8  \u518C");
		regbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {  //注册按钮
				String code=textField.getText().toUpperCase();    //统一大写：不区分大小写
				if(ToolUtil.isEmpty(code)){
					JOptionPane.showMessageDialog(null, "请输入验证码");
				}else{
					if(code.equalsIgnoreCase(vcode.getCode())){
						RegCheck(arg0); //注册监听器需要在RegFrm类中自行定义
					}else{
						JOptionPane.showMessageDialog(null, "验证码错误，请重新输入");
					}
				}
			}

			
		});
		regbutton.setBackground(new Color(220, 220, 220));
		regbutton.setFont(new Font("宋体", Font.BOLD, 15));
		regbutton.setBounds(84, 338, 131, 33);
		contentPane.add(regbutton);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(RegFrm.class.getResource("/tupian/regBG.png")));
		label.setBounds(0, 0, 495, 389);
		contentPane.add(label);

	}
	protected void RegCheck(ActionEvent e) {    //注册检查
		String username=uNameField.getText();
		String password=passField.getText();
		String phone=telField.getText();
		String sex="";
		if(nanButton.isSelected())      //男的单选项被选中
		{
			sex=nanButton.getText();
		}
		else
		{
			sex=nvButton.getText();
		}
		if (ToolUtil.isEmpty(username) || ToolUtil.isEmpty(password)||ToolUtil.isEmpty(phone)) {
			JOptionPane.showMessageDialog(null, "请输入相关信息");
			return;
		}
		User user = new User();
		user.setuserName(username);
		user.setPassword(password);
		user.setSex(sex);
		user.setPhone(phone);
		Connection con = null;
		dbUtil=new DbUtil();
		userDao=new UserDao();
		//数据库方面
		try {
			con = dbUtil.getConnection();//获得与数据库的连接
			
			int i = userDao.addUser(con, user);//userDao为自定义UserDao类的对象，基于连接con，将user对象的数据添加到数据库user表中
			if (i == 2) {
				JOptionPane.showMessageDialog(null, "该用户名已存在,请重新注册");
			} else if (i == 0) {    //找不到对象，有可能是数据库访问失败
				JOptionPane.showMessageDialog(null, "注册失败");
			} else {
				JOptionPane.showMessageDialog(null, "注册成功");
				dispose();
				LoginFrm frame = new LoginFrm();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		} catch (Exception e1) {
			e1.printStackTrace();      //出现异常，返回异常信息
		} finally {
			try {
				dbUtil.closeCon(con);    //数据库用完关掉
			} catch (Exception e1) {
				e1.printStackTrace();    //出现异常，返回异常信息
			}
		}
	}
}
