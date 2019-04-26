package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import bll.ArticleTypeDao;
import bll.BookDao;
import bll.BooksDao;
import bll.CodeDao;
import bll.EditorsDao;
import bll.IdentifierDao;
import bll.Reference1Dao;
import bll.Reference2Dao;
import bll.SizeDao;
import model.ArticleType;
import model.Bjs_Code;
import model.Bjs_Identifier;
import model.Book;
import model.Books;
import model.Editors;
import model.Reference1;
import model.Size;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BooksFrame extends JFrame {

	private JPanel contentPane,BooksInfoPanel,SettingPanel,SortPanel,searchPanel;
	private JScrollPane BooksInfoScrollPane;
	private TitledBorder titledBorder1,titledBorder2,titledBorder3,titledBorder4;
	private JLabel bookNameLabel;
	private JLabel bookNumberLabel;
	private JLabel openLabel;
	private JLabel languageLabel,printNumberLabel,bjsLabel,authorLabel,categoryLabel,priceLabel,timeLabel,editorLabel;
	private JTextField bookNameTextField;
	private JTextField bookNumberTextField;
	private JTextField authorTextField;
	private JComboBox openComboBox,languageComboBox,bjsComboBox,editorComboBox,categoryComboBox;
	private JSpinner printNumberSpinner,timeSpinner;
	private JButton closeButton,deleteButton,resetButton,saveButton;
	private JTable booksTable;
	private static int CURRENTID = 0;
	private DefaultTableModel tableModel;
	private String head[]=null;  //表格列名
	private Object [][]data=null;   //表格行表
	private JTextField searchTextField;
	private JRadioButton bookNumRadioButton,bookNameRadioButton,bjsRadioButton,publishRadioButton,bookTypeRadioButton;
	private ButtonGroup selectedGroup;
	private JTextField priceTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try
				    {		
						BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;			
						org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();			
						UIManager.put("RootPane.setupButtonVisible", false);
				      //设置此开关量为false即表示关闭之，BeautyEye LNF中默认是true
				        BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
				    }
				    catch(Exception e)
				    {
				        //TODO exception
				    }
					BooksFrame frame = new BooksFrame();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BooksFrame() {
		setTitle("\u56FE\u4E66\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 741);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		

		searchPanel = new JPanel();
		titledBorder3 = new TitledBorder(null, "\u68C0\u7D22", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 102, 153));
		titledBorder3.setTitleFont(new Font("宋体", Font.PLAIN, 20));
		searchPanel.setBorder(titledBorder3);
		searchPanel.setBounds(14, 0, 323, 69);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);
		
		searchTextField = new JTextField();
		searchTextField.setFont(new Font("宋体", Font.PLAIN, 20));
		searchTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RefreshListPanel();
				booksTable.clearSelection();
				CURRENTID = 0; //当前用户id清零
				bookNameTextField.setText("");
				authorTextField.setText("");
				bookNumberTextField.setText("");
				priceTextField.setText("");
				openComboBox.setSelectedIndex(0);
				languageComboBox.setSelectedIndex(0);
				bjsComboBox.setSelectedIndex(0);
				editorComboBox.setSelectedIndex(0);
				categoryComboBox.setSelectedIndex(0);
				printNumberSpinner.setValue(0);
				try {
					timeSpinner.setValue(new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				deleteButton.setEnabled(false);
				titledBorder2.setTitle("新增");
				SettingPanel.repaint();
			}
		});
		searchTextField.setBounds(47, 26, 258, 30);
		searchPanel.add(searchTextField);
		searchTextField.setColumns(10);
		
		SortPanel = new JPanel();
		titledBorder4 = new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u6392\u5E8F\u65B9\u5F0F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 102, 153));
		titledBorder4.setTitleFont(new Font("宋体", Font.PLAIN, 20));
		SortPanel.setBorder(titledBorder4);
		SortPanel.setBounds(351, 0, 542, 69);
		contentPane.add(SortPanel);
		SortPanel.setLayout(null);
		
		bookNumRadioButton = new JRadioButton("\u4E66\u53F7");
		bookNumRadioButton.setFont(new Font("宋体", Font.PLAIN, 20));
		bookNumRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RefreshListPanel();
			}
		});
		bookNumRadioButton.setSelected(true);
		bookNumRadioButton.setBounds(21, 22, 77, 38);
		SortPanel.add(bookNumRadioButton);
		
		bookNameRadioButton = new JRadioButton("\u4E66\u540D");
		bookNameRadioButton.setFont(new Font("宋体", Font.PLAIN, 20));
		bookNameRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RefreshListPanel();
			}
		});
		bookNameRadioButton.setBounds(104, 22, 77, 38);
		SortPanel.add(bookNameRadioButton);
		
		bjsRadioButton = new JRadioButton("\u7F16\u8F91\u5BA4");
		bjsRadioButton.setFont(new Font("宋体", Font.PLAIN, 20));
		bjsRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RefreshListPanel();
			}
		});
		bjsRadioButton.setBounds(187, 22, 101, 38);
		SortPanel.add(bjsRadioButton);
		
		publishRadioButton = new JRadioButton("\u51FA\u7248\u65F6\u95F4");
		publishRadioButton.setFont(new Font("宋体", Font.PLAIN, 20));
		publishRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RefreshListPanel();
			}
		});
		publishRadioButton.setBounds(294, 22, 116, 38);
		SortPanel.add(publishRadioButton);
		
		bookTypeRadioButton = new JRadioButton("\u56FE\u4E66\u5206\u7C7B");
		bookTypeRadioButton.setFont(new Font("宋体", Font.PLAIN, 20));
		bookTypeRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RefreshListPanel();
			}
		});
		bookTypeRadioButton.setBounds(416, 22, 116, 38);
		SortPanel.add(bookTypeRadioButton);
		
		selectedGroup = new ButtonGroup();//单选组
		selectedGroup.add(bookNumRadioButton);
		selectedGroup.add(bookNameRadioButton);
		selectedGroup.add(bjsRadioButton);
		selectedGroup.add(publishRadioButton);
		selectedGroup.add(bookTypeRadioButton);
		
		BooksInfoPanel = new JPanel();
		titledBorder1 = new TitledBorder(null, "共XXX本", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 102, 153));
		titledBorder1.setTitleFont(new Font("宋体", Font.PLAIN, 20));
		BooksInfoPanel.setBorder(titledBorder1);
		BooksInfoPanel.setBounds(14, 70, 879, 368);
		contentPane.add(BooksInfoPanel);
		BooksInfoPanel.setLayout(null);
		
		BooksInfoScrollPane = new JScrollPane();
		BooksInfoScrollPane.setBounds(14, 26, 848, 326);
		BooksInfoPanel.add(BooksInfoScrollPane);
		
		//初始化表格
		booksTable = new JTable();
		booksTable.setRowHeight(30);
		booksTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		head=new String[] {"书名","作者","书号","图书分类","开本","印数","定价","出版时间","文种","编辑室","责任编辑"};
		tableModel=new DefaultTableModel(queryData(),head){
		public boolean isCellEditable(int row, int column)
		{
			return false;
			}
		};
		booksTable.setFont(new Font("宋体", Font.BOLD, 20));
		booksTable.addMouseListener(new MouseAdapter() {   //用户名表格点击事件
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				titledBorder2.setTitle("修改");
				SettingPanel.repaint();
				//填充编辑室编辑框
				String bookName = booksTable.getValueAt(booksTable.getSelectedRow(), 0).toString();
		    	String authorName = booksTable.getValueAt(booksTable.getSelectedRow(), 1).toString();
				String bookNumber = booksTable.getValueAt(booksTable.getSelectedRow(), 2).toString();
				String bookTypeID = booksTable.getValueAt(booksTable.getSelectedRow(), 3).toString();
				String sizeID = booksTable.getValueAt(booksTable.getSelectedRow(), 4).toString();
				String printNumber = booksTable.getValueAt(booksTable.getSelectedRow(), 5).toString();
				String price = booksTable.getValueAt(booksTable.getSelectedRow(), 6).toString();
				String publishTime = booksTable.getValueAt(booksTable.getSelectedRow(), 7).toString();
				String articleType = booksTable.getValueAt(booksTable.getSelectedRow(), 8).toString();
				String bjsNameID = booksTable.getValueAt(booksTable.getSelectedRow(), 9).toString();
				String bjsEditorName = booksTable.getValueAt(booksTable.getSelectedRow(), 10).toString();
				bookNameTextField.setText(bookName);
				authorTextField.setText(authorName);
				bookNumberTextField.setText(bookNumber);
				priceTextField.setText(price);
				openComboBox.setSelectedItem(sizeID);
				languageComboBox.setSelectedItem(articleType);
				bjsComboBox.setSelectedItem(bjsNameID);
				editorComboBox.setSelectedItem(bjsEditorName);
				categoryComboBox.setSelectedItem(bookTypeID);
				printNumberSpinner.setValue(Integer.valueOf(printNumber));
				try {
					timeSpinner.setValue(new SimpleDateFormat("yyyy-MM-dd").parse(publishTime));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				CURRENTID = BooksDao.getInstance().QueryIDByBookName(bookName);//获得当前选中的id
				deleteButton.setEnabled(true);
				saveButton.setEnabled(true);
			}
		});
		ActionMap am = (ActionMap) UIManager.get("Table.actionMap");  
		am.put("selectNextRowCell", new AbstractAction() {  
		    @Override  
		    public void actionPerformed(ActionEvent e) {  
		    	titledBorder2.setTitle("修改");
				SettingPanel.repaint();
		        // 自己的处理代码  
		    	String bookName = booksTable.getValueAt(booksTable.getSelectedRow(), 0).toString();
		    	String authorName = booksTable.getValueAt(booksTable.getSelectedRow(), 1).toString();
				String bookNumber = booksTable.getValueAt(booksTable.getSelectedRow(), 2).toString();
				String bookTypeID = booksTable.getValueAt(booksTable.getSelectedRow(), 3).toString();
				String sizeID = booksTable.getValueAt(booksTable.getSelectedRow(), 4).toString();
				String printNumber = booksTable.getValueAt(booksTable.getSelectedRow(), 5).toString();
				String price = booksTable.getValueAt(booksTable.getSelectedRow(), 6).toString();
				String publishTime = booksTable.getValueAt(booksTable.getSelectedRow(), 7).toString();
				String articleType = booksTable.getValueAt(booksTable.getSelectedRow(), 8).toString();
				String bjsNameID = booksTable.getValueAt(booksTable.getSelectedRow(), 9).toString();
				String bjsEditorName = booksTable.getValueAt(booksTable.getSelectedRow(), 10).toString();
				bookNameTextField.setText(bookName);
				authorTextField.setText(authorName);
				bookNumberTextField.setText(bookNumber);
				priceTextField.setText(price);
				openComboBox.setSelectedItem(sizeID);
				languageComboBox.setSelectedItem(articleType);
				bjsComboBox.setSelectedItem(bjsNameID);
				editorComboBox.setSelectedItem(bjsEditorName);
				categoryComboBox.setSelectedItem(bookTypeID);
				printNumberSpinner.setValue(Integer.valueOf(printNumber));
				try {
					timeSpinner.setValue(new SimpleDateFormat("yyyy-MM-dd").parse(publishTime));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				CURRENTID = BooksDao.getInstance().QueryIDByBookName(bookName);//获得当前选中的id
				deleteButton.setEnabled(true);
				saveButton.setEnabled(true);
		    }  
		});  
		booksTable.setActionMap(am); 
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		booksTable.setDefaultRenderer(Object.class, r);
		booksTable.setModel(tableModel);
		booksTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		BooksInfoScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);  //设置水平滚动条需要时可见
		FitTableColumns(booksTable);
		BooksInfoScrollPane.setViewportView(booksTable);
		
		SettingPanel = new JPanel();
		titledBorder2 = new TitledBorder(UIManager.getBorder("TitledBorder.border"), "新增", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 102, 153));
		titledBorder2.setTitleFont(new Font("宋体", Font.PLAIN, 20));
		SettingPanel.setBorder(titledBorder2);
		SettingPanel.setBounds(14, 439, 879, 248);
		contentPane.add(SettingPanel);
		SettingPanel.setLayout(null);
		
		bookNameLabel = new JLabel("\u4E66\u540D");
		bookNameLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		bookNameLabel.setBounds(14, 24, 72, 18);
		SettingPanel.add(bookNameLabel);
		
		bookNumberLabel = new JLabel("\u4E66\u53F7");
		bookNumberLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		bookNumberLabel.setBounds(14, 60, 72, 18);
		SettingPanel.add(bookNumberLabel);
		
		openLabel = new JLabel("\u5F00\u672C*");
		openLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		openLabel.setBounds(14, 95, 72, 18);
		SettingPanel.add(openLabel);
		
		languageLabel = new JLabel("\u6587\u79CD");
		languageLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		languageLabel.setBounds(14, 126, 72, 18);
		SettingPanel.add(languageLabel);
		
		bookNameTextField = new JTextField();
		bookNameTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				authorTextField.requestFocus();
			}
		});
		bookNameTextField.setFont(new Font("宋体", Font.PLAIN, 20));
		bookNameTextField.setBounds(64, 20, 345, 26);
		SettingPanel.add(bookNameTextField);
		bookNameTextField.setColumns(10);
		bookNameTextField.requestFocus();
		
		bookNumberTextField = new JTextField();
		bookNumberTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoryComboBox.requestFocus();
			}
		});
		bookNumberTextField.setFont(new Font("宋体", Font.PLAIN, 20));
		bookNumberTextField.setBounds(64, 57, 345, 24);
		SettingPanel.add(bookNumberTextField);
		bookNumberTextField.setColumns(10);
		
		openComboBox = new JComboBox();
		openComboBox.setModel(new DefaultComboBoxModel(QueryOpenName()));
		openComboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){ 					
					printNumberSpinner.requestFocus();
				}
			}
		});

		openComboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		openComboBox.setBounds(64, 92, 112, 24);
		SettingPanel.add(openComboBox);
		
		languageComboBox = new JComboBox();
		languageComboBox.setModel(new DefaultComboBoxModel(QueryLanguageName()));
		languageComboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					bjsComboBox.requestFocus();
			}
		});
		

		languageComboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		languageComboBox.setBounds(64, 126, 112, 24);
		SettingPanel.add(languageComboBox);
		
		printNumberLabel = new JLabel("\u5370  \u6570");
		printNumberLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		printNumberLabel.setBounds(180, 95, 72, 18);
		SettingPanel.add(printNumberLabel);
		
		bjsLabel = new JLabel("\u7F16\u8F91\u5BA4");
		bjsLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		bjsLabel.setBounds(180, 129, 72, 18);
		SettingPanel.add(bjsLabel);
		
		bjsComboBox = new JComboBox();
		bjsComboBox.setModel(new DefaultComboBoxModel(QueryBjsName()));
		bjsComboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					saveButton.requestFocus();
			}
		});

		bjsComboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		bjsComboBox.setBounds(247, 126, 162, 24);
		SettingPanel.add(bjsComboBox);
		
		printNumberSpinner = new JSpinner();
		printNumberSpinner.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					priceTextField.requestFocus();
			}
		});
		printNumberSpinner.setFont(new Font("宋体", Font.PLAIN, 20));
		printNumberSpinner.setBounds(245, 89, 164, 30);
		SettingPanel.add(printNumberSpinner);
		
		authorTextField = new JTextField();
		authorTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookNumberTextField.requestFocus();
			}
		});
		authorTextField.setFont(new Font("宋体", Font.PLAIN, 20));
		authorTextField.setColumns(10);
		authorTextField.setBounds(583, 20, 282, 26);
		SettingPanel.add(authorTextField);
		
		authorLabel = new JLabel("\u4F5C    \u8005");
		authorLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		authorLabel.setBounds(489, 27, 96, 18);
		SettingPanel.add(authorLabel);
		
		categoryLabel  = new JLabel("\u56FE\u4E66\u5206\u7C7B");
		categoryLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		categoryLabel.setBounds(489, 58, 92, 18);
		SettingPanel.add(categoryLabel);
		
		priceLabel = new JLabel("\u5B9A    \u4EF7");
		priceLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		priceLabel.setBounds(489, 94, 96, 18);
		SettingPanel.add(priceLabel);
		
		timeLabel = new JLabel("\u51FA\u7248\u65F6\u95F4");
		timeLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		timeLabel.setBounds(654, 95, 92, 18);
		SettingPanel.add(timeLabel);
		
		// 获得时间日期模型
		SpinnerDateModel model = new SpinnerDateModel();
		// 获得JSPinner对象
		timeSpinner = new JSpinner(model);
		timeSpinner.setValue(new Date());
		// 设置时间格式
		JSpinner.DateEditor editor = new JSpinner.DateEditor(timeSpinner, "yyyy-MM-dd");
		timeSpinner.setEditor(editor);
		timeSpinner.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					languageComboBox.requestFocus();
			}
		});
		timeSpinner.setFont(new Font("宋体", Font.PLAIN, 20));
		timeSpinner.setBounds(735, 90, 130, 30);
		SettingPanel.add(timeSpinner);
		
		editorLabel = new JLabel("\u8D23\u4EFB\u7F16\u8F91");
		editorLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		editorLabel.setBounds(489, 132, 96, 18);
		SettingPanel.add(editorLabel);
		
		editorComboBox = new JComboBox();
		editorComboBox.setModel(new DefaultComboBoxModel(QueryEditorName()));
		editorComboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		editorComboBox.setBounds(583, 128, 282, 24);
		SettingPanel.add(editorComboBox);
		
		closeButton = new JButton("\u5173\u95ED");
		closeButton.setFont(new Font("宋体", Font.PLAIN, 20));
		closeButton.setBounds(750, 183, 115, 39);
		SettingPanel.add(closeButton);
		
		saveButton = new JButton("\u4FDD\u5B58");
		saveButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					save();
			}
		});
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		saveButton.setFont(new Font("宋体", Font.PLAIN, 20));
		saveButton.setBounds(598, 183, 112, 39);
		SettingPanel.add(saveButton);
		
		deleteButton = new JButton("\u5220\u9664");
		deleteButton.setEnabled(false);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selection = JOptionPane.showConfirmDialog(null,"确认删除"+BooksDao.getInstance().QueryBookNameByID(CURRENTID)+"？","删除",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if( selection == JOptionPane.OK_OPTION ){
					String bookName = bookNameTextField.getText();
					BooksDao.getInstance().Delete(bookName);
					RefreshListPanel();//刷新table
					booksTable.clearSelection();
					CURRENTID = 0; //当前用户id清零
					searchTextField.setText("");
					bookNumRadioButton.setSelected(true);
					bookNameTextField.setText("");
					authorTextField.setText("");
					bookNumberTextField.setText("");
					priceTextField.setText("");
					openComboBox.setSelectedIndex(0);
					languageComboBox.setSelectedIndex(0);
					bjsComboBox.setSelectedIndex(0);
					editorComboBox.setSelectedIndex(0);
					categoryComboBox.setSelectedIndex(0);
					printNumberSpinner.setValue(0);
					try {
						timeSpinner.setValue(new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					deleteButton.setEnabled(false);
					titledBorder2.setTitle("新增");
					SettingPanel.repaint();
				}
			}
		});
		deleteButton.setFont(new Font("宋体", Font.PLAIN, 20));
		deleteButton.setBounds(470, 183, 96, 39);
		SettingPanel.add(deleteButton);
		
		resetButton = new JButton("\u6E05\u9664");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				titledBorder2.setTitle("新增");
				SettingPanel.repaint();
				searchTextField.setText("");
				bookNumRadioButton.setSelected(true);
				bookNameTextField.setText("");
				authorTextField.setText("");
				bookNumberTextField.setText("");
				priceTextField.setText("");
				openComboBox.setSelectedIndex(0);
				languageComboBox.setSelectedIndex(0);
				bjsComboBox.setSelectedIndex(0);
				editorComboBox.setSelectedIndex(0);
				categoryComboBox.setSelectedIndex(0);
				printNumberSpinner.setValue(0);
				try {
					timeSpinner.setValue(new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				deleteButton.setEnabled(false);
				CURRENTID = 0;
				RefreshListPanel(); //刷新编辑室列表
			}
		});
		resetButton.setFont(new Font("宋体", Font.PLAIN, 20));
		resetButton.setBounds(329, 183, 107, 39);
		SettingPanel.add(resetButton);
		
		priceTextField = new JTextField();
		priceTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeSpinner.requestFocus();
			}
		});
		priceTextField.setFont(new Font("宋体", Font.PLAIN, 20));
		priceTextField.setBounds(583, 92, 57, 24);
		SettingPanel.add(priceTextField);
		priceTextField.setColumns(10);
		
		categoryComboBox = new JComboBox();
		categoryComboBox.setModel(new DefaultComboBoxModel(QueryBookTypeName()));
		categoryComboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					openComboBox.requestFocus();
			}
		});
		categoryComboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		categoryComboBox.setBounds(583, 57, 282, 24);
		SettingPanel.add(categoryComboBox);
	}
	
	/**
	 * 设置table的列宽随着列的内容而变化
	 * @param myTable
	 */
	public void FitTableColumns(JTable myTable) {               
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();
        Enumeration columns = myTable.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) myTable.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(myTable,column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();
            for (int row = 0; row < rowCount; row++){
                int preferedWidth = (int) myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,myTable.getValueAt(row, col), false, false,row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column);
            column.setWidth(width + myTable.getIntercellSpacing().width);
        }
    }
	//生成用户名表格数据
    /**
     * @return
     */
    public Object[][] queryData(){
    	//获取排序方式
    	String sortType = null; 
    	Enumeration<AbstractButton> radioBtns= selectedGroup.getElements();  
    	while (radioBtns.hasMoreElements()) {  
    		AbstractButton btn = radioBtns.nextElement();  
    	    if(btn.isSelected()){  
    	    	sortType=btn.getText();  
    	        break;  
    	    }  
    	}
    	String searchText = searchTextField.getText();
        List<Book> list=BookDao.getInstance().QueryAll(searchText,QuerySortType(sortType));
        titledBorder1.setTitle("共"+list.size()+"本");
        data=new Object[list.size()][head.length];
        for(int i=0;i<list.size();i++){
            for(int j=0;j<head.length;j++){
                data[i][0]=list.get(i).getBookName();
                data[i][1]=list.get(i).getAuthorName();
                data[i][2]=list.get(i).getBookNumber();
                data[i][3]=list.get(i).getBookType();
                data[i][4]=list.get(i).getSize();
                data[i][5]=list.get(i).getPrintNumber();
                data[i][6]=list.get(i).getPrice();
                data[i][7]=list.get(i).getPublishTime();
                data[i][8]=list.get(i).getArticleType();
                data[i][9]=list.get(i).getBjsName();
                data[i][10]=list.get(i).getBjsEditorName();
            }
        }
        return data;
    }
    
    public String QuerySortType(String sortType) {
    	String type = null;
    	if(sortType.equals("书号"))
    		type="bookNumber";
    	else if(sortType.equals("书名"))
    		type="bookName";
    	else if(sortType.equals("编辑室"))
    		type="bjsName";
    	else if(sortType.equals("出版时间"))
    		type="publishTime";
    	else if(sortType.equals("图书分类"))
    		type="bookType";
    	return type;
    }
	public void RefreshListPanel() {
		BooksInfoPanel.removeAll();
		BooksInfoPanel.add(BooksInfoScrollPane);
    	tableModel=new DefaultTableModel(queryData(),head){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        booksTable.setModel(tableModel);
        FitTableColumns(booksTable);
        BooksInfoScrollPane.setViewportView(booksTable);
    	BooksInfoPanel.repaint();
		BooksInfoPanel.validate();
		BooksInfoPanel.repaint();
	}
	
	//设置表格行选中
	public void SetSelected(){
		String queryName = BooksDao.getInstance().QueryBookNameByID(CURRENTID);
		for(int i =0;i<booksTable.getRowCount();i++)
			if(queryName.equals(booksTable.getValueAt(i,0).toString())){
				booksTable.setRowSelectionInterval(i,i);//设置新添加用户行为选中样式；
				Rectangle rect = booksTable.getCellRect(i, 0, true);  	  
				booksTable.scrollRectToVisible(rect);
				break;
			}
	}
	
	public void save() {
		String bookName = bookNameTextField.getText();
    	String authorName = authorTextField.getText();
		String bookNumber = bookNumberTextField.getText();
		String bookTypeID = categoryComboBox.getSelectedItem().toString();
		String sizeID = openComboBox.getSelectedItem().toString();
		String printNumber = printNumberSpinner.getValue().toString();
		String price = priceTextField.getText();
		String publishTime = new SimpleDateFormat("yyyy-MM-dd").format(timeSpinner.getValue());
		System.out.println(publishTime);
		String articleType = languageComboBox.getSelectedItem().toString();
		String bjsNameID = bjsComboBox.getSelectedItem().toString();
		String bjsEditorName = editorComboBox.getSelectedItem().toString();
		if(bookName.equals("")) {//书名为空
			JOptionPane.showMessageDialog(null,"书名为空","提示",JOptionPane.INFORMATION_MESSAGE);
			bookNameTextField.requestFocus();
		}else if(authorName.equals("")) {//作者为空
			JOptionPane.showMessageDialog(null,"作者名为空","提示",JOptionPane.INFORMATION_MESSAGE);
			authorTextField.requestFocus();
		}else if(bookNumber.equals("")) {//书号为空
			JOptionPane.showMessageDialog(null,"书号为空","提示",JOptionPane.INFORMATION_MESSAGE);
			bookNumberTextField.requestFocus();
		}else if(price.equals("")) {//定价为空
			JOptionPane.showMessageDialog(null,"定价为空","提示",JOptionPane.INFORMATION_MESSAGE);
			priceTextField.requestFocus();
		}
		else {  //不为空
			if(CURRENTID !=0 ){  //当前选中了一项
				if(!BooksDao.getInstance().QueryBookNumberByID(CURRENTID).equals(bookNumber) && BooksDao.getInstance().bookNumberIsExist(bookNumber))
					JOptionPane.showMessageDialog(null,"书号已经存在","提示",JOptionPane.INFORMATION_MESSAGE);
				else				
					BooksDao.getInstance().Update(
							bookName,
							authorName,
							bookNumber,
							CodeDao.getInstance().QueryIDByName(bookTypeID),
							SizeDao.getInstance().QueryIDByName(sizeID),
							Integer.valueOf(printNumber),
							Float.valueOf(price),
							publishTime,
							ArticleTypeDao.getInstance().QueryIDByName(articleType),
							IdentifierDao.getInstance().QueryIDByName(bjsNameID),
							EditorsDao.getInstance().QueryIDByName(bjsEditorName),
							CURRENTID);
			}
			else if(CURRENTID ==0 ){  //添加编辑室
				if(BooksDao.getInstance().bookNumberIsExist(bookNumber))
					JOptionPane.showMessageDialog(null,"书号已经存在","提示",JOptionPane.INFORMATION_MESSAGE);
				else {
					BooksDao.getInstance().Add(
							bookName,
							authorName,
							bookNumber,
							CodeDao.getInstance().QueryIDByName(bookTypeID),
							SizeDao.getInstance().QueryIDByName(sizeID),
							Integer.valueOf(printNumber),
							Float.valueOf(price),
							publishTime,
							ArticleTypeDao.getInstance().QueryIDByName(articleType),
							IdentifierDao.getInstance().QueryIDByName(bjsNameID),
							EditorsDao.getInstance().QueryIDByName(bjsEditorName));
					CURRENTID =BooksDao.getInstance().QueryIDByBookName(bookName);//更新当前id指针为新建用户的id
					deleteButton.setEnabled(true);
					resetButton.setEnabled(true);
					titledBorder2.setTitle("修改");
					SettingPanel.repaint();
				}
			}
			RefreshListPanel();//刷新列表
			SetSelected();//设置选中
		}
	}
	
	public Object[] QueryBjsName() {
		List<String> result = new ArrayList<String>();
		List<Bjs_Identifier> list=IdentifierDao.getInstance().QueryAllUsed();
		for(Bjs_Identifier item:list) {
			String name = item.getBjsName();
			if(result == null)
				result.add(name);
			else if(!result.contains(name))
				result.add(name);
		}
		return result.toArray();
	}
	
	public Object[] QueryBookTypeName() {
		List<String> result = new ArrayList<String>();
		List<Bjs_Code> list=CodeDao.getInstance().QueryAll();
		for(Bjs_Code item:list) {
			String name = item.getBookType();
			if(result == null)
				result.add(name);
			else if(!result.contains(name))
				result.add(name);
		}
		return result.toArray();
	}
	public Object[] QueryOpenName() {
		List<String> result = new ArrayList<String>();
		List<Size> list=SizeDao.getInstance().QueryAll();
		for(Size item:list) {
			String name = item.getSizeName();
			if(result == null)
				result.add(name);
			else if(!result.contains(name))
				result.add(name);
		}
		return result.toArray();
	}
	public Object[] QueryLanguageName() {
		List<String> result = new ArrayList<String>();
		List<ArticleType> list=ArticleTypeDao.getInstance().QueryAll();
		for(ArticleType item:list) {
			String name = item.getArticleTypeName();
			if(result == null)
				result.add(name);
			else if(!result.contains(name))
				result.add(name);
		}
		return result.toArray();
	}
	public Object[] QueryEditorName() {
		List<String> result = new ArrayList<String>();
		List<Editors> list=EditorsDao.getInstance().QueryAll();
		for(Editors item:list) {
			String name = item.getEditorName();
			if(result == null)
				result.add(name);
			else if(!result.contains(name))
				result.add(name);
		}
		return result.toArray();
	}

}
