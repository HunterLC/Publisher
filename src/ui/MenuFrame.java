package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bll.RightsDao;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;

public class MenuFrame extends JFrame {

	private JPanel contentPane,menuTreePanel;
	private JLabel loginDateLabel,dateLabel,loginAgainLabel,closeLabel,pictureLabel;
	private JScrollPane menuTreeScrollPane;
	private DefaultMutableTreeNode root;
	private JTree menuTree;


	/**
	 * Create the frame.
	 */
	public MenuFrame(int userID) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1022, 628);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		loginDateLabel = new JLabel("登陆日期：");
		loginDateLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		loginDateLabel.setForeground(Color.RED);
		loginDateLabel.setBounds(14, 13, 111, 18);
		contentPane.add(loginDateLabel);
		
		dateLabel = new JLabel(LoginFrame.loginDay);
		dateLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		dateLabel.setForeground(Color.RED);
		dateLabel.setBounds(104, 13, 185, 18);
		contentPane.add(dateLabel);
		
		loginAgainLabel = new JLabel("[重新登录]");
		loginAgainLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		loginAgainLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				new LoginFrame();
			}
		});
		loginAgainLabel.setForeground(Color.BLUE);
		loginAgainLabel.setBounds(471, 562, 123, 18);
		contentPane.add(loginAgainLabel);
		
		closeLabel = new JLabel("[关闭]");
		closeLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		closeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		closeLabel.setForeground(Color.BLUE);
		closeLabel.setBounds(725, 562, 72, 18);
		contentPane.add(closeLabel);
		
		menuTreePanel = new JPanel();
		menuTreePanel.setFont(new Font("宋体", Font.PLAIN, 20));
		menuTreePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "菜单", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(210, 105, 30)));
		menuTreePanel.setBounds(10, 44, 239, 536);
		contentPane.add(menuTreePanel);
		menuTreePanel.setLayout(null);
		
		menuTreeScrollPane = new JScrollPane();
		menuTreeScrollPane.setBounds(14, 25, 211, 498);
		menuTreePanel.add(menuTreeScrollPane);
		
		menuTree = new JTree(QueryRoot(userID));// 利用根节点直接创建树
		menuTree.setRootVisible(false);
		menuTree.setRowHeight(30);//树节点的行高为30像素
		menuTree.setFont(new Font("宋体", Font.BOLD, 16));//设置树结点的字体
		//节点间不采用连接线
		ImageIcon img1 = new ImageIcon("image/frame_3.png");
		ImageIcon img2 = new ImageIcon("image/frame_1.png");
		ImageIcon img3 = new ImageIcon("image/frame_2.png");
		menuTree.putClientProperty("JTree.lineStyle", "None");
		DefaultTreeCellRenderer treeCellRenderer;// 获得树节点的绘制对象
		treeCellRenderer = (DefaultTreeCellRenderer) menuTree.getCellRenderer();
		treeCellRenderer.setLeafIcon(img1);// 设置叶子节点采用图标
		treeCellRenderer.setClosedIcon(img3);// 设置节点折叠时采用图标
		treeCellRenderer.setOpenIcon(img2);// 设置节点展开时采用图标
		menuTreeScrollPane.setViewportView(menuTree);
		
		//捕获树节点选择事件
		menuTree.addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeSelectionEvent e) {
				TreePath path = e.getPath();// 获得当前选择的路径
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path
						.getLastPathComponent();// 获得当前被选择的节点
				if(node.isLeaf()){ //如果是叶子节点
					Object nodeInfo = node.getUserObject();
					OpenFrame(RightsDao.getInstance().QueryW_Name(nodeInfo.toString()));
				}
				
			}
		});
		// 捕获树节点已经被展开或折叠的事件
		menuTree.addTreeExpansionListener(new TreeExpansionListener() {
			// 树节点已经折叠时触发
			public void treeCollapsed(TreeExpansionEvent e) {
				TreePath path = e.getPath();// 获得已经被折叠节点的路径
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path
						.getLastPathComponent();// 获得已经被折叠的节点
				System.out.println("节点\"" + node + "\"已经被折叠！");
			}
			
			// 树节点已经被展开时触发
			public void treeExpanded(TreeExpansionEvent e) {
				TreePath path = e.getPath();// 获得已经被展开节点的路径
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path
						.getLastPathComponent();// 获得已经被展开的节点
				System.out.println("节点\"" + node + "\"已经被展开！");
			}
		});
		
		ImageIcon img = new ImageIcon("image/menu.png");
		pictureLabel = new JLabel();
		pictureLabel.setIcon(img);
		pictureLabel.setBounds(263, 44, 728, 509);
		contentPane.add(pictureLabel);
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
	
	/**
	 * 按照用户的id初始化菜单数
	 * @param id
	 * @return
	 */
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
	
	/**
	 * 打开相应的窗口
	 * @param name
	 */
	public void OpenFrame(String name){
		switch(name){
			case "W_xtgl":new W_xtgl();break;
			case "W_bpcx":new W_bpcx();break;
			case "W_zgcx":new W_zgcx();break;
			case "W_tsdj":new W_tsdj();break;
			case "W_jhfp":new W_jhfp();break;
			case "W_yszd":new W_yszd();break;
			case "W_jscl":new W_jscl();break;
			case "W_xsrk":new W_xsrk();break;
			case "W_pdrk":new W_pdrk();break;
			case "W_yhqx":new UsersRightsFrame();break;
		}
	}
}
