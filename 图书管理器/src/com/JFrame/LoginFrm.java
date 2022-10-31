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
	public static User currentUser;   //��ǰ�û�(���о�̬)��Ա����
	private JPanel contentPane;
	private JTextField uNameField;
	private JPasswordField uPassField;
	private JButton loginButton;    //���밴ť
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
					frame.setLocationRelativeTo(null);//�������
					frame.setVisible(true);   //������ʾ
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
		setFont(new Font("΢���ź�", Font.PLAIN, 14));
		setTitle("\u6B22\u8FCE\u4F7F\u7528\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF  V1.1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("�û�����");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(new Color(30, 144, 255));
		label.setBounds(101, 158, 112, 24);
		label.setFont(new Font("������κ", Font.PLAIN, 28));
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("���룺");
		label_1.setForeground(new Color(30, 144, 255));
		label_1.setBounds(129, 195, 84, 24);
		label_1.setFont(new Font("������κ", Font.PLAIN, 28));
		contentPane.add(label_1);
		
		label_2 = new JLabel("\u597D\u597D\u5B66\u4E60  \u5929\u5929\u5411\u4E0A");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(UIManager.getColor("Button.background"));
		label_2.setFont(new Font("���Ŀ���", Font.BOLD, 35));
		label_2.setBounds(101, 56, 335, 53);
		contentPane.add(label_2);
		
		JLabel lblGoodGoodStudy = new JLabel("good good study      day  day  up  !!");
		lblGoodGoodStudy.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 18));
		lblGoodGoodStudy.setForeground(SystemColor.menu);
		lblGoodGoodStudy.setBounds(116, 104, 301, 24);
		contentPane.add(lblGoodGoodStudy);
		
		JLabel label_4 = new JLabel("\u00A9 \u8F6F\u4E8C\u6797\u6CF0\u5723");
		label_4.setForeground(Color.LIGHT_GRAY);
		label_4.setFont(new Font("����", Font.PLAIN, 12));
		label_4.setBounds(227, 325, 84, 18);
		contentPane.add(label_4);
		
		uNameField = new JTextField();
		uNameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				loginButton.setText("��  ¼");
			}
		});
		uNameField.setBackground(SystemColor.menu);
		uNameField.setSelectedTextColor(Color.LIGHT_GRAY);
		uNameField.setToolTipText("");
		uNameField.setBounds(227, 158, 185, 30);
		uNameField.setFont(new Font("������κ", Font.PLAIN, 17));
		contentPane.add(uNameField);
		uNameField.setColumns(10);
		
		uPassField = new JPasswordField();    //�����
		uPassField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				loginButton.setText("��  ¼");
			}
		});
		uPassField.setHorizontalAlignment(SwingConstants.LEFT);
		uPassField.setEchoChar('*');
		uPassField.setBackground(SystemColor.menu);
		uPassField.setBounds(227, 198, 185, 30);
		uPassField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 29));
		uPassField.setColumns(10);
		contentPane.add(uPassField);
		
		loginButton = new JButton("\u767B  \u5F55");     //���밴ť
		loginButton.setBackground(SystemColor.menu);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {   //��Ӧ�¼�
				loginButton.setText("��¼�С�����");    //û�ﵽԤ�ڵ�Ч��
				checkLogin(e);//�������¼����ť�󣬶��û��������Ϣ���м��
			}
		});
		loginButton.setBounds(101, 259, 119, 44);
		loginButton.setFont(new Font("����", Font.BOLD, 16));
		contentPane.add(loginButton);
		
		zcButton = new JButton("\u6CE8  \u518C");     //ע�ᰴť
		zcButton.setToolTipText("\u6CA1\u6709\u7528\u6237\uFF1F\uFF1F");
		zcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();  //�رյ�ǰ����
				RegFrm rf = new RegFrm();   //ʵ�����´���
				rf.setLocationRelativeTo(null);//�´������
				rf.setVisible(true);    //��ʾ�´���
			}
		});
		zcButton.setBackground(SystemColor.menu);
		zcButton.setBounds(300, 259, 119, 44);
		zcButton.setFont(new Font("����", Font.BOLD, 16));
		contentPane.add(zcButton);
		
		label_3 = new JLabel("");
		label_3.setLocation(new Point(500, 300));
		label_3.setIcon(new ImageIcon(LoginFrm.class.getResource("/tupian/BG.png")));
		label_3.setBounds(0, 0, 529, 343);
		contentPane.add(label_3);
	}
	protected void checkLogin(ActionEvent e) {    //������밴ť�ķ���
		String userName = uNameField.getText();    //��ȡ�ı����û���
		String password = String.valueOf(uPassField.getPassword());    //��ȡ����(��ϣֵ)-->(�ַ���)
		//�����ȶ���õ�ToolUtil��ľ�̬���пշ���isEmpty(String str)�������û������û��������������пղ���
		if (ToolUtil.isEmpty(userName) || ToolUtil.isEmpty(password)) {   //�п�
			JOptionPane.showMessageDialog(null, "�û��������벻��Ϊ��");
			return;
		}
		
		User user = new User();
		user.setuserName(userName);
		user.setPassword(password);
		DbUtil dbUtil=new DbUtil();   //���ݿ⹤�߰�����
		UserDao userDao=new UserDao();
		Connection con = null;
		
		try {   //���κ���������
			con = dbUtil.getConnection();//�Ȼ�������ݿ�bookmanager������
			User login = userDao.login(con, user);//ͨ�����ݿ����Ӻ��û�ʵ��user���е�¼������UserDao���صľ��Ǹ�����
			currentUser = login;//��̬����currentUser�������û����ڴ��ݵ�¼���û����󣬲������û���Ϣ
			if (login == null) {    //���������ݿ⣬�����Ϊ��
				JOptionPane.showMessageDialog(null, "��¼ʧ��:��˶��û��������룡");
			} else {
				//��¼�ɹ������û�����
				dispose();//�رյ�ǰ��¼����
//				System.out.println("��¼�ɹ�����");
				UserMenuFrm frame=new UserMenuFrm();//���û�����
				frame.setLocationRelativeTo(null);//�������
				frame.setVisible(true);
			}
		} catch (Exception e21) {
			e21.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ŷ�𣡵�¼�쳣������ϵϵͳ����Ա��");
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e31) {

				e31.printStackTrace();
			}
		}
		
	 }
}
