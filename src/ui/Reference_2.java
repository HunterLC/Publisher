package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import bll.CodeDao;
import bll.IdentifierDao;
import bll.NumDao;
import bll.Reference2Dao;
import bll.SimpleDao;
import bll.UsersDao;
import model.Bjs_Code;
import model.Bjs_Identifier;
import model.Bjs_Num;
import model.Reference2;
import model.Users;
import util.MyProperties;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class Reference_2 extends JFrame {

	private JPanel contentPane,listPanel,settingPanel;
	private JTextField nameTextField;
	private JScrollPane scrollPane;
	private JTable listTable;
	private DefaultTableModel tableModel;
	private TitledBorder titledBorder,titledBorder1;
	private String head[]=null;  //表格列名
	private Object [][]data=null;   //表格行表
	private JLabel nameLabel,label,isUse;
	private JButton saveButton,deleteButton,resetButton,closeButton;
	private static int CURRENTID = 0;
	private JComboBox nameComboBox,bookTypeComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reference_2 frame = new Reference_2();
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
	public Reference_2() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 877, 554);
		setTitle("【基本信息：引用类型二】");
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listPanel = new JPanel();
		titledBorder = new TitledBorder(UIManager.getBorder("TitledBorder.border"), "共"+Reference2Dao.getInstance().QueryCount()+"项", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED);
		titledBorder.setTitleFont(new Font("宋体", Font.PLAIN, 20));
		listPanel.setBorder(titledBorder);
		listPanel.setBounds(14, 0, 831, 280);
		contentPane.add(listPanel);
		listPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 24, 803, 243);
		listPanel.add(scrollPane);
		
		//初始化表格
		listTable = new JTable();
		listTable.setRowHeight(25);
		listTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		head=new String[] {"编辑室名称","图书类型","图书名称"};
		tableModel=new DefaultTableModel(queryData(),head){
		public boolean isCellEditable(int row, int column)
		{
			return false;
			}
		};
		listTable.setFont(new Font("宋体", Font.BOLD, 14));
		listTable.addMouseListener(new MouseAdapter() {   //用户名表格点击事件
			@Override
			public void mouseClicked(MouseEvent arg0) {
				titledBorder1.setTitle("修改");
				settingPanel.repaint();
				//填充编辑室编辑框
				String bjsName = listTable.getValueAt(listTable.getSelectedRow(), 0).toString();
				String bookType = listTable.getValueAt(listTable.getSelectedRow(), 1).toString();
				String shum = listTable.getValueAt(listTable.getSelectedRow(), 2).toString();
				nameTextField.setText(shum);
				CURRENTID = Reference2Dao.getInstance().QueryIDByName(shum);//获得当前选中编辑室的id
				nameComboBox.setSelectedItem(bjsName);
				bookTypeComboBox.setSelectedItem(bookType);
				deleteButton.setEnabled(true);
				resetButton.setEnabled(true);
			}
		});
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		listTable.setDefaultRenderer(Object.class, r);
		listTable.setModel(tableModel);
		scrollPane.setViewportView(listTable);
				
		settingPanel = new JPanel();
		titledBorder1 = new TitledBorder(null, "新增", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY);
		titledBorder1.setTitleFont(new Font("宋体", Font.PLAIN, 20));
		settingPanel.setBorder(titledBorder1);
		settingPanel.setBounds(14, 297, 831, 197);
		contentPane.add(settingPanel);
		settingPanel.setLayout(null);
		
		nameLabel = new JLabel("编辑室名称");
		nameLabel.setBounds(14, 39, 106, 18);
		settingPanel.add(nameLabel);
		nameLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		nameTextField = new JTextField();
		nameTextField.setBounds(117, 121, 292, 24);
		settingPanel.add(nameTextField);
		nameTextField.setFont(new Font("宋体", Font.PLAIN, 20));
		nameTextField.setColumns(10);
		
		saveButton = new JButton("保存");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String shum= nameTextField.getText();
				String bjsName = nameComboBox.getSelectedItem().toString();
				String bookType = bookTypeComboBox.getSelectedItem().toString();
				if(shum.equals("")) {//编辑室名为空
					JOptionPane.showMessageDialog(null,"图书名为空","提示",JOptionPane.INFORMATION_MESSAGE);
				}
				else {  //编辑室名不为空
					if(CURRENTID !=0 ){  //当前选中了一项
						if(!Reference2Dao.getInstance().QueryShumByID(CURRENTID).equals(shum) && Reference2Dao.getInstance().shumIsExist(shum))
							JOptionPane.showMessageDialog(null,"该图书名已经存在","提示",JOptionPane.INFORMATION_MESSAGE);
						else//修改之后的排序号、编辑室不存在					
							Reference2Dao.getInstance().Update(IdentifierDao.getInstance().QueryIDByName(bjsName),CodeDao.getInstance().QueryIDByName(bookType),shum,CURRENTID);
					}
					else if(CURRENTID ==0 ){  //添加编辑室
						if(Reference2Dao.getInstance().shumIsExist(shum))
							JOptionPane.showMessageDialog(null,"该图书名已经存在","提示",JOptionPane.INFORMATION_MESSAGE);
						else {
							Reference2Dao.getInstance().Add(IdentifierDao.getInstance().QueryIDByName(bjsName),CodeDao.getInstance().QueryIDByName(bookType),shum);
							CURRENTID =Reference2Dao.getInstance().QueryIDByName(shum);//更新当前id指针为新建用户的id
							deleteButton.setEnabled(true);
							resetButton.setEnabled(true);
							titledBorder1.setTitle("修改");
							settingPanel.repaint();
						}
					}
					RefreshListPanel();//刷新列表
					SetSelected();//设置选中
				}
			}
		});
		saveButton.setFont(new Font("宋体", Font.PLAIN, 20));
		saveButton.setBounds(609, 54, 113, 27);
		settingPanel.add(saveButton);
		
		deleteButton = new JButton("删除");
		deleteButton.setEnabled(false);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selection = JOptionPane.showConfirmDialog(null,"确认删除"+Reference2Dao.getInstance().QueryShumByID(CURRENTID)+"？","删除",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if( selection == JOptionPane.OK_OPTION ){
					String shum = nameTextField.getText();
					Reference2Dao.getInstance().Delete(shum);
					RefreshListPanel();//刷新table
					listTable.clearSelection();
					CURRENTID = 0; //当前用户id清零
					nameTextField.setText("");
					nameTextField.requestFocus();
					nameComboBox.setSelectedIndex(0);
					bookTypeComboBox.setSelectedIndex(0);
					deleteButton.setEnabled(false);
					titledBorder1.setTitle("新增");
					settingPanel.repaint();
				}
			}
		});
		deleteButton.setFont(new Font("宋体", Font.PLAIN, 20));
		deleteButton.setBounds(436, 54, 113, 27);
		settingPanel.add(deleteButton);
		
		resetButton = new JButton("复位");
		resetButton.setEnabled(false);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				titledBorder1.setTitle("新增");
				settingPanel.repaint();
				nameTextField.setText("");
				nameTextField.requestFocus();
				deleteButton.setEnabled(false);
				resetButton.setEnabled(false);
				CURRENTID = 0;
				nameComboBox.setSelectedIndex(0);
				bookTypeComboBox.setSelectedIndex(0);
				RefreshListPanel(); //刷新编辑室列表
			}
		});
		resetButton.setFont(new Font("宋体", Font.PLAIN, 20));
		resetButton.setBounds(436, 94, 113, 27);
		settingPanel.add(resetButton);
		
		closeButton = new JButton("关闭");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		closeButton.setFont(new Font("宋体", Font.PLAIN, 20));
		closeButton.setBounds(609, 94, 113, 27);
		settingPanel.add(closeButton);
		
		label = new JLabel("\u56FE\u4E66\u7C7B\u578B");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(14, 83, 99, 18);
		settingPanel.add(label);
		
		isUse = new JLabel("\u56FE\u4E66\u540D\u79F0");
		isUse.setFont(new Font("宋体", Font.PLAIN, 20));
		isUse.setBounds(14, 124, 106, 18);
		settingPanel.add(isUse);
		
		nameComboBox = new JComboBox();
		nameComboBox.setModel(new DefaultComboBoxModel(QueryBjsName()));
		nameComboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		nameComboBox.setBounds(117, 38, 292, 24);
		settingPanel.add(nameComboBox);
		
		bookTypeComboBox = new JComboBox();
		bookTypeComboBox.setModel(new DefaultComboBoxModel(QueryBookTypeName()));
		bookTypeComboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		bookTypeComboBox.setBounds(117, 82, 292, 24);
		settingPanel.add(bookTypeComboBox);
	}
	
	//生成用户名表格数据
    /**
     * @return
     */
    public Object[][] queryData(){
        List<Reference2> list=Reference2Dao.getInstance().QueryAllByMethod1();
        data=new Object[list.size()][head.length];
        for(int i=0;i<list.size();i++){
            for(int j=0;j<head.length;j++){
                data[i][0]=IdentifierDao.getInstance().QueryNameByID(list.get(i).getBjsID());
                data[i][1]=CodeDao.getInstance().QueryNameByID(list.get(i).getBookTypeID());
                data[i][2]=list.get(i).getShum();
            }
        }
        return data;
    }
    
	public void RefreshListPanel() {
		listPanel.removeAll();
		listPanel.add(scrollPane);
    	tableModel=new DefaultTableModel(queryData(),head){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
    	listTable.setModel(tableModel);
    	scrollPane.setViewportView(listTable);
    	titledBorder.setTitle("共"+Reference2Dao.getInstance().QueryCount()+"项");
		listPanel.repaint();
		listPanel.validate();
		listPanel.repaint();
	}
	
	//设置表格行选中
	public void SetSelected(){
		String queryName = Reference2Dao.getInstance().QueryShumByID(CURRENTID);
		for(int i =0;i<listTable.getRowCount();i++)
			if(queryName.equals(listTable.getValueAt(i,2).toString())){
				listTable.setRowSelectionInterval(i,i);//设置新添加用户行为选中样式；
				break;
			}
	}
	
	public Object[] QueryBjsName() {
		List<String> result = new ArrayList<String>();
		List<Bjs_Identifier> list=IdentifierDao.getInstance().QueryAll();
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
}
