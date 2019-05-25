package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import bll.Search1_BLL;
import util.MyTableModel;
import model.Book;
import model.SearchBooks;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class Search_1 extends JFrame {
	Search1_BLL s1_bll=new Search1_BLL();
	private JPanel contentPane;
	private JComboBox cb_articletype;
	private JComboBox cb_bjs;
	private JComboBox cb_booktype;
	private JButton btn_search;
	private JButton btn_quit;
	private JScrollPane scrollPane_book;
	private JTable book_tb;
	private MyTableModel model; 
	private Vector data;
	private JSpinner sp_startdate;
	private JSpinner sp_enddate;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	List<SearchBooks> booklist=new ArrayList<SearchBooks>();
	private JLabel label_5;
	private JTextField tf_minprice;
	private JTextField tf_maxprice;
	private SpinnerDateModel start_model;
	private SpinnerDateModel end_model;
	private JTextField tf_author;
	private JLabel label_8;
	private JTextField tf_bookname;
	private JRadioButton rb_publishtime;
	private JButton btn_repaint;
	private JPanel query_panel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search_1 frame = new Search_1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Search_1() throws SQLException {
		setTitle("图书信息（组合查询.retrieve技术）");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 911, 648);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		query_panel = new JPanel();
		query_panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u67E5\u8BE2\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(205, 92, 92)));
		query_panel.setBounds(14, 409, 865, 179);
		contentPane.add(query_panel);
		query_panel.setLayout(null);
		Init();
		InitSpinner();
	}
	
	public void Init() throws SQLException
	{	
		cb_articletype = new JComboBox();
		cb_articletype.setModel(new DefaultComboBoxModel(new String[] {"", "CN", "EN"}));
		cb_articletype.setBounds(108, 80, 117, 31);
		query_panel.add(cb_articletype);
		
		JLabel label = new JLabel("文  种");
		label.setBounds(24, 81, 59, 28);
		query_panel.add(label);
		
		JLabel label_1 = new JLabel("\u7F16\u8F91\u5BA4");
		label_1.setBounds(639, 37, 45, 15);
		query_panel.add(label_1);
		
		cb_bjs = new JComboBox();
		cb_bjs.setModel(new DefaultComboBoxModel(new String[] {"", "外文编辑室", "社科编辑室", "公路编辑室", "医学编辑室", "哲学编辑室", "计算机编辑室"}));
		cb_bjs.setBounds(712, 30, 117, 34);
		query_panel.add(cb_bjs);
		
		JLabel label_2 = new JLabel("\u56FE\u4E66\u5206\u7C7B");
		label_2.setBounds(438, 34, 72, 18);
		query_panel.add(label_2);
		
		cb_booktype = new JComboBox();
		cb_booktype.setModel(new DefaultComboBoxModel(new String[] {"", "医药学术", "志怪小说", "外国文学", "青春文学", "玄幻武侠"}));
		cb_booktype.setBounds(511, 30, 117, 34);
		query_panel.add(cb_booktype);
		
		btn_search = new JButton("S \u68C0\u7D22");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> querylist=new ArrayList<String>();//查询条件集合
				String author=tf_author.getText();
				boolean author_f=isNull(author);
				String bookname=tf_bookname.getText();
				boolean bookname_f=isNull(bookname);
				String booktype=cb_booktype.getSelectedItem().toString();
				boolean booktype_f=isNull(booktype);
				String bjs=cb_bjs.getSelectedItem().toString();
				boolean bjs_f=isNull(bjs);
				String startdate=sdf.format(sp_startdate.getValue());
				String enddate=sdf.format(sp_enddate.getValue());
				String minprice=tf_minprice.getText();
				boolean minprice_f=isNull(minprice);
				String maxprice=tf_maxprice.getText();
				boolean maxprice_f=isNull(maxprice);
				String articletype=cb_articletype.getSelectedItem().toString();
				boolean art_f=isNull(articletype);
				
				//不为空的条件则加入查询
				if(author_f)
					querylist.add(author);
				if(bookname_f)
					querylist.add(bookname);
				if(booktype_f)
					querylist.add(booktype);
				if(bjs_f)
					querylist.add(bjs);
				if(art_f)
					querylist.add(articletype);
				
				if(rb_publishtime.isSelected())
				{	
					try {
						if(minprice_f==false&&maxprice_f==false) 
						{
							booklist.clear();
							booklist=s1_bll.QueryWithDate(querylist,startdate,enddate);
							RepaintTable();
						}
						else
						{
							booklist.clear();
							booklist=s1_bll.QueryDateAndPrice(querylist,startdate,enddate,minprice,maxprice);
							RepaintTable();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					try {
						if(querylist.isEmpty()&&minprice_f==false&&maxprice_f==false)
						{		
							JOptionPane.showMessageDialog(null, "请输入查询信息！", "错误", JOptionPane.ERROR_MESSAGE);
						}
						else if(querylist.isEmpty()==false&&minprice_f==false&&maxprice_f==false)
						{
							booklist.clear();
							booklist=s1_bll.QueryWithNothing(querylist);
							RepaintTable();
						}
						else 
						{
							booklist.clear();
							booklist=s1_bll.QueryWithPrice(querylist,minprice,maxprice);
							RepaintTable();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		btn_search.setBounds(510, 121, 97, 45);
		query_panel.add(btn_search);
		
		JLabel label_4 = new JLabel("\u81F3");
		label_4.setBounds(252, 129, 28, 15);
		query_panel.add(label_4);
		
		btn_quit = new JButton("Q \u5173\u95ED");
		btn_quit.setBounds(732, 121, 97, 45);
		query_panel.add(btn_quit);
		
		label_5 = new JLabel("最低价");
		label_5.setBounds(235, 78, 45, 31);
		query_panel.add(label_5);
		
		tf_minprice = new JTextField();
		tf_minprice.setBounds(294, 78, 118, 31);
		query_panel.add(tf_minprice);
		tf_minprice.setColumns(10);
		
		JLabel label_6 = new JLabel("最高价");
		label_6.setBounds(443, 82, 54, 22);
		query_panel.add(label_6);
		
		tf_maxprice = new JTextField();
		tf_maxprice.setBounds(511, 78, 117, 31);
		query_panel.add(tf_maxprice);
		tf_maxprice.setColumns(10);
		
		JLabel label_7 = new JLabel("作  者");
		label_7.setBounds(24, 28, 59, 33);
		query_panel.add(label_7);
		
		tf_author = new JTextField();
		tf_author.setBounds(108, 30, 117, 35);
		query_panel.add(tf_author);
		tf_author.setColumns(10);
		
		label_8 = new JLabel("书  名");
		label_8.setBounds(239, 34, 54, 20);
		query_panel.add(label_8);
		
		tf_bookname = new JTextField();
		tf_bookname.setBounds(295, 29, 117, 31);
		query_panel.add(tf_bookname);
		tf_bookname.setColumns(10);
		
		btn_repaint = new JButton("R 复位");
		btn_repaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RepaintUI();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_repaint.setBounds(621, 121, 97, 45);
		query_panel.add(btn_repaint);
		
		booklist=s1_bll.getAllBook();
		InitTable();
		scrollPane_book = new JScrollPane();
		scrollPane_book.setViewportView(book_tb);
		scrollPane_book.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);//需要时显现水平滚动条
		scrollPane_book.setBounds(10, 10, 869, 396);
		contentPane.add(scrollPane_book);
		query_panel.validate();
		query_panel.repaint();
	}
	
	public void InitTable() throws SQLException
	{
		book_tb=new JTable();
		Vector<String> colname = new Vector<String>();//列名
		colname.add("书号");
		colname.add("书名");
		colname.add("作者");
		colname.add("出版时间");
		colname.add("图书分类");
		colname.add("编辑室");
		colname.add("开本");
		colname.add("印数");
		colname.add("价格");
		colname.add("文种");
		colname.add("编辑");
		model = new MyTableModel(data, colname);
		book_tb.setFont(new Font("宋体", Font.BOLD, 16));
		book_tb.setModel(model);
		book_tb.setRowHeight(40);
		for (int i = 0; i < booklist.size(); i++) {
			SearchBooks book = booklist.get(i);
			Vector<String> bk = new Vector<String>();
			bk.add(book.getBookNumber());
			bk.add(book.getBookName());
			bk.add(book.getAuthorName());
			bk.add(sdf.format(book.getPublishTime()));
			bk.add(book.getBookType());
			bk.add(book.getBjsName());
			bk.add(book.getSize());
			bk.add(String.valueOf(book.getPrintNumber()));
			bk.add(String.valueOf(book.getPrice()));
			bk.add(book.getArticleType());
			bk.add(book.getEditorName());
			data = ((MyTableModel) book_tb.getModel()).getDataVector();
			data.add(bk);
		}
		//设置相对列宽
		/*book_tb.getColumnModel().getColumn(0).setPreferredWidth(50);
		book_tb.getColumnModel().getColumn(1).setPreferredWidth(100);
		book_tb.getColumnModel().getColumn(2).setPreferredWidth(50);
		book_tb.getColumnModel().getColumn(3).setPreferredWidth(100);
		book_tb.getColumnModel().getColumn(4).setPreferredWidth(50);
		book_tb.getColumnModel().getColumn(5).setPreferredWidth(100);
		book_tb.getColumnModel().getColumn(6).setPreferredWidth(50);
		book_tb.getColumnModel().getColumn(7).setPreferredWidth(30);
		book_tb.getColumnModel().getColumn(8).setPreferredWidth(30);
		book_tb.getColumnModel().getColumn(9).setPreferredWidth(30);
		book_tb.getColumnModel().getColumn(10).setPreferredWidth(80);*/
		FitTableColumns(book_tb);
	}
	public void InitSpinner()
	{
		rb_publishtime = new JRadioButton("出版时间");
		rb_publishtime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rb_publishtime.isSelected())
				{
					sp_startdate.setEnabled(true);
					sp_enddate.setEnabled(true);
				}
				else
				{
					sp_startdate.setEnabled(false);
					sp_enddate.setEnabled(false);
				}
			}
		});
		rb_publishtime.setBounds(4, 125, 97, 23);
		query_panel.add(rb_publishtime);
		
		start_model=new SpinnerDateModel();
		sp_startdate = new JSpinner(start_model);
		sp_startdate.setEnabled(false);
		JSpinner.DateEditor editor1=new JSpinner.DateEditor(sp_startdate,"yyyy-MM-dd");
		sp_startdate.setEditor(editor1);
		sp_startdate.setBounds(108, 124, 117, 31);
		query_panel.add(sp_startdate);
		
		end_model=new SpinnerDateModel();
		sp_enddate = new JSpinner(end_model);
		sp_enddate.setEnabled(false);
		JSpinner.DateEditor editor2=new JSpinner.DateEditor(sp_enddate,"yyyy-MM-dd");
		sp_enddate.setEditor(editor2);
		sp_enddate.setBounds(294, 120, 118, 31);
		query_panel.add(sp_enddate);
	}
	/**
	 * 重绘表格
	 * @throws SQLException
	 */
	public void RepaintTable() throws SQLException
	{
		model.setRowCount(0);
		contentPane.remove(scrollPane_book);
		scrollPane_book = new JScrollPane();
		InitTable();
		scrollPane_book.setViewportView(book_tb);
		scrollPane_book.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);//需要时显现水平滚动条
		scrollPane_book.setBounds(10, 10, 880, 380);
		contentPane.add(scrollPane_book);
		contentPane.validate();
		contentPane.repaint();
	}
	/**
	 * 判断编辑框是否为空
	 * @param str
	 * @return
	 */
	public boolean isNull(String str)
	{
		if(str.length()<=0)
			return false;
		else
			return true;
	}
	/**
	 * 根据内容自动设置列宽
	 * @param myTable
	 */
	public void FitTableColumns(JTable myTable) {
		book_tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//关闭表格自动调整的状态
		JTableHeader header = myTable.getTableHeader();
		int rowCount = myTable.getRowCount();
		Enumeration columns = myTable.getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = (TableColumn) columns.nextElement();
			int col = header.getColumnModel().getColumnIndex(
				column.getIdentifier());
			int width = (int) myTable.getTableHeader().getDefaultRenderer()
					.getTableCellRendererComponent(myTable,
							column.getIdentifier(), false, false, -1, col)
					.getPreferredSize().getWidth();
			for (int row = 0; row < rowCount; row++) {
				int preferedWidth = (int) myTable.getCellRenderer(row, col)
						.getTableCellRendererComponent(myTable,
								myTable.getValueAt(row, col), false, false, row, col)
						.getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth)+2;
			}
			header.setResizingColumn(column);
			column.setWidth(width + myTable.getIntercellSpacing().width);
		}
	}
	/**
	 * 复位
	 * @throws SQLException
	 */
	/*public void RepaintUI() throws SQLException{
		Date date=Calendar.getInstance().getTime();
		model.setRowCount(0);
		//contentPane.remove(scrollPane_book);
		contentPane.removeAll();
		Init();
		tf_author.setText("");
		tf_bookname.setText("");
		cb_booktype.setSelectedIndex(0);
		cb_bjs.setSelectedIndex(0);
		rb_publishtime.setSelected(false);
		sp_startdate.setValue(date);
		sp_startdate.setEnabled(false);
		sp_enddate.setValue(date);
		sp_enddate.setEnabled(false);
		tf_minprice.setText("");
		tf_maxprice.setText("");
		cb_articletype.setSelectedIndex(0);
		contentPane.invalidate();
		contentPane.repaint();
	}*/
	public void RepaintUI() throws SQLException{
		Date date=Calendar.getInstance().getTime();
		model.setRowCount(0);
		contentPane.remove(scrollPane_book);
		query_panel.remove(tf_author);
		query_panel.remove(tf_bookname);
		query_panel.remove(tf_minprice);
		query_panel.remove(tf_maxprice);
		query_panel.remove(cb_booktype);
		query_panel.remove(cb_bjs);
		query_panel.remove(cb_articletype);
		Init();
		
		rb_publishtime.setSelected(false);
		sp_startdate.setValue(date);
		sp_startdate.setEnabled(false);
		sp_enddate.setValue(date);
		sp_enddate.setEnabled(false);
		cb_booktype.setSelectedIndex(0);
		cb_bjs.setSelectedIndex(0);
		cb_articletype.setSelectedIndex(0);
		contentPane.validate();
		contentPane.repaint();
	}

}
