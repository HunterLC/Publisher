package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import bll.SearchBLL;
import model.BookInfor;

public class ComposeSearch extends JFrame {// 组合查询（部分查询）

	private JTable table;
	private JLabel lblNewLabel;
	private DefaultTableModel model;

	public ComposeSearch() throws Exception {
		setResizable(false);
		setTitle("组合查询");
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 816, 625);
		getContentPane().setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel.setBounds(14, 13, 778, 439);
		getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(5, 38, 768, 395);
		panel.add(scrollPane);

		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.LEFT);
		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		table.setFont(new Font("宋体", Font.PLAIN, 18));
		table.setBackground(Color.WHITE);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setFont(new Font("宋体", Font.PLAIN, 20));
		table.setRowHeight(30);
		table.setDefaultRenderer(Object.class, r);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		ActionMap am = table.getActionMap();
		am.getParent().remove("selectNextRowCell");
		table.setActionMap(am);
		scrollPane.setViewportView(table);
		Object[] columnNames = { "书号", "书名", "作者", "编辑室", "单价", "责任编辑", "印数", "开本", "出版时间", "图书分类", "出版社", "文种", "页数" };
		Object[][] rowData = new Object[0][13];
		model = new DefaultTableModel(rowData, columnNames);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(210);
		table.getColumnModel().getColumn(1).setPreferredWidth(190);
		table.getColumnModel().getColumn(2).setPreferredWidth(170);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(70);
		table.getColumnModel().getColumn(7).setPreferredWidth(70);
		table.getColumnModel().getColumn(8).setPreferredWidth(120);
		table.getColumnModel().getColumn(9).setPreferredWidth(150);
		table.getColumnModel().getColumn(10).setPreferredWidth(170);
		table.getColumnModel().getColumn(11).setPreferredWidth(100);
		table.getColumnModel().getColumn(12).setPreferredWidth(70);

		lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(205, 92, 92));
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(5, 0, 152, 38);
		panel.add(lblNewLabel);
		lblNewLabel.setText("共0本");

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel_1.setBounds(14, 460, 778, 110);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("\u7F16\u8F91\u5BA4");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(14, 60, 69, 25);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_1 = new JLabel("\u6587  \u79CD");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(14, 12, 69, 25);
		panel_1.add(lblNewLabel_1);

		ArrayList<BookInfor> list = new SearchBLL().searchBook5();
		String[] s = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			s[i] = list.get(i).getBookLanguage();
		}
		Set set = new HashSet();
		for (int j = 0; j < s.length; j++) {
			set.add(s[j]);
		}
		s = (String[]) set.toArray(new String[0]);
		JComboBox comboBox = new JComboBox(s);
		comboBox.setBounds(84, 12, 139, 24);
		panel_1.add(comboBox);
		comboBox.setFont(new Font("宋体", Font.PLAIN, 18));
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setSelectedItem(null);
		comboBox.addItem(null);

		String[] s2 = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			s2[i] = list.get(i).getBjsName();
		}
		Set set2 = new HashSet();
		for (int j = 0; j < s2.length; j++) {
			set2.add(s2[j]);
		}
		s2 = (String[]) set2.toArray(new String[0]);
		JComboBox comboBox_1 = new JComboBox(s2);
		comboBox_1.setBounds(84, 60, 139, 24);
		panel_1.add(comboBox_1);
		comboBox_1.setFont(new Font("宋体", Font.PLAIN, 18));
		comboBox_1.setBackground(new Color(255, 255, 255));
		comboBox_1.setSelectedItem(null);
		comboBox_1.addItem(null);

		JLabel label = new JLabel("\u56FE\u4E66\u5206\u7C7B");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(237, 12, 85, 25);
		panel_1.add(label);

		JLabel label_1 = new JLabel("\u51FA\u7248\u65F6\u95F4");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(237, 60, 85, 25);
		panel_1.add(label_1);

		String[] s3 = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			s3[i] = list.get(i).getCategorize();
		}
		Set set3 = new HashSet();
		for (int j = 0; j < s3.length; j++) {
			set3.add(s3[j]);
		}
		s3 = (String[]) set3.toArray(new String[0]);
		JComboBox comboBox_2 = new JComboBox(s3);
		comboBox_2.setBounds(321, 12, 275, 24);
		panel_1.add(comboBox_2);
		comboBox_2.setFont(new Font("宋体", Font.PLAIN, 18));
		comboBox_2.setBackground(new Color(255, 255, 255));
		comboBox_2.setSelectedItem(null);
		comboBox_2.addItem(null);

		JSpinner spinner = new JSpinner();
		SpinnerDateModel model = new SpinnerDateModel();
		model.setCalendarField(Calendar.DAY_OF_MONTH);
		spinner.setModel(model);
		spinner.setFont(new Font("宋体", Font.PLAIN, 18));
		spinner.setEditor(new JSpinner.DateEditor(spinner, "yyyy-MM-dd"));
		spinner.setBounds(321, 56, 121, 29);
		panel_1.add(spinner);

		JLabel label_2 = new JLabel("\u81F3");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		label_2.setBounds(444, 60, 26, 25);
		panel_1.add(label_2);

		JSpinner spinner_1 = new JSpinner();
		SpinnerDateModel model2 = new SpinnerDateModel();
		model2.setCalendarField(Calendar.DAY_OF_MONTH);
		spinner_1.setModel(model2);
		spinner_1.setFont(new Font("宋体", Font.PLAIN, 18));
		spinner_1.setEditor(new JSpinner.DateEditor(spinner_1, "yyyy-MM-dd"));
		spinner_1.setBounds(473, 55, 123, 30);
		panel_1.add(spinner_1);

		JButton btnNewButton = new JButton("\u68C0\u7D22");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookLanguage = null;
				String bjsName = null;
				String categorize = null;
				if (comboBox.getSelectedItem() != null) {
					bookLanguage = comboBox.getSelectedItem().toString();
				}
				if (comboBox_1.getSelectedItem() != null) {
					bjsName = comboBox_1.getSelectedItem().toString();
				}
				if (comboBox_2.getSelectedItem() != null) {
					categorize = comboBox_2.getSelectedItem().toString();
				}
				BookInfor bookInfor = new BookInfor();
				bookInfor.setBookLanguage(bookLanguage);
				bookInfor.setBjsName(bjsName);
				bookInfor.setCategorize(categorize);
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
				Date date = (Date) (spinner.getValue());
				String startTime = f.format(date);
				Date date2 = (Date) (spinner_1.getValue());
				String endTime = f.format(date2);
				try {
					ArrayList<BookInfor> list = new SearchBLL().searchBook2(bookInfor, startTime, endTime);
					getBook(list);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton.setBounds(610, 39, 70, 30);
		panel_1.add(btnNewButton);

		JButton button = new JButton("\u5173\u95ED");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button.setBackground(new Color(192, 192, 192));
		button.setFont(new Font("宋体", Font.PLAIN, 18));
		button.setBounds(694, 39, 70, 30);
		panel_1.add(button);
	}

	public void getBook(ArrayList<BookInfor> list) throws Exception {
		Object[] columnNames = { "书号", "书名", "作者", "编辑室", "单价", "责任编辑", "印数", "开本", "出版时间", "图书分类", "出版社", "文种", "页数" };
		Object[][] rowData = new Object[list.size()][13];
		if (list.size() == 0) {
		} else {
			for (int i = 0; i < list.size(); i++) {
				rowData[i][0] = list.get(i).getBookNO();
				rowData[i][1] = list.get(i).getBookName();
				rowData[i][2] = list.get(i).getAuthor();
				rowData[i][3] = list.get(i).getBjsName();
				rowData[i][4] = list.get(i).getPrice();
				rowData[i][5] = list.get(i).getBjName();
				rowData[i][6] = list.get(i).getNum();
				rowData[i][7] = list.get(i).getFormat();
				rowData[i][8] = list.get(i).getPublishtime();
				rowData[i][9] = list.get(i).getCategorize();
				rowData[i][10] = list.get(i).getPublisher();
				rowData[i][11] = list.get(i).getBookLanguage();
				rowData[i][12] = list.get(i).getPages();
			}
		}
		model.setDataVector(rowData, columnNames);
		table.getColumnModel().getColumn(0).setPreferredWidth(210);
		table.getColumnModel().getColumn(1).setPreferredWidth(190);
		table.getColumnModel().getColumn(2).setPreferredWidth(170);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(70);
		table.getColumnModel().getColumn(7).setPreferredWidth(70);
		table.getColumnModel().getColumn(8).setPreferredWidth(120);
		table.getColumnModel().getColumn(9).setPreferredWidth(150);
		table.getColumnModel().getColumn(10).setPreferredWidth(170);
		table.getColumnModel().getColumn(11).setPreferredWidth(100);
		table.getColumnModel().getColumn(12).setPreferredWidth(70);
		lblNewLabel.setText("共" + list.size() + "本");
	}
}
