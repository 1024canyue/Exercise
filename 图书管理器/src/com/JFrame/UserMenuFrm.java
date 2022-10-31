package com.JFrame;

//import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.dao.BookDao;
import com.dao.BorrowDetailDao;
import com.model.Book;
import com.model.BorrowDetail;
import com.utils.DbUtil;
import com.utils.ToolUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
//import javax.swing.JPasswordField;
import javax.swing.UIManager;

//import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
//import javax.swing.ScrollPaneConstants;
import java.awt.SystemColor;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
//import javax.swing.event.AncestorListener;
//import javax.swing.event.AncestorEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class UserMenuFrm extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane_1;
	private JPanel jiexxpanel;
	private JPanel huanBookpanel;
	private JPanel bookxxpanel;
	private JPanel jiebookpanel;
	private JTable jieTable;
	private JTable BookTable;
	private JLabel label_1;
	private JTextField bookIDField;
	private JTextField cBookField;
	private JButton button;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_2_1;
	private JPanel top;
	private JLabel label_2;
	private JTextField printbookIDField;
	private JTextField bookNameField_1;
	private JLabel label_3;
	private JButton jieBookbutton;
	DbUtil dbUtil = new DbUtil();
	BorrowDetailDao bdetailDao = new BorrowDetailDao();
	private JButton huanBookbutton;
	private JComboBox comboBox;
	BookDao bookDao = new BookDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserMenuFrm() {
		setTitle("\u7528\u6237\u501F\u9605\u7A97\u53E3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 663, 996);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jiexxpanel = new JPanel(); //---------------------------------������Ϣ---------------------------------------//
		jiexxpanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "������Ϣ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		jiexxpanel.setBounds(14, 88, 617, 276);
		contentPane.add(jiexxpanel);
		//��һ����ͷ������  һλ����
		String[] title={"���", "����", "״̬", "����ʱ��", "����ʱ��"};
		//����ĸ����м�¼ ���ÿյĶ�λ����ռλ
		String[][] dates={};
		 //Ȼ��ʵ���� ����2���ؼ�����
		DefaultTableModel model_1=new DefaultTableModel(dates,title);//ʹ��Ĭ�ϵı��ģʽ
		jieTable=new JTable();
		jieTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {            //----------��갴ѹ���
				putBack(e);//ʵ����ʾ/���ػ��鰴ť���Զ�������
			}
		});
		jieTable.setModel(model_1);//���ݱ�ͷ�Ϳռ�¼�������
		scrollPane_1 = new JScrollPane();     //��Ӵ������������
		scrollPane_1.addMouseListener(new MouseAdapter() {});
		scrollPane_1.setBounds(14, 25, 589, 236);
		scrollPane_1.setViewportView(jieTable);    //�����
		putDates(new BorrowDetail());//��ȡ�鼮�������ݱ����ݷ���table��	
		jiexxpanel.setLayout(null);
		jiexxpanel.add(scrollPane_1);
		
		huanBookpanel = new JPanel();//---------------------------------����---------------------------------------//
		huanBookpanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "����", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		huanBookpanel.setBounds(14, 362, 617, 126);
		contentPane.add(huanBookpanel);
		huanBookpanel.setLayout(null);
		
		label_1 = new JLabel("\u7F16\u53F7\uFF1A");
		label_1.setFont(new Font("��������", Font.PLAIN, 19));
		label_1.setBounds(35, 45, 57, 33);
		huanBookpanel.add(label_1);
		
		bookIDField = new JTextField();                 //----------��ʾ��ı��
		bookIDField.setHorizontalAlignment(SwingConstants.CENTER);
		bookIDField.setFont(new Font("����", Font.BOLD, 22));
		bookIDField.setBounds(99, 47, 186, 33);
		huanBookpanel.add(bookIDField);
		bookIDField.setColumns(10);
		
		huanBookbutton = new JButton("\u8FD8   \u4E66");
		huanBookbutton.setEnabled(false);
		huanBookbutton.setVisible(true);
		huanBookbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String BorrowStr = bookIDField.getText();//��ȡ���鰴ť����ı�������ֵ
				if (ToolUtil.isEmpty(BorrowStr)) {
					JOptionPane.showMessageDialog(null, "��ѡ��δ�����鼮");//������鰴ť����ı�������ֵΪ�գ�������û���δѡ��Ҫ�����鼮
					return;
				}
				//����һ�����Ķ���detail��Ϊ��������׼��
				BorrowDetail detail = new BorrowDetail();
				detail.setborrowId(Integer.parseInt(BorrowStr));
				detail.setstatus(2);
				detail.setreturnTime(ToolUtil.getTime());
				Connection con = null;
				try {
					con = dbUtil.getConnection();
					int i = bdetailDao.returnBook(con, detail);//ͨ�����Ĳ��������bdetailDao��ɻ������
					if (i == 1) {
						JOptionPane.showMessageDialog(null, "����ɹ�");
					} else {
						JOptionPane.showMessageDialog(null, "����ʧ��");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ŷ�𣡻��鹦���ڲ��쳣������ϵϵͳ����Ա��");
				}finally{
					try {
						dbUtil.closeCon(con);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				putDates(new BorrowDetail());//���������ˢ���Ϸ��Ľ��ļ�¼��
			
			}
		});

		huanBookbutton.setBackground(SystemColor.controlHighlight);
		huanBookbutton.setFont(new Font("��������", Font.PLAIN, 18));
		huanBookbutton.setBounds(330, 41, 113, 45);
		huanBookpanel.add(huanBookbutton);
		
		JButton exitbutton = new JButton("\u9000\u51FA\u767B\u5F55");        //----------�˳�ϵͳ
		exitbutton.setToolTipText("\u5C06\u9000\u51FA\u60A8\u7684\u767B\u5165\u72B6\u6001");
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "�����������Ʒ����ӭ�ٴ�ʹ��");
				dispose();//�رյ�ǰ�û����ڣ��û��˳�ϵͳ
				LoginFrm frame = new LoginFrm();//���»ص�������棬������һ���û�ʹ��
				frame.setLocationRelativeTo(null);//�������
				frame.setVisible(true);   //������ʾ
			}

		});
		exitbutton.setBackground(SystemColor.controlHighlight);
		exitbutton.setFont(new Font("��������", Font.PLAIN, 18));
		exitbutton.setBounds(477, 41, 113, 45);
		huanBookpanel.add(exitbutton);
		
		bookxxpanel = new JPanel();//---------------------------------�鼮��Ϣ---------------------------------------//
		bookxxpanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "�鼮��Ϣ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		bookxxpanel.setBounds(14, 488, 617, 312);
		contentPane.add(bookxxpanel);
		bookxxpanel.setLayout(null);
		
		cBookField = new JTextField();
		cBookField.setHorizontalAlignment(SwingConstants.CENTER);
		cBookField.setToolTipText("\u7F6E\u7A7A\u53EF\u8FD8\u539F\u67E5\u8BE2\u7ED3\u679C");
		cBookField.setBounds(219, 22, 244, 32);
		bookxxpanel.add(cBookField);
		cBookField.setColumns(10);
		
		button = new JButton("\u67E5  \u8BE2");     //��ѯ��ť
		button.setFont(new Font("������", Font.BOLD, 15));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = comboBox.getSelectedIndex();
				//���������ı���ѡ�������ͼ����Ϣ�Ĳ���
				String in = cBookField.getText();   //��ʱ��������ȡ�ı����ı�
				if(in != ""){
					if(index==0){
						String bookName = in;
						Book book = new Book();
						book.setbookname(bookName);
						//�������涨������ڷ���putDates������ѯ�����鼮��Ϣ��ʾ��table_1���ؼ���
						putDates(book);
					}
					if(index==1){
						String authoerName = in;
						Book book = new Book();
						book.setauthor(authoerName);
						putDates(book);
					}
					if(index==2){
						String bookId = in;
						Book book = new Book();
						book.setbookId(Integer.parseInt(bookId));
						putDates(book);
					}
				}else{putDates(new Book());}   //��ԭ
				
			}
		});
		button.setBackground(SystemColor.controlHighlight);
		button.setBounds(477, 22, 110, 32);
		bookxxpanel.add(button);
		
		scrollPane_2 = new JScrollPane();
		//��һ����ͷ������  һλ����
		String[] title2={"���", "����", "����", "����", "����"};
		//����ĸ����м�¼ ���ÿյĶ�λ����ռλ
		String[][] dates2={};
		//Ȼ��ʵ���� ����2���ؼ�����
		DefaultTableModel model_2=new DefaultTableModel(dates2,title2);//ʹ��Ĭ�ϵı��ģʽ
		BookTable=new JTable();
		BookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableMousePressed(e);
			}
		});
		BookTable.setModel(model_2);//���ݱ�ͷ�Ϳռ�¼�������
		scrollPane_2_1 = new JScrollPane();     //��Ӵ������������
		scrollPane_2_1.setBounds(14, 66, 589, 242);
		scrollPane_2_1.setViewportView(BookTable);    //�����
		putDates(new Book());//��ȡ�鼮�������ݱ����ݷ���table_2��
		bookxxpanel.setLayout(null);
		bookxxpanel.add(scrollPane_2_1);
		scrollPane_2_1.setBounds(14, 66, 589, 229);
		bookxxpanel.add(scrollPane_2_1);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel());
		comboBox.addItem("�鼮����");
		comboBox.addItem("�鼮����");
		comboBox.addItem("�鼮���");
		comboBox.setBounds(27, 22, 178, 32);
		bookxxpanel.add(comboBox);
		
		jiebookpanel = new JPanel();//---------------------------------����---------------------------------------//
		jiebookpanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "����", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		jiebookpanel.setBounds(14, 800, 617, 136);
		contentPane.add(jiebookpanel);
		jiebookpanel.setLayout(null);
		
		label_2 = new JLabel("\u7F16\u53F7\uFF1A");
		label_2.setFont(new Font("����", Font.BOLD, 21));
		label_2.setBounds(31, 59, 72, 25);
		jiebookpanel.add(label_2);
		
		printbookIDField = new JTextField();
		printbookIDField.setHorizontalAlignment(SwingConstants.CENTER);
		printbookIDField.setFont(new Font("����", Font.BOLD, 22));
		printbookIDField.setBounds(102, 58, 82, 32);
		jiebookpanel.add(printbookIDField);
		printbookIDField.setColumns(10);
		
		bookNameField_1 = new JTextField();
		bookNameField_1.setHorizontalAlignment(SwingConstants.CENTER);
		bookNameField_1.setColumns(10);
		bookNameField_1.setBounds(276, 59, 188, 32);
		jiebookpanel.add(bookNameField_1);
		
		label_3 = new JLabel("\u4E66\u540D\uFF1A");
		label_3.setFont(new Font("����", Font.BOLD, 21));
		label_3.setBounds(211, 59, 72, 25);
		jiebookpanel.add(label_3);
		
		jieBookbutton = new JButton("\u501F  \u4E66");
		jieBookbutton.setFont(new Font("����", Font.BOLD, 15));
		jieBookbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//����ı������ݣ���š�����
				String bookId = printbookIDField.getText();
				String bookName = bookNameField_1.getText();
				
				if (ToolUtil.isEmpty(bookId) || ToolUtil.isEmpty(bookName)) {
					JOptionPane.showMessageDialog(null, "��ѡ������鼮");
					return;
				}
				//���ݵ�¼���û���Ϣ���û��������š���������һ�����Ķ���
				BorrowDetail borrowDetail = new BorrowDetail();
				borrowDetail.setuserId(LoginFrm.currentUser.getuserID());//ͨ����¼���ڣ�����ѵ�¼�û�����Ϣ
				borrowDetail.setbookId(Integer.parseInt(bookId));//���ַ���bookIdת������
				borrowDetail.setstatus(1);//��1����ʾ�ڽ裻��2����ʾ�ѻ����ȸ��ݡ��ڽ衱��������ͼ���ѯ
				borrowDetail.setborrowTime(ToolUtil.getTime());//��ȡ��ǰʱ��Ϊ����ʱ��
				Connection con = null;
				try {
					
					con = dbUtil.getConnection();
					//���Ĳ�������bdetailDao�������ݿ�����con�ͽ��Ķ���borrowDetail���Ȳ�ѯͼ�����ݱ����Ƿ��и������Ƿ��ڡ��ڽ衱״̬
					ResultSet list = bdetailDao.list(con, borrowDetail);
					//����������ڽ�״̬,��ʾ�û�"�������ڽ�,���Ȼ��ٽ�"
					while(list.next()){
						JOptionPane.showMessageDialog(null, "�������ڽ�,���Ȼ��ٽ�");
						return;//���ش�����״̬
					}
					//������鲻���ڽ�״̬����ͨ�����Ķ���bdetailDao��ɽ�������������µĽ��ļ�¼��ӵ��������ݱ�borrowdetail��
					int i = bdetailDao.add(con, borrowDetail);
					//���borrowDetail���ݱ�ɹ����᷵��1����������ɹ�
					if (i == 1) {
						putDates(new BorrowDetail());   //����ɹ���ˢ�½������ݱ�
						JOptionPane.showMessageDialog(null, "����ɹ�");
					} else {
						JOptionPane.showMessageDialog(null, "����ʧ�ܣ�������");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();  //���������Ϣ
					JOptionPane.showMessageDialog(null, "Ŷ�𣡽��鹦���ڲ��쳣������ϵϵͳ����Ա��");
				}finally{
					try {
						dbUtil.closeCon(con);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			
		});
		jieBookbutton.setBackground(SystemColor.controlHighlight);
		jieBookbutton.setBounds(490, 53, 99, 43);
		jiebookpanel.add(jieBookbutton);
				
		top = new JPanel();     //---------------------------------ͷ����ɫ����---------------------------------------//
		top.setBounds(14, 13, 617, 98);
		contentPane.add(top);
		top.setLayout(null);
				
		JLabel TopLable = new JLabel("");                   //ͷ����ӭ����
		TopLable.setText(LoginFrm.currentUser.getuserName()+" ��ӭ����");
		TopLable.setBounds(55, 27, 513, 34);
		top.add(TopLable);
		TopLable.setFont(new Font("�����п�", Font.PLAIN, 32));
		TopLable.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("\u00A9 \u8F6F\u4E8C\u6797\u6CF0\u5723");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 10));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(258, 932, 125, 18);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("");//---------------------------------����---------------------------------------//
		label.setBounds(0, -208, 645, 1157);
		contentPane.add(label);
		label.setIcon(new ImageIcon(UserMenuFrm.class.getResource("/tupian/uBG02.png")));
	}


	private void putDates(BorrowDetail borrowDetail) {         //---------------------����ı��������
		DefaultTableModel model_1 = (DefaultTableModel) jieTable.getModel();
		model_1.setRowCount(0);
		Integer userId = LoginFrm.currentUser.getuserID();
		Connection con = null;
		try {
			con = dbUtil.getConnection();      //sql�ĵ�ǰ�û���Ϣ
			borrowDetail.setuserId(userId);		//������Ķ���
          //���ݵ�ǰ��¼���û�id��userId���ӽ������ݱ��в�ѯ��صĽ��ļ�¼
			ResultSet list = bdetailDao.list(con, borrowDetail);   //��ѯͼ��������ݱ�ͼ�����ݱ���û����ݱ������
			while (list.next()) {
				Vector rowData = new Vector();
				rowData.add(list.getInt("id"));
				rowData.add(list.getString("book_name"));
				int status = list.getInt("status");
				if (status == 1) {
					rowData.add("�ڽ�");
				}
				if (status == 2) {
					rowData.add("�ѻ�");
				}
				rowData.add(ToolUtil.getDateByTime(list.getLong("borrow_time"))); //��ʽ������ʾ����ʱ��
				if (status == 2) {
					rowData.add(ToolUtil.getDateByTime(list.getLong("return_time"))); //��ʽ������ʾ����ʱ��
				}
			model_1.addRow(rowData);//����ѯ�����ʾ�ڱ��ؼ�table��
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	private void putDates(Book book) {                //-----------------���鼮���������
		DefaultTableModel model_1 = (DefaultTableModel) BookTable.getModel();
		model_1.setRowCount(0);
		Connection con = null;
		try {
			con = dbUtil.getConnection();
			book.setstatus(1);//�鼮״̬��1����ʾ�ϼܣ���0����ʾ�¼�
			//ͨ��ͼ����������bookDao��ʵ��ͼ����Ϣ�Ĳ�ѯ�����õ���ѧ����ѯ����listCan
			ResultSet list = bookDao.listCan(con, book);
	        //ͼ����Ϣ�����ҵ����򷵻���ص�ͼ����Ϣ
			while (list.next()) {
				Vector rowData = new Vector();
				//table_1�����ݵ����
				rowData.add(list.getInt("id"));
				rowData.add(list.getString("book_name"));
				rowData.add(list.getString("type_name"));
				rowData.add(list.getString("author"));
				rowData.add(list.getString("remark"));
				model_1.addRow(rowData);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	protected void putBack(MouseEvent e){   //-------------------------ʵ����ʾ/���ػ��鰴ť���Զ�������
		int row = jieTable.getSelectedRow();//�к�Ϊ�����ѡ�����
		Integer borrowId = (Integer) jieTable.getValueAt(row, 0);//��ȡ��1�н��ļ�¼���
		String status = (String) jieTable.getValueAt(row, 2);//��ȡ��3��Ϊ״̬��ֵ
		//���·��ı�Ŷ�Ӧ���ı�����ʾΪ�������ͼ���¼�ı��
		bookIDField.setText(borrowId.toString());
		//������ڽ裬�ͼ���鰴ť�������鰴ť������
		if (status.equals("�ڽ�")) {
			huanBookbutton.setText("��  ��");
			huanBookbutton.setToolTipText("����Թ黹����");
			huanBookbutton.setEnabled(true);
		} else {
			huanBookbutton.setText("��  ��");
			huanBookbutton.setToolTipText("	�������ٴι黹����");
			huanBookbutton.setEnabled(false);
		}
		
	

		
	}
	protected void tableMousePressed(MouseEvent evt) {
		int row = BookTable.getSelectedRow();
		Object bookId = BookTable.getValueAt(row, 0);
		Object bookName = BookTable.getValueAt(row, 1);
//���û����ѡ���ͼ���Ӧ����š�������ʾ���ı�����
		printbookIDField.setText(bookId.toString());
		bookNameField_1.setText(bookName.toString());
		
	}
}
