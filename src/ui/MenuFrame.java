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
		
		loginDateLabel = new JLabel("��½���ڣ�");
		loginDateLabel.setForeground(Color.RED);
		loginDateLabel.setBounds(0, 13, 88, 18);
		contentPane.add(loginDateLabel);
		
		dateLabel = new JLabel(LoginFrame.loginDay);
		dateLabel.setForeground(Color.RED);
		dateLabel.setBounds(68, 13, 72, 18);
		contentPane.add(dateLabel);
		
		loginAgainLabel = new JLabel("[���µ�¼]");
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
		
		closeLabel = new JLabel("[�ر�]");
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
		menuTreePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "�˵�", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(210, 105, 30)));
		menuTreePanel.setBounds(10, 44, 185, 353);
		contentPane.add(menuTreePanel);
		menuTreePanel.setLayout(null);
		
		menuTreeScrollPane = new JScrollPane();
		menuTreeScrollPane.setBounds(14, 25, 157, 315);
		menuTreePanel.add(menuTreeScrollPane);
		
		menuTree = new JTree(QueryRoot(userID));// ���ø��ڵ�ֱ�Ӵ�����
		menuTree.setRootVisible(false);
		menuTree.setRowHeight(20);//���ڵ���и�Ϊ20����
		menuTree.setFont(new Font("����", Font.BOLD, 14));//��������������
		//�ڵ�䲻����������
		menuTree.putClientProperty("JTree.lineStyle", "None");
		DefaultTreeCellRenderer treeCellRenderer;// ������ڵ�Ļ��ƶ���
		treeCellRenderer = (DefaultTreeCellRenderer) menuTree.getCellRenderer();
		treeCellRenderer.setLeafIcon(null);// ����Ҷ�ӽڵ㲻����ͼ��
		treeCellRenderer.setClosedIcon(null);// ���ýڵ��۵�ʱ������ͼ��
		treeCellRenderer.setOpenIcon(null);// ���ýڵ�չ��ʱ������ͼ��
		menuTreeScrollPane.setViewportView(menuTree);
		//�������ڵ�ѡ���¼�
		menuTree.addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeSelectionEvent e) {
				TreePath path = e.getPath();// ��õ�ǰѡ���·��
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path
						.getLastPathComponent();// ��õ�ǰ��ѡ��Ľڵ�
				if(node.isLeaf()){ //�����Ҷ�ӽڵ�
					Object nodeInfo = node.getUserObject();
					OpenFrame(RightsDao.getInstance().QueryW_Name(nodeInfo.toString()));
				}
				
			}
		});
		// �������ڵ��Ѿ���չ�����۵����¼�
		menuTree.addTreeExpansionListener(new TreeExpansionListener() {
			// ���ڵ��Ѿ��۵�ʱ����
			public void treeCollapsed(TreeExpansionEvent e) {
				TreePath path = e.getPath();// ����Ѿ����۵��ڵ��·��
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path
						.getLastPathComponent();// ����Ѿ����۵��Ľڵ�
				System.out.println("�ڵ�\"" + node + "\"�Ѿ����۵���");
			}
			
			// ���ڵ��Ѿ���չ��ʱ����
			public void treeExpanded(TreeExpansionEvent e) {
				TreePath path = e.getPath();// ����Ѿ���չ���ڵ��·��
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path
						.getLastPathComponent();// ����Ѿ���չ���Ľڵ�
				System.out.println("�ڵ�\"" + node + "\"�Ѿ���չ����");
			}
		});
		
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
            	String title = "������������Ϣϵͳ��   ��ǰ�û�:";
				String userName = UsersDao.MyUser;
                while(true){   //���̸߳��¶���ʱ��
                	try {
						Thread.sleep(1 * 1000);
						String time = MyUtil.getCurrentTime();
						setTitle(title+userName+"  "+time);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} //������ͣ��ʱ�� 1 ��
                }              
            }
		};
		t1.start();
	}
	
	/**
	 * �����û���id��ʼ���˵���
	 * @param id
	 * @return
	 */
	public DefaultMutableTreeNode QueryRoot(int id){
		root = new DefaultMutableTreeNode("���ڵ㣬�������û���");//�������ڵ�
		//��ѯid�Ѿ������Ȩ��<Ȩ����������ģ��>
		List<HashMap<String,String>> result = UsersRightsDao.getInstance().AllocatedByUserID(id);
		//�������е�һ���ڵ�
		List<DefaultMutableTreeNode> nodeFirst = new ArrayList<DefaultMutableTreeNode>();
		//�������еĶ����ڵ�
		List<DefaultMutableTreeNode> nodeSecond = new ArrayList<DefaultMutableTreeNode>();
		for(int i=0;i<result.size();i++)
			for(Map.Entry<String, String> arg:result.get(i).entrySet()){  //key:Ȩ����  value:����ģ��
				boolean findNode = false; //����Ƿ��ҵ���ͬ�����Ľڵ�
				if(nodeFirst.size() == 0)  //������һ���ڵ㣬��϶�û���ظ���ֱ�����
					nodeFirst.add(new DefaultMutableTreeNode(arg.getValue()));
				else{
					for(DefaultMutableTreeNode node: nodeFirst){
						System.out.println(node.getUserObject().toString());
						if(node.getUserObject().toString().equals(arg.getValue())){//�Ѿ��д������Ľڵ�
							findNode = true;
							break;
						}
					}
					if(!findNode)  //�����ڸ������Ľڵ�
						nodeFirst.add(new DefaultMutableTreeNode(arg.getValue()));
				}	
				nodeSecond.add(new DefaultMutableTreeNode(arg.getKey()));//���е�Ȩ���������ڶ����ڵ�
			}
		//�����е�һ���ڵ���ӽ����ڵ�
		for(DefaultMutableTreeNode item: nodeFirst)
			root.add(item);
		
		//�����еĶ����ڵ���ӽ���Ӧ��һ���ڵ���
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
	 * ����Ӧ�Ĵ���
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
