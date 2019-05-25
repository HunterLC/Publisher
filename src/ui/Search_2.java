package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import bll.FuzzyQueryDao;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Enumeration;
import java.awt.event.ActionEvent;

public class Search_2 extends JFrame {

	private JPanel contentPane;
	private JTable bookTable;
	private JTextField searchTextField;
	public static String[] columnNames1 = { "书号", "书名", "作者", "图书分类", "开本", "印数", "定价", "出版时间", "文种", "编辑室", "责任编辑" };
	private FuzzyQueryDao f=new FuzzyQueryDao();
	public Object[][] obj1;
	private JScrollPane scrollPane;
	private JButton serachButton;
	private JButton exitButton;
	private DefaultTableCellRenderer tcr1;
	public void updateTable() {//更新图书列表
		
		bookTable.setRowHeight(40);
		tcr1 = new DefaultTableCellRenderer();
		tcr1.setHorizontalAlignment(SwingConstants.CENTER);// 设置为居中
		bookTable.setDefaultRenderer(Object.class, tcr1);
		bookTable.setFont(new Font("宋体", Font.BOLD, 16));
		try {
			obj1 = f.getBookList(searchTextField.getText());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bookTable.setModel(new DefaultTableModel(obj1, columnNames1));
		bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		FitTableColumns(bookTable);//根据列表的内容动态的调整对应的列宽
		
		bookTable.repaint();
		bookTable.updateUI();
		// bookTable.setRowSelectionInterval(0, 0);
	}

	/**
	 * 设置table的列宽随着列的内容而变化
	 * 
	 * @param myTable
	 */
	public void FitTableColumns(JTable myTable) {
		JTableHeader header = myTable.getTableHeader();
		int rowCount = myTable.getRowCount();
		Enumeration columns = myTable.getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = (TableColumn) columns.nextElement();
			int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
			int width = (int) myTable.getTableHeader().getDefaultRenderer()
					.getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col)
					.getPreferredSize().getWidth();
			for (int row = 0; row < rowCount; row++) {
				int preferedWidth = (int) myTable.getCellRenderer(row, col)
						.getTableCellRendererComponent(myTable, myTable.getValueAt(row, col), false, false, row, col)
						.getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth)+2;
			}
			header.setResizingColumn(column);
			column.setWidth(width + myTable.getIntercellSpacing().width);
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						// org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
						BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
						org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
						UIManager.put("RootPane.setupButtonVisible", false);
						// 设置此开关量为false即表示关闭之，BeautyEye LNF中默认是true
						BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
					} catch (Exception e) {
						// TODO exception
					}
					Search_2 frame = new Search_2();
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
	public Search_2() {
		setResizable(false);
		setTitle("\u56FE\u4E66\u4FE1\u606F\u67E5\u8BE2\uFF08\u591A\u5B57\u6BB5\u6A21\u7CCA\u67E5\u8BE2\uFF09");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 950, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel bookPanel = new JPanel();
		bookPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		bookPanel.setBounds(14, 10, 916, 462);
		contentPane.add(bookPanel);
		bookPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 892, 439);
		bookPanel.add(scrollPane);
		
		bookTable = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		bookTable.setRowHeight(40);
		bookTable.setSelectionBackground(Color.LIGHT_GRAY);
		tcr1 = new DefaultTableCellRenderer();// 设置table内容居中
		tcr1.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
		bookTable.setDefaultRenderer(Object.class, tcr1);
		bookTable.setFont(new Font("宋体", Font.BOLD, 16));
		try {
			obj1 = f.getBookList("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bookTable.setModel(new DefaultTableModel(obj1, columnNames1));
		bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		FitTableColumns(bookTable);
		scrollPane.setViewportView(bookTable);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		searchPanel.setBounds(14, 485, 916, 68);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);
		
		JLabel label = new JLabel("\u68C0\u7D22");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(14, 11, 45, 44);
		searchPanel.add(label);
		
		searchTextField = new JTextField();
		searchTextField.setFont(new Font("宋体", Font.PLAIN, 20));
		searchTextField.setBounds(63, 10, 614, 45);
		searchPanel.add(searchTextField);
		searchTextField.setColumns(10);
		
		serachButton = new JButton("\u641C\u7D22");
		serachButton.setFont(new Font("宋体", Font.PLAIN, 17));
		serachButton.setBounds(709, 11, 80, 43);
		searchPanel.add(serachButton);
		
		exitButton = new JButton("\u5173\u95ED");
		exitButton.setFont(new Font("宋体", Font.PLAIN, 17));
		exitButton.setBounds(822, 11, 80, 43);
		searchPanel.add(exitButton);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		serachButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchText=searchTextField.getText();
				//System.out.println(searchText);
				updateTable();
				
			}
		});
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
