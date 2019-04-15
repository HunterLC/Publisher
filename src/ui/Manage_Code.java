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
import bll.NumDao;
import bll.SimpleDao;
import bll.UsersDao;
import model.Bjs_Code;
import model.Bjs_Num;
import model.Users;
import util.MyProperties;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Manage_Code extends JFrame {

	private JPanel contentPane,listPanel,settingPanel;
	private JTextField nameTextField;
	private JScrollPane scrollPane;
	private JTable listTable;
	private DefaultTableModel tableModel;
	private TitledBorder titledBorder,titledBorder1;
	private String head[]=null;  //表格列名
	private Object [][]data=null;   //表格行表
	private JLabel nameLabel;
	private JButton saveButton,deleteButton,resetButton,closeButton;
	private static int CURRENTID = 0;
	private JLabel label;
	private JTextField numTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manage_Code frame = new Manage_Code();
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
	public Manage_Code() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 831, 491);
		setTitle("【基本信息：图书类型带有编码】");
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listPanel = new JPanel();
		titledBorder = new TitledBorder(UIManager.getBorder("TitledBorder.border"), "共"+CodeDao.getInstance().QueryCount()+"项", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED);
		titledBorder.setTitleFont(new Font("宋体", Font.PLAIN, 20));
		listPanel.setBorder(titledBorder);
		listPanel.setBounds(14, 0, 407, 423);
		contentPane.add(listPanel);
		listPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 24, 368, 386);
		listPanel.add(scrollPane);
		
		//初始化表格
		listTable = new JTable();
		listTable.setRowHeight(25);
		listTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		head=new String[] {"编码","图书类型名"};
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
				//String num = Integer.valueOf(listTable.getValueAt(listTable.getSelectedRow(), 0).toString());
				String code = listTable.getValueAt(listTable.getSelectedRow(), 0).toString();
				String bookType = listTable.getValueAt(listTable.getSelectedRow(), 1).toString();
				nameTextField.setText(bookType);
				numTextField.setText(code);
				CURRENTID = CodeDao.getInstance().QueryIDByName(bookType);//获得当前选中编辑室的id
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
		settingPanel.setBounds(423, 0, 376, 423);
		contentPane.add(settingPanel);
		settingPanel.setLayout(null);
		
		nameLabel = new JLabel("图书类型");
		nameLabel.setBounds(14, 80, 106, 18);
		settingPanel.add(nameLabel);
		nameLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		nameTextField = new JTextField();
		nameTextField.setBounds(118, 77, 244, 24);
		settingPanel.add(nameTextField);
		nameTextField.setFont(new Font("宋体", Font.PLAIN, 20));
		nameTextField.setColumns(10);
		
		saveButton = new JButton("保存");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookType = nameTextField.getText();
				String code = numTextField.getText();
				if(code.equals("")) //编码号为空
					JOptionPane.showMessageDialog(null,"编码为空","提示",JOptionPane.INFORMATION_MESSAGE);
				else if(bookType.equals("")) {//图书类型名为空
					JOptionPane.showMessageDialog(null,"图书类型名为空","提示",JOptionPane.INFORMATION_MESSAGE);
				}
				else {  //图书类型名不为空
					if(CURRENTID !=0 ){  //当前选中了一项
						//修改之后的排序号或编辑室已经存在
						if(!CodeDao.getInstance().QueryCodeByID(CURRENTID).equals(code)  && CodeDao.getInstance().codeIsExist(code))
							JOptionPane.showMessageDialog(null,"该编码已经存在","提示",JOptionPane.INFORMATION_MESSAGE);
						else if(!CodeDao.getInstance().QueryNameByID(CURRENTID).equals(bookType) && CodeDao.getInstance().nameIsExist(bookType))
							JOptionPane.showMessageDialog(null,"该图书类型名已经存在","提示",JOptionPane.INFORMATION_MESSAGE);
						else//修改之后的排序号、编辑室不存在					
							CodeDao.getInstance().Update(code,bookType,CURRENTID);
					}
					else if(CURRENTID ==0 ){  //添加编辑室
						if(CodeDao.getInstance().codeIsExist(code))
							JOptionPane.showMessageDialog(null,"该编码号已经存在","提示",JOptionPane.INFORMATION_MESSAGE);
						else if(CodeDao.getInstance().nameIsExist(bookType))
							JOptionPane.showMessageDialog(null,"该编辑室名已经存在","提示",JOptionPane.INFORMATION_MESSAGE);
						else {
							CodeDao.getInstance().Add(code,bookType);
							CURRENTID =CodeDao.getInstance().QueryIDByName(bookType);//更新当前id指针为新建用户的id
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
		saveButton.setBounds(68, 218, 113, 27);
		settingPanel.add(saveButton);
		
		deleteButton = new JButton("删除");
		deleteButton.setEnabled(false);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selection = JOptionPane.showConfirmDialog(null,"确认删除"+CodeDao.getInstance().QueryNameByID(CURRENTID)+"？","删除",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if( selection == JOptionPane.OK_OPTION ){
					String bookType = nameTextField.getText();
					CodeDao.getInstance().Delete(bookType);
					RefreshListPanel();//刷新table
					listTable.clearSelection();
					CURRENTID = 0; //当前用户id清零
					nameTextField.setText("");
					numTextField.setText("");
					nameTextField.requestFocus();
					deleteButton.setEnabled(false);
					titledBorder1.setTitle("新增");
					settingPanel.repaint();
				}
			}
		});
		deleteButton.setFont(new Font("宋体", Font.PLAIN, 20));
		deleteButton.setBounds(68, 268, 113, 27);
		settingPanel.add(deleteButton);
		
		resetButton = new JButton("复位");
		resetButton.setEnabled(false);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				titledBorder1.setTitle("新增");
				settingPanel.repaint();
				nameTextField.setText("");
				numTextField.setText("");
				nameTextField.requestFocus();
				deleteButton.setEnabled(false);
				resetButton.setEnabled(false);
				CURRENTID = 0;
				RefreshListPanel(); //刷新编辑室列表
			}
		});
		resetButton.setFont(new Font("宋体", Font.PLAIN, 20));
		resetButton.setBounds(68, 314, 113, 27);
		settingPanel.add(resetButton);
		
		closeButton = new JButton("关闭");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		closeButton.setFont(new Font("宋体", Font.PLAIN, 20));
		closeButton.setBounds(68, 361, 113, 27);
		settingPanel.add(closeButton);
		
		label = new JLabel("编码");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(14, 39, 72, 18);
		settingPanel.add(label);
		
		numTextField = new JTextField();
		numTextField.setFont(new Font("宋体", Font.PLAIN, 20));
		numTextField.setBounds(117, 36, 245, 24);
		settingPanel.add(numTextField);
		numTextField.setColumns(10);
	}
	
	//生成用户名表格数据
    /**
     * @return
     */
    public Object[][] queryData(){
        List<Bjs_Code> list=CodeDao.getInstance().QueryAll();
        data=new Object[list.size()][head.length];
        for(int i=0;i<list.size();i++){
            for(int j=0;j<head.length;j++){
                data[i][0]=list.get(i).getCode();
                data[i][1]=list.get(i).getBookType();
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
    	titledBorder.setTitle("共"+CodeDao.getInstance().QueryCount()+"项");
		listPanel.repaint();
		listPanel.validate();
		listPanel.repaint();
	}
	
	//设置表格行选中
	public void SetSelected(){
		String queryName = CodeDao.getInstance().QueryNameByID(CURRENTID);
		for(int i =0;i<listTable.getRowCount();i++)
			if(queryName.equals(listTable.getValueAt(i,1).toString())){
				listTable.setRowSelectionInterval(i,i);//设置新添加用户行为选中样式；
				break;
			}
	}
	

}
