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

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

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
import javax.swing.JTextArea;

public class Manage_RK extends JFrame {

	private JPanel contentPane,BooksInfoPanel,SettingPanel;
	private JScrollPane BooksInfoScrollPane;
	private TitledBorder titledBorder1,titledBorder2;
	private JLabel bookNameLabel;
	private JLabel bookNumberLabel;
	private JLabel printNumberLabel,priceLabel;
	private JTextField bookNameTextField;
	private JTextField bookNumberTextField;
	private JButton closeButton,searchButton,saveButton;
	private JTable booksTable;
	private static int CURRENTID = 0;
	private DefaultTableModel tableModel;
	private String head[]=null;  //表格列名
	private Object [][]data=null;   //表格行表
	private JTextField priceTextField;
	private JTextField printNumberTextField;

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
					Manage_RK frame = new Manage_RK();
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
	public Manage_RK() {
		setTitle("\u56FE\u4E66\u5165\u5E93");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
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
		BooksInfoPanel.setBounds(14, 0, 854, 228);
		contentPane.add(BooksInfoPanel);
		BooksInfoPanel.setLayout(null);
		
		BooksInfoScrollPane = new JScrollPane();
		BooksInfoScrollPane.setBounds(14, 25, 826, 196);
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
				searchButton.setEnabled(true);
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
		titledBorder2 = new TitledBorder(UIManager.getBorder("TitledBorder.border"), "入库", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 102, 153));
		titledBorder2.setTitleFont(new Font("宋体", Font.PLAIN, 20));
		SettingPanel.setBorder(titledBorder2);
		SettingPanel.setBounds(14, 235, 854, 123);
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
		
		bookNameTextField = new JTextField();
		bookNameTextField.setEditable(false);
		bookNameTextField.setFont(new Font("宋体", Font.PLAIN, 20));
		bookNameTextField.setBounds(64, 23, 282, 24);
		SettingPanel.add(bookNameTextField);
		bookNameTextField.setColumns(10);
		
		bookNumberTextField = new JTextField();
		bookNumberTextField.setEditable(false);
		bookNumberTextField.setFont(new Font("宋体", Font.PLAIN, 20));
		bookNumberTextField.setBounds(64, 55, 282, 24);
		SettingPanel.add(bookNumberTextField);
		bookNumberTextField.setColumns(10);
		
		printNumberLabel = new JLabel("\u5370\u6570");
		printNumberLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		printNumberLabel.setBounds(180, 92, 72, 18);
		SettingPanel.add(printNumberLabel);
		
		priceLabel = new JLabel("\u5B9A\u4EF7");
		priceLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		priceLabel.setBounds(14, 86, 72, 18);
		SettingPanel.add(priceLabel);
		
		saveButton = new JButton("\u4FDD\u5B58");
		saveButton.setFont(new Font("宋体", Font.PLAIN, 20));
		saveButton.setBounds(741, 82, 99, 27);
		SettingPanel.add(saveButton);
		
		priceTextField = new JTextField();
		priceTextField.setEditable(false);
		priceTextField.setBounds(63, 86, 103, 24);
		SettingPanel.add(priceTextField);
		priceTextField.setColumns(10);
		
		printNumberTextField = new JTextField();
		printNumberTextField.setEditable(false);
		printNumberTextField.setBounds(227, 86, 119, 24);
		SettingPanel.add(printNumberTextField);
		printNumberTextField.setColumns(10);
		
		JLabel RKNumberLabel = new JLabel("\u5165\u5E93\u518C\u6570");
		RKNumberLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		RKNumberLabel.setBounds(363, 26, 85, 18);
		SettingPanel.add(RKNumberLabel);
		
		JSpinner RKNumberSpinner = new JSpinner();
		RKNumberSpinner.setFont(new Font("宋体", Font.PLAIN, 20));
		RKNumberSpinner.setBounds(447, 23, 57, 24);
		SettingPanel.add(RKNumberSpinner);
		
		JLabel RKTimeLabel = new JLabel("\u5165\u5E93\u65F6\u95F4");
		RKTimeLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		RKTimeLabel.setBounds(518, 24, 85, 18);
		SettingPanel.add(RKTimeLabel);
		
		JSpinner RKTimeSpinner = new JSpinner();
		RKTimeSpinner.setModel(new SpinnerDateModel(new Date(1555689600000L), null, null, Calendar.DAY_OF_YEAR));
		RKTimeSpinner.setFont(new Font("宋体", Font.PLAIN, 20));
		RKTimeSpinner.setBounds(617, 23, 110, 24);
		SettingPanel.add(RKTimeSpinner);
		
		JLabel noteLabel = new JLabel("\u5907\u6CE8");
		noteLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		noteLabel.setBounds(360, 55, 72, 18);
		SettingPanel.add(noteLabel);
		
		JScrollPane noteScrollPane = new JScrollPane();
		noteScrollPane.setBounds(404, 55, 323, 55);
		SettingPanel.add(noteScrollPane);
		
		JTextArea noteTextArea = new JTextArea();
		noteScrollPane.setViewportView(noteTextArea);
		
		JPanel RKPanel = new JPanel();
		RKPanel.setBorder(new TitledBorder(null, "\u56FE\u4E66\u5165\u5E93\u660E\u7EC6\u67E5\u8BE2\uFF08\u5171XX\u6761\uFF09", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 102, 153)));
		RKPanel.setBounds(14, 366, 854, 174);
		contentPane.add(RKPanel);
		RKPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 23, 713, 138);
		RKPanel.add(scrollPane);
		
		closeButton = new JButton("\u5173\u95ED");
		closeButton.setBounds(741, 134, 99, 27);
		RKPanel.add(closeButton);
		closeButton.setFont(new Font("宋体", Font.PLAIN, 20));
		
		searchButton = new JButton("\u67E5\u8BE2");
		searchButton.setBounds(741, 91, 99, 27);
		RKPanel.add(searchButton);
		searchButton.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JSpinner RKTimeSearchSpinner = new JSpinner();
		RKTimeSearchSpinner.setBounds(741, 23, 99, 24);
		RKPanel.add(RKTimeSearchSpinner);
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
