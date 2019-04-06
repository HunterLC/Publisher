package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bll.UsersDao;
import bll.UsersRightsDao;
import util.MyUtil;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTree;

public class MenuFrame extends JFrame {

	private JPanel contentPane,menuTreePanel,picturePanel;
	private JLabel loginDateLabel,dateLabel,loginAgainLabel,closeLabel;
	private JScrollPane menuTreeScrollPane;
	private JButton btnNewButton;
	private DefaultMutableTreeNode root;
	private JTree menuTree;


	/**
	 * Create the frame.
	 */
	public MenuFrame(int userID) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 476);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		loginDateLabel = new JLabel("登陆日期：");
		loginDateLabel.setForeground(Color.RED);
		loginDateLabel.setBounds(0, 13, 88, 18);
		contentPane.add(loginDateLabel);
		
		dateLabel = new JLabel(LoginFrame.loginDay);
		dateLabel.setForeground(Color.RED);
		dateLabel.setBounds(68, 13, 72, 18);
		contentPane.add(dateLabel);
		
		loginAgainLabel = new JLabel("[重新登录]");
		loginAgainLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				new LoginFrame();
			}
		});
		loginAgainLabel.setForeground(Color.BLUE);
		loginAgainLabel.setBounds(419, 410, 90, 18);
		contentPane.add(loginAgainLabel);
		
		closeLabel = new JLabel("[关闭]");
		closeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		closeLabel.setForeground(Color.BLUE);
		closeLabel.setBounds(556, 410, 72, 18);
		contentPane.add(closeLabel);
		
		menuTreePanel = new JPanel();
		menuTreePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "菜单", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(210, 105, 30)));
		menuTreePanel.setBounds(10, 44, 185, 353);
		contentPane.add(menuTreePanel);
		menuTreePanel.setLayout(null);
		
		menuTreeScrollPane = new JScrollPane();
		menuTreeScrollPane.setBounds(14, 25, 157, 315);
		menuTreePanel.add(menuTreeScrollPane);
		
		menuTree = new JTree(QueryRoot(userID));// 利用根节点直接创建树
		menuTreeScrollPane.setViewportView(menuTree);
		
		picturePanel = new JPanel();
		picturePanel.setBounds(209, 44, 489, 353);
		contentPane.add(picturePanel);
		picturePanel.setLayout(null);
		
		btnNewButton = new JButton("New button");
		btnNewButton.setBounds(269, 115, 113, 27);
		picturePanel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new UsersRightsFrame();
			}
		});
		Thread t1= new Thread(){
            public void run(){
            	String title = "☆出版社管理信息系统☆   当前用户:";
				String userName = UsersDao.MyUser;
                while(true){   //子线程更新顶部时间
                	try {
						Thread.sleep(1 * 1000);
						String time = MyUtil.getCurrentTime();
						setTitle(title+userName+"  "+time);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} //设置暂停的时间 1 秒
                }              
            }
		};
		t1.start();
	}
	
	public DefaultMutableTreeNode QueryRoot(int id){
		root = new DefaultMutableTreeNode("根节点，可以是用户名");//创建根节点
		//查询id已经分配的权限<权限名，所属模块>
		List<HashMap<String,String>> result = UsersRightsDao.getInstance().AllocatedByUserID(id);
		//保存所有的一级节点
		List<DefaultMutableTreeNode> nodeFirst = new ArrayList<DefaultMutableTreeNode>();
		//保存所有的二级节点
		List<DefaultMutableTreeNode> nodeSecond = new ArrayList<DefaultMutableTreeNode>();
		for(int i=0;i<result.size();i++)
			for(Map.Entry<String, String> arg:result.get(i).entrySet()){  //key:权限名  value:所属模块
				boolean findNode = false; //标记是否找到相同命名的节点
				if(nodeFirst.size() == 0)  //不存在一级节点，则肯定没有重复，直接添加
					nodeFirst.add(new DefaultMutableTreeNode(arg.getValue()));
				else{
					for(DefaultMutableTreeNode node: nodeFirst){
						System.out.println(node.getUserObject().toString());
						if(node.getUserObject().toString().equals(arg.getValue())){//已经有此命名的节点
							findNode = true;
							break;
						}
					}
					if(!findNode)  //不存在该命名的节点
						nodeFirst.add(new DefaultMutableTreeNode(arg.getValue()));
				}	
				nodeSecond.add(new DefaultMutableTreeNode(arg.getKey()));//所有的权限名皆属于二级节点
			}
		//将所有的一级节点添加进根节点
		for(DefaultMutableTreeNode item: nodeFirst)
			root.add(item);
		
		//将所有的二级节点添加进对应的一级节点中
		for(int i = 0; i < nodeSecond.size(); i++)
			for(Map.Entry<String, String> arg:result.get(i).entrySet()){
				for(DefaultMutableTreeNode node: nodeFirst)
					if(node.getUserObject().toString().equals(arg.getValue())){
						node.add(nodeSecond.get(i));
						break;
					}				
			}
		return root;
	}
}
