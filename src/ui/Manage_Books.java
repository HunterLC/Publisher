package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import bll.Reference1Dao;
import model.Reference1;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class Manage_Books extends JFrame {

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
	private JTextField categoryTextField;
	private JComboBox openComboBox,languageComboBox,bjsComboBox,priceComboBox,editorComboBox;
	private JSpinner printNumberSpinner,timeSpinner;
	private JButton closeButton,deleteButton,resetButton,saveButton;
	private JTable booksTable;
	private static int CURRENTID = 0;
	private DefaultTableModel tableModel;
	private String head[]=null;  //表格列名
	private Object [][]data=null;   //表格行表
	private JTextField searchTextField;
	private JRadioButton bookNumRadioButton,bookNameRadioButton,bjsRadioButton,publishRadioButton,bookTypeRadioButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manage_Books frame = new Manage_Books();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Manage_Books() {
		setTitle("\u56FE\u4E66\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		
		BooksInfoPanel = new JPanel();
		titledBorder1 = new TitledBorder(null, "共XXX本", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 102, 153));
		titledBorder1.setTitleFont(new Font("宋体", Font.PLAIN, 20));
		BooksInfoPanel.setBorder(titledBorder1);
		BooksInfoPanel.setBounds(14, 51, 754, 278);
		contentPane.add(BooksInfoPanel);
		BooksInfoPanel.setLayout(null);
		
		BooksInfoScrollPane = new JScrollPane();
		BooksInfoScrollPane.setBounds(14, 24, 726, 241);
		BooksInfoPanel.add(BooksInfoScrollPane);
		
		//初始化表格
		booksTable = new JTable();
		booksTable.setRowHeight(25);
		booksTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		head=new String[] {"书名","作者","书号","图书分类","开本","印数","定价","出版时间","文种","编辑室","责任编辑"};
		tableModel=new DefaultTableModel(queryData(),head){
		public boolean isCellEditable(int row, int column)
		{
			return false;
			}
		};
		booksTable.setFont(new Font("宋体", Font.BOLD, 14));
		booksTable.addMouseListener(new MouseAdapter() {   //用户名表格点击事件
			@Override
			public void mouseClicked(MouseEvent arg0) {
				titledBorder2.setTitle("修改");
				SettingPanel.repaint();
				deleteButton.setEnabled(true);
				resetButton.setEnabled(true);
			}
		});
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
		SettingPanel.setBounds(14, 328, 754, 212);
		contentPane.add(SettingPanel);
		SettingPanel.setLayout(null);
		
		bookNameLabel = new JLabel("\u4E66\u540D");
		bookNameLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		bookNameLabel.setBounds(14, 24, 72, 18);
		SettingPanel.add(bookNameLabel);
		
		bookNumberLabel = new JLabel("\u4E66\u53F7");
		bookNumberLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		bookNumberLabel.setBounds(14, 55, 72, 18);
		SettingPanel.add(bookNumberLabel);
		
		openLabel = new JLabel("\u5F00\u672C*");
		openLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		openLabel.setBounds(14, 90, 72, 18);
		SettingPanel.add(openLabel);
		
		languageLabel = new JLabel("\u6587\u79CD");
		languageLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		languageLabel.setBounds(14, 128, 72, 18);
		SettingPanel.add(languageLabel);
		
		bookNameTextField = new JTextField();
		bookNameTextField.setFont(new Font("宋体", Font.PLAIN, 20));
		bookNameTextField.setBounds(64, 23, 282, 24);
		SettingPanel.add(bookNameTextField);
		bookNameTextField.setColumns(10);
		
		bookNumberTextField = new JTextField();
		bookNumberTextField.setFont(new Font("宋体", Font.PLAIN, 20));
		bookNumberTextField.setBounds(64, 55, 282, 24);
		SettingPanel.add(bookNumberTextField);
		bookNumberTextField.setColumns(10);
		
		openComboBox = new JComboBox();
		openComboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		openComboBox.setBounds(64, 86, 112, 24);
		SettingPanel.add(openComboBox);
		
		languageComboBox = new JComboBox();
		languageComboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		languageComboBox.setBounds(64, 127, 112, 24);
		SettingPanel.add(languageComboBox);
		
		printNumberLabel = new JLabel("\u5370\u6570");
		printNumberLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		printNumberLabel.setBounds(180, 92, 72, 18);
		SettingPanel.add(printNumberLabel);
		
		bjsLabel = new JLabel("\u7F16\u8F91\u5BA4");
		bjsLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		bjsLabel.setBounds(180, 128, 72, 18);
		SettingPanel.add(bjsLabel);
		
		bjsComboBox = new JComboBox();
		bjsComboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		bjsComboBox.setBounds(247, 127, 99, 24);
		SettingPanel.add(bjsComboBox);
		
		printNumberSpinner = new JSpinner();
		printNumberSpinner.setFont(new Font("宋体", Font.PLAIN, 20));
		printNumberSpinner.setBounds(245, 89, 101, 24);
		SettingPanel.add(printNumberSpinner);
		
		authorTextField = new JTextField();
		authorTextField.setFont(new Font("宋体", Font.PLAIN, 20));
		authorTextField.setColumns(10);
		authorTextField.setBounds(458, 21, 282, 24);
		SettingPanel.add(authorTextField);
		
		authorLabel = new JLabel("\u4F5C\u8005");
		authorLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		authorLabel.setBounds(388, 24, 72, 18);
		SettingPanel.add(authorLabel);
		
		categoryLabel  = new JLabel("\u56FE\u4E66\u5206\u7C7B");
		categoryLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		categoryLabel.setBounds(364, 55, 92, 18);
		SettingPanel.add(categoryLabel);
		
		categoryTextField = new JTextField();
		categoryTextField.setFont(new Font("宋体", Font.PLAIN, 20));
		categoryTextField.setColumns(10);
		categoryTextField.setBounds(458, 52, 282, 24);
		SettingPanel.add(categoryTextField);
		
		priceLabel = new JLabel("\u5B9A\u4EF7");
		priceLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		priceLabel.setBounds(388, 91, 72, 18);
		SettingPanel.add(priceLabel);
		
		priceComboBox = new JComboBox();
		priceComboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		priceComboBox.setBounds(458, 87, 112, 24);
		SettingPanel.add(priceComboBox);
		
		timeLabel = new JLabel("\u51FA\u7248\u65F6\u95F4");
		timeLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		timeLabel.setBounds(568, 90, 92, 18);
		SettingPanel.add(timeLabel);
		
		timeSpinner = new JSpinner();
		timeSpinner.setModel(new SpinnerDateModel(new Date(1555689600000L), null, null, Calendar.DAY_OF_YEAR));
		timeSpinner.setFont(new Font("宋体", Font.PLAIN, 20));
		timeSpinner.setBounds(648, 87, 92, 24);
		SettingPanel.add(timeSpinner);
		
		editorLabel = new JLabel("\u8D23\u4EFB\u7F16\u8F91");
		editorLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		editorLabel.setBounds(364, 129, 96, 18);
		SettingPanel.add(editorLabel);
		
		editorComboBox = new JComboBox();
		editorComboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		editorComboBox.setBounds(458, 125, 112, 24);
		SettingPanel.add(editorComboBox);
		
		closeButton = new JButton("\u5173\u95ED");
		closeButton.setFont(new Font("宋体", Font.PLAIN, 20));
		closeButton.setBounds(641, 172, 99, 27);
		SettingPanel.add(closeButton);
		
		saveButton = new JButton("\u4FDD\u5B58");
		saveButton.setFont(new Font("宋体", Font.PLAIN, 20));
		saveButton.setBounds(529, 172, 99, 27);
		SettingPanel.add(saveButton);
		
		deleteButton = new JButton("\u5220\u9664");
		deleteButton.setFont(new Font("宋体", Font.PLAIN, 20));
		deleteButton.setBounds(423, 172, 92, 27);
		SettingPanel.add(deleteButton);
		
		resetButton = new JButton("\u6E05\u9664");
		resetButton.setFont(new Font("宋体", Font.PLAIN, 20));
		resetButton.setBounds(310, 172, 99, 27);
		SettingPanel.add(resetButton);
		
		searchPanel = new JPanel();
		titledBorder3 = new TitledBorder(null, "\u68C0\u7D22", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 102, 153));
		titledBorder3.setTitleFont(new Font("宋体", Font.PLAIN, 16));
		searchPanel.setBorder(titledBorder3);
		searchPanel.setBounds(14, 0, 323, 49);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(47, 17, 258, 24);
		searchPanel.add(searchTextField);
		searchTextField.setColumns(10);
		
		SortPanel = new JPanel();
		titledBorder4 = new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u6392\u5E8F\u65B9\u5F0F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 102, 153));
		titledBorder4.setTitleFont(new Font("宋体", Font.PLAIN, 16));
		SortPanel.setBorder(titledBorder4);
		SortPanel.setBounds(351, 0, 417, 49);
		contentPane.add(SortPanel);
		SortPanel.setLayout(null);
		
		bookNumRadioButton = new JRadioButton("\u4E66\u53F7");
		bookNumRadioButton.setSelected(true);
		bookNumRadioButton.setBounds(10, 22, 59, 18);
		SortPanel.add(bookNumRadioButton);
		
		bookNameRadioButton = new JRadioButton("\u4E66\u540D");
		bookNameRadioButton.setBounds(75, 22, 59, 18);
		SortPanel.add(bookNameRadioButton);
		
		bjsRadioButton = new JRadioButton("\u7F16\u8F91\u5BA4");
		bjsRadioButton.setBounds(140, 22, 73, 18);
		SortPanel.add(bjsRadioButton);
		
		publishRadioButton = new JRadioButton("\u51FA\u7248\u65F6\u95F4");
		publishRadioButton.setBounds(219, 22, 99, 18);
		SortPanel.add(publishRadioButton);
		
		bookTypeRadioButton = new JRadioButton("\u56FE\u4E66\u5206\u7C7B");
		bookTypeRadioButton.setBounds(314, 22, 93, 18);
		SortPanel.add(bookTypeRadioButton);
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
        List<Reference1> list=Reference1Dao.getInstance().QueryAllByMethod1();
        data=new Object[list.size()][head.length];
        for(int i=0;i<list.size();i++){
            for(int j=0;j<head.length;j++){
                data[i][0]=list.get(i).getBjsName();
                data[i][1]=list.get(i).getBookType();
                data[i][2]=list.get(i).getShum();
            }
        }
        return data;
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
    	titledBorder1.setTitle("共"+Reference1Dao.getInstance().QueryCount()+"本");
    	BooksInfoPanel.repaint();
		BooksInfoPanel.validate();
		BooksInfoPanel.repaint();
	}
	
	//设置表格行选中
	public void SetSelected(){
		String queryName = Reference1Dao.getInstance().QueryShumByID(CURRENTID);
		for(int i =0;i<booksTable.getRowCount();i++)
			if(queryName.equals(booksTable.getValueAt(i,2).toString())){
				booksTable.setRowSelectionInterval(i,i);//设置新添加用户行为选中样式；
				Rectangle rect = booksTable.getCellRect(i, 0, true);  	  
				booksTable.scrollRectToVisible(rect);
				break;
			}
	}
}
