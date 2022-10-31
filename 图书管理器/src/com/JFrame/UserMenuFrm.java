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
		
		jiexxpanel = new JPanel(); //---------------------------------借阅信息---------------------------------------//
		jiexxpanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "借阅信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		jiexxpanel.setBounds(14, 88, 617, 276);
		contentPane.add(jiexxpanel);
		//做一个表头栏数据  一位数组
		String[] title={"编号", "书名", "状态", "借书时间", "还书时间"};
		//具体的各栏行记录 先用空的二位数组占位
		String[][] dates={};
		 //然后实例化 上面2个控件对象
		DefaultTableModel model_1=new DefaultTableModel(dates,title);//使用默认的表格模式
		jieTable=new JTable();
		jieTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {            //----------鼠标按压表格
				putBack(e);//实现显示/隐藏还书按钮与自动输入编号
			}
		});
		jieTable.setModel(model_1);//根据表头和空记录创建表格
		scrollPane_1 = new JScrollPane();     //添加带滚动条的面板
		scrollPane_1.addMouseListener(new MouseAdapter() {});
		scrollPane_1.setBounds(14, 25, 589, 236);
		scrollPane_1.setViewportView(jieTable);    //塞表格
		putDates(new BorrowDetail());//获取书籍借阅数据表数据放置table中	
		jiexxpanel.setLayout(null);
		jiexxpanel.add(scrollPane_1);
		
		huanBookpanel = new JPanel();//---------------------------------还书---------------------------------------//
		huanBookpanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "还书", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		huanBookpanel.setBounds(14, 362, 617, 126);
		contentPane.add(huanBookpanel);
		huanBookpanel.setLayout(null);
		
		label_1 = new JLabel("\u7F16\u53F7\uFF1A");
		label_1.setFont(new Font("华文中宋", Font.PLAIN, 19));
		label_1.setBounds(35, 45, 57, 33);
		huanBookpanel.add(label_1);
		
		bookIDField = new JTextField();                 //----------显示书的编号
		bookIDField.setHorizontalAlignment(SwingConstants.CENTER);
		bookIDField.setFont(new Font("宋体", Font.BOLD, 22));
		bookIDField.setBounds(99, 47, 186, 33);
		huanBookpanel.add(bookIDField);
		bookIDField.setColumns(10);
		
		huanBookbutton = new JButton("\u8FD8   \u4E66");
		huanBookbutton.setEnabled(false);
		huanBookbutton.setVisible(true);
		huanBookbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String BorrowStr = bookIDField.getText();//获取还书按钮左边文本框的书号值
				if (ToolUtil.isEmpty(BorrowStr)) {
					JOptionPane.showMessageDialog(null, "请选择未还的书籍");//如果还书按钮左边文本框的书号值为空，则表明用户还未选择要还的书籍
					return;
				}
				//创建一个借阅对象detail，为还书做好准备
				BorrowDetail detail = new BorrowDetail();
				detail.setborrowId(Integer.parseInt(BorrowStr));
				detail.setstatus(2);
				detail.setreturnTime(ToolUtil.getTime());
				Connection con = null;
				try {
					con = dbUtil.getConnection();
					int i = bdetailDao.returnBook(con, detail);//通过借阅操作类对象bdetailDao完成还书操作
					if (i == 1) {
						JOptionPane.showMessageDialog(null, "还书成功");
					} else {
						JOptionPane.showMessageDialog(null, "还书失败");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "哦吼！还书功能内部异常，请联系系统管理员！");
				}finally{
					try {
						dbUtil.closeCon(con);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				putDates(new BorrowDetail());//还书操作后，刷新上方的借阅记录表
			
			}
		});

		huanBookbutton.setBackground(SystemColor.controlHighlight);
		huanBookbutton.setFont(new Font("华文中宋", Font.PLAIN, 18));
		huanBookbutton.setBounds(330, 41, 113, 45);
		huanBookpanel.add(huanBookbutton);
		
		JButton exitbutton = new JButton("\u9000\u51FA\u767B\u5F55");        //----------退出系统
		exitbutton.setToolTipText("\u5C06\u9000\u51FA\u60A8\u7684\u767B\u5165\u72B6\u6001");
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "请带好随身物品，欢迎再次使用");
				dispose();//关闭当前用户窗口，用户退出系统
				LoginFrm frame = new LoginFrm();//重新回到登入界面，方便下一个用户使用
				frame.setLocationRelativeTo(null);//窗体居中
				frame.setVisible(true);   //窗体显示
			}

		});
		exitbutton.setBackground(SystemColor.controlHighlight);
		exitbutton.setFont(new Font("华文中宋", Font.PLAIN, 18));
		exitbutton.setBounds(477, 41, 113, 45);
		huanBookpanel.add(exitbutton);
		
		bookxxpanel = new JPanel();//---------------------------------书籍信息---------------------------------------//
		bookxxpanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "书籍信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		bookxxpanel.setBounds(14, 488, 617, 312);
		contentPane.add(bookxxpanel);
		bookxxpanel.setLayout(null);
		
		cBookField = new JTextField();
		cBookField.setHorizontalAlignment(SwingConstants.CENTER);
		cBookField.setToolTipText("\u7F6E\u7A7A\u53EF\u8FD8\u539F\u67E5\u8BE2\u7ED3\u679C");
		cBookField.setBounds(219, 22, 244, 32);
		bookxxpanel.add(cBookField);
		cBookField.setColumns(10);
		
		button = new JButton("\u67E5  \u8BE2");     //查询按钮
		button.setFont(new Font("新宋体", Font.BOLD, 15));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = comboBox.getSelectedIndex();
				//根据下拉文本框选择项，进行图书信息的查找
				String in = cBookField.getText();   //临时变量，获取文本框文本
				if(in != ""){
					if(index==0){
						String bookName = in;
						Book book = new Book();
						book.setbookname(bookName);
						//调用下面定义的类内方法putDates，将查询到的书籍信息显示在table_1表格控件中
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
				}else{putDates(new Book());}   //还原
				
			}
		});
		button.setBackground(SystemColor.controlHighlight);
		button.setBounds(477, 22, 110, 32);
		bookxxpanel.add(button);
		
		scrollPane_2 = new JScrollPane();
		//做一个表头栏数据  一位数组
		String[] title2={"编号", "书名", "类型", "作者", "描述"};
		//具体的各栏行记录 先用空的二位数组占位
		String[][] dates2={};
		//然后实例化 上面2个控件对象
		DefaultTableModel model_2=new DefaultTableModel(dates2,title2);//使用默认的表格模式
		BookTable=new JTable();
		BookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableMousePressed(e);
			}
		});
		BookTable.setModel(model_2);//根据表头和空记录创建表格
		scrollPane_2_1 = new JScrollPane();     //添加带滚动条的面板
		scrollPane_2_1.setBounds(14, 66, 589, 242);
		scrollPane_2_1.setViewportView(BookTable);    //塞表格
		putDates(new Book());//获取书籍借阅数据表数据放置table_2中
		bookxxpanel.setLayout(null);
		bookxxpanel.add(scrollPane_2_1);
		scrollPane_2_1.setBounds(14, 66, 589, 229);
		bookxxpanel.add(scrollPane_2_1);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel());
		comboBox.addItem("书籍名称");
		comboBox.addItem("书籍作者");
		comboBox.addItem("书籍编号");
		comboBox.setBounds(27, 22, 178, 32);
		bookxxpanel.add(comboBox);
		
		jiebookpanel = new JPanel();//---------------------------------借书---------------------------------------//
		jiebookpanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "借书", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		jiebookpanel.setBounds(14, 800, 617, 136);
		contentPane.add(jiebookpanel);
		jiebookpanel.setLayout(null);
		
		label_2 = new JLabel("\u7F16\u53F7\uFF1A");
		label_2.setFont(new Font("宋体", Font.BOLD, 21));
		label_2.setBounds(31, 59, 72, 25);
		jiebookpanel.add(label_2);
		
		printbookIDField = new JTextField();
		printbookIDField.setHorizontalAlignment(SwingConstants.CENTER);
		printbookIDField.setFont(new Font("宋体", Font.BOLD, 22));
		printbookIDField.setBounds(102, 58, 82, 32);
		jiebookpanel.add(printbookIDField);
		printbookIDField.setColumns(10);
		
		bookNameField_1 = new JTextField();
		bookNameField_1.setHorizontalAlignment(SwingConstants.CENTER);
		bookNameField_1.setColumns(10);
		bookNameField_1.setBounds(276, 59, 188, 32);
		jiebookpanel.add(bookNameField_1);
		
		label_3 = new JLabel("\u4E66\u540D\uFF1A");
		label_3.setFont(new Font("宋体", Font.BOLD, 21));
		label_3.setBounds(211, 59, 72, 25);
		jiebookpanel.add(label_3);
		
		jieBookbutton = new JButton("\u501F  \u4E66");
		jieBookbutton.setFont(new Font("宋体", Font.BOLD, 15));
		jieBookbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获得文本框数据：编号、书名
				String bookId = printbookIDField.getText();
				String bookName = bookNameField_1.getText();
				
				if (ToolUtil.isEmpty(bookId) || ToolUtil.isEmpty(bookName)) {
					JOptionPane.showMessageDialog(null, "请选择相关书籍");
					return;
				}
				//根据登录的用户信息、用户输入的书号、书名创建一个借阅对象
				BorrowDetail borrowDetail = new BorrowDetail();
				borrowDetail.setuserId(LoginFrm.currentUser.getuserID());//通过登录窗口，获得已登录用户的信息
				borrowDetail.setbookId(Integer.parseInt(bookId));//将字符串bookId转成整数
				borrowDetail.setstatus(1);//“1”表示在借；“2”表示已还，先根据“在借”条件进行图书查询
				borrowDetail.setborrowTime(ToolUtil.getTime());//获取当前时间为借书时间
				Connection con = null;
				try {
					
					con = dbUtil.getConnection();
					//借阅操作对象bdetailDao根据数据库连接con和借阅对象borrowDetail，先查询图书数据表中是否有该书且是否处于“在借”状态
					ResultSet list = bdetailDao.list(con, borrowDetail);
					//如果该书是在借状态,提示用户"该书已在借,请先还再借"
					while(list.next()){
						JOptionPane.showMessageDialog(null, "该书已在借,请先还再借");
						return;//返回待借书状态
					}
					//如果该书不是在借状态，则通过借阅对象bdetailDao完成借书操作，即将新的借阅记录添加到借阅数据表borrowdetail中
					int i = bdetailDao.add(con, borrowDetail);
					//添加borrowDetail数据表成功，会返回1，表明借书成功
					if (i == 1) {
						putDates(new BorrowDetail());   //借书成功，刷新借阅数据表
						JOptionPane.showMessageDialog(null, "借书成功");
					} else {
						JOptionPane.showMessageDialog(null, "借书失败，请重试");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();  //输出错误信息
					JOptionPane.showMessageDialog(null, "哦吼！借书功能内部异常，请联系系统管理员！");
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
				
		top = new JPanel();     //---------------------------------头部白色区域---------------------------------------//
		top.setBounds(14, 13, 617, 98);
		contentPane.add(top);
		top.setLayout(null);
				
		JLabel TopLable = new JLabel("");                   //头部欢迎文字
		TopLable.setText(LoginFrm.currentUser.getuserName()+" 欢迎您！");
		TopLable.setBounds(55, 27, 513, 34);
		top.add(TopLable);
		TopLable.setFont(new Font("华文行楷", Font.PLAIN, 32));
		TopLable.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("\u00A9 \u8F6F\u4E8C\u6797\u6CF0\u5723");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 10));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(258, 932, 125, 18);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("");//---------------------------------背景---------------------------------------//
		label.setBounds(0, -208, 645, 1157);
		contentPane.add(label);
		label.setIcon(new ImageIcon(UserMenuFrm.class.getResource("/tupian/uBG02.png")));
	}


	private void putDates(BorrowDetail borrowDetail) {         //---------------------向借阅表添加数据
		DefaultTableModel model_1 = (DefaultTableModel) jieTable.getModel();
		model_1.setRowCount(0);
		Integer userId = LoginFrm.currentUser.getuserID();
		Connection con = null;
		try {
			con = dbUtil.getConnection();      //sql的当前用户信息
			borrowDetail.setuserId(userId);		//处理借阅对象
          //根据当前登录的用户id（userId）从借阅数据表中查询相关的借阅记录
			ResultSet list = bdetailDao.list(con, borrowDetail);   //查询图书借阅数据表、图书数据表和用户数据表的数据
			while (list.next()) {
				Vector rowData = new Vector();
				rowData.add(list.getInt("id"));
				rowData.add(list.getString("book_name"));
				int status = list.getInt("status");
				if (status == 1) {
					rowData.add("在借");
				}
				if (status == 2) {
					rowData.add("已还");
				}
				rowData.add(ToolUtil.getDateByTime(list.getLong("borrow_time"))); //格式化并显示借阅时间
				if (status == 2) {
					rowData.add(ToolUtil.getDateByTime(list.getLong("return_time"))); //格式化并显示还书时间
				}
			model_1.addRow(rowData);//将查询结果显示在表格控件table中
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
	private void putDates(Book book) {                //-----------------向书籍表添加数据
		DefaultTableModel model_1 = (DefaultTableModel) BookTable.getModel();
		model_1.setRowCount(0);
		Connection con = null;
		try {
			con = dbUtil.getConnection();
			book.setstatus(1);//书籍状态“1”表示上架；“0”表示下架
			//通过图书操作类对象bookDao，实现图书信息的查询，调用的是学生查询方法listCan
			ResultSet list = bookDao.listCan(con, book);
	        //图书信息若查找到，则返回相关的图书信息
			while (list.next()) {
				Vector rowData = new Vector();
				//table_1行数据的添加
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
	protected void putBack(MouseEvent e){   //-------------------------实现显示/隐藏还书按钮与自动输入编号
		int row = jieTable.getSelectedRow();//行号为鼠标点击选择的行
		Integer borrowId = (Integer) jieTable.getValueAt(row, 0);//获取第1列借阅记录编号
		String status = (String) jieTable.getValueAt(row, 2);//获取第3列为状态的值
		//将下方的编号对应的文本框显示为鼠标点击的图书记录的编号
		bookIDField.setText(borrowId.toString());
		//如果书在借，就激活还书按钮，否则还书按钮不可用
		if (status.equals("在借")) {
			huanBookbutton.setText("还  书");
			huanBookbutton.setToolTipText("点击以归还此书");
			huanBookbutton.setEnabled(true);
		} else {
			huanBookbutton.setText("已  还");
			huanBookbutton.setToolTipText("	您无需再次归还此书");
			huanBookbutton.setEnabled(false);
		}
		
	

		
	}
	protected void tableMousePressed(MouseEvent evt) {
		int row = BookTable.getSelectedRow();
		Object bookId = BookTable.getValueAt(row, 0);
		Object bookName = BookTable.getValueAt(row, 1);
//将用户点击选择的图书对应的书号、书名显示在文本框中
		printbookIDField.setText(bookId.toString());
		bookNameField_1.setText(bookName.toString());
		
	}
}
