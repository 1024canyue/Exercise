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
	private JTextField uNameField;   //�û��������
	private JTextField passField;    //���������
	private JTextField telField;     //�绰���������
	private JLabel uNameLabel;       //�û�����֤��ʾ
	private JLabel uPassLabel;       //������֤��ʾ
	private JLabel uTelLabel;       //�绰������֤��ʾ
	private JTextField textField;
	private JRadioButton nvButton;    //�Ա�ѡ��
	private JRadioButton nanButton;
	private ButtonGroup bg;   //��ѡ������
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
		
		JLabel label_1 = new JLabel("\u65B0\u7528\u6237\u6CE8\u518C");     //���û�ע��
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("������κ", Font.PLAIN, 37));
		label_1.setBounds(141, 25, 225, 63);
		contentPane.add(label_1);
		
		uNameField = new JTextField();      //�û�������
		uNameField.setToolTipText("\u7528\u6237\u540D\u5FC5\u987B\u4E3A1\u3001\u4E2D\u6587\uFF1B2\u3001\u5927\u5C0F\u5199\u5B57\u6BCD\u7EC4\u5408\uFF1B3\u3001\u5927\u5C0F\u5199\u5B57\u6BCD\u4E0E\u6570\u5B57\u7684\u7EC4\u5408\uFF0C\u4E14\u5B57\u6BCD\u5F00\u5934");
		uNameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String text = uNameField.getText();
				if(ToolUtil.isEmpty(text)){
					uNameLabel.setText("�û�������Ϊ��");
					uNameLabel.setForeground(Color.RED);
				}else{
					//���û����������
					boolean flag = text.matches("^[a-zA-z]|{1,}+\\w*|[\u4e00-\u9fa5]{2,4}$");//�û�������Ϊ1�����ģ�2����Сд��ĸ��ϣ�3����Сд��ĸ�����ֵ���ϣ�����ĸ��ͷ
					if (flag){
						uNameLabel.setText("��");     //ͨ����ʾ
						uNameLabel.setForeground(Color.GREEN);
			            }else{
							JOptionPane.showMessageDialog(null, "�û�������Ϊ1�����ģ�2����Сд��ĸ��ϣ�3����Сд��ĸ�����ֵ���ϣ�����ĸ��ͷ");
							uNameLabel.setText("");
					}
				}
			}
		});
		uNameField.setBounds(151, 90, 210, 38);
		contentPane.add(uNameField);
		uNameField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u7528\u6237\u540D:");  //�û���
		label_2.setFont(new Font("��������", Font.BOLD, 22));
		label_2.setBounds(52, 96, 78, 21);
		contentPane.add(label_2);
		
		passField = new JTextField();
		passField.setToolTipText("\u5BC6\u7801\u9700\u4E3A6-16\u4F4D\u6570\u5B57\u548C\u5B57\u6BCD\u7684\u7EC4\u5408");
		passField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String text = passField.getText();
				if(ToolUtil.isEmpty(text)){
					uPassLabel.setText("���벻��Ϊ��");
					uPassLabel.setForeground(Color.RED);
				}else{
					//���û����������
					boolean flag = text.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");//������Ϊ6-16λ���ֺ���ĸ�����
					if (flag){
						uPassLabel.setText("��");
						uPassLabel.setForeground(Color.GREEN);
			            }else{
							JOptionPane.showMessageDialog(null, "������Ϊ6-16λ���ֺ���ĸ�����");
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
		label_2_1.setFont(new Font("��������", Font.BOLD, 22));
		label_2_1.setBounds(52, 147, 78, 21);
		contentPane.add(label_2_1);
		
		telField = new JTextField();
		telField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String text = telField.getText();
				if(ToolUtil.isEmpty(text)){
					uTelLabel.setText("�ֻ��Ų���Ϊ��");
					uTelLabel.setForeground(Color.RED);
				}else{
					//���û����������
					boolean flag = text.matches("^[1][3,7,9][0-9]{9}|[1][4][5-7]{9}|[1][5][0-3][5-9][0-9]{7}|[1][8][0,3,5-9][0-9]{8}$");//�жϵ绰�����Ƿ�Ϲ�
					if (flag){
						uTelLabel.setText("��");
						uTelLabel.setForeground(Color.GREEN);
			            }else{
							JOptionPane.showMessageDialog(null, "��������ȷ���ֻ��Ÿ�ʽ");
							uTelLabel.setText("");
			            }
				}
			
			}
		});
		telField.setColumns(10);
		telField.setBounds(151, 192, 210, 38);
		contentPane.add(telField);
		
		JLabel label_2_1_1 = new JLabel("\u624B\u673A\u53F7:");
		label_2_1_1.setFont(new Font("��������", Font.BOLD, 22));
		label_2_1_1.setBounds(52, 198, 78, 21);
		contentPane.add(label_2_1_1);
		
		uNameLabel = new JLabel("");      //�û�����֤��ʾ
		uNameLabel.setBounds(363, 90, 120, 38);
		contentPane.add(uNameLabel);
		
		uPassLabel = new JLabel("");     //�û���������ʾ
		uPassLabel.setBounds(363, 141, 120, 38);
		contentPane.add(uPassLabel);
		
		uTelLabel = new JLabel("");     //�û��绰��֤��ʾ
		uTelLabel.setBounds(363, 192, 120, 38);
		contentPane.add(uTelLabel);
		
		nvButton = new JRadioButton("��");      //��ѡ����
		nvButton.setForeground(Color.BLACK);
		nvButton.setSelected(true);
		nvButton.setBackground(Color.WHITE);
		nvButton.setFont(new Font("���Ŀ���", Font.PLAIN, 21));
		nvButton.setBounds(152, 248, 57, 27);
		contentPane.add(nvButton);
		
		nanButton = new JRadioButton("Ů");      //��ѡ��Ů
		nanButton.setBackground(Color.WHITE);
		nanButton.setFont(new Font("���Ŀ���", Font.PLAIN, 21));
		nanButton.setBounds(304, 250, 57, 27);
		contentPane.add(nanButton);
		
		bg=new ButtonGroup();    //��������ѡ������һ����
		bg.add(nanButton);
		bg.add(nvButton);
		
		JLabel sexLable = new JLabel("\u6027 \u522B:");  
		sexLable.setHorizontalAlignment(SwingConstants.LEFT);
		sexLable.setFont(new Font("��������", Font.BOLD, 22));
		sexLable.setBounds(52, 250, 78, 21);
		contentPane.add(sexLable);
		
		JLabel sexLable_1 = new JLabel("\u9A8C\u8BC1\u7801:");
		sexLable_1.setHorizontalAlignment(SwingConstants.RIGHT);
		sexLable_1.setFont(new Font("��������", Font.BOLD, 22));
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
				dispose();    //�رյ�ǰ����
				LoginFrm lf = new LoginFrm();   //ʵ�����´���
				lf.setLocationRelativeTo(null);//�´������
				lf.setVisible(true);      //��ʾ�´���
			}
		});
		
		ValidCode vcode = new ValidCode(); //ValidCode��Ϊ�����Զ���õ��࣬���ڲ�����֤��
		vcode.setLocation(299, 289);
		contentPane.add(vcode);
		button_1.setFont(new Font("����", Font.BOLD, 15));
		button_1.setBounds(271, 338, 131, 33);
		contentPane.add(button_1);
		
		JButton regbutton = new JButton("\u6CE8  \u518C");
		regbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {  //ע�ᰴť
				String code=textField.getText().toUpperCase();    //ͳһ��д�������ִ�Сд
				if(ToolUtil.isEmpty(code)){
					JOptionPane.showMessageDialog(null, "��������֤��");
				}else{
					if(code.equalsIgnoreCase(vcode.getCode())){
						RegCheck(arg0); //ע���������Ҫ��RegFrm�������ж���
					}else{
						JOptionPane.showMessageDialog(null, "��֤���������������");
					}
				}
			}

			
		});
		regbutton.setBackground(new Color(220, 220, 220));
		regbutton.setFont(new Font("����", Font.BOLD, 15));
		regbutton.setBounds(84, 338, 131, 33);
		contentPane.add(regbutton);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(RegFrm.class.getResource("/tupian/regBG.png")));
		label.setBounds(0, 0, 495, 389);
		contentPane.add(label);

	}
	protected void RegCheck(ActionEvent e) {    //ע����
		String username=uNameField.getText();
		String password=passField.getText();
		String phone=telField.getText();
		String sex="";
		if(nanButton.isSelected())      //�еĵ�ѡ�ѡ��
		{
			sex=nanButton.getText();
		}
		else
		{
			sex=nvButton.getText();
		}
		if (ToolUtil.isEmpty(username) || ToolUtil.isEmpty(password)||ToolUtil.isEmpty(phone)) {
			JOptionPane.showMessageDialog(null, "�����������Ϣ");
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
		//���ݿⷽ��
		try {
			con = dbUtil.getConnection();//��������ݿ������
			
			int i = userDao.addUser(con, user);//userDaoΪ�Զ���UserDao��Ķ��󣬻�������con����user�����������ӵ����ݿ�user����
			if (i == 2) {
				JOptionPane.showMessageDialog(null, "���û����Ѵ���,������ע��");
			} else if (i == 0) {    //�Ҳ��������п��������ݿ����ʧ��
				JOptionPane.showMessageDialog(null, "ע��ʧ��");
			} else {
				JOptionPane.showMessageDialog(null, "ע��ɹ�");
				dispose();
				LoginFrm frame = new LoginFrm();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		} catch (Exception e1) {
			e1.printStackTrace();      //�����쳣�������쳣��Ϣ
		} finally {
			try {
				dbUtil.closeCon(con);    //���ݿ�����ص�
			} catch (Exception e1) {
				e1.printStackTrace();    //�����쳣�������쳣��Ϣ
			}
		}
	}
}
