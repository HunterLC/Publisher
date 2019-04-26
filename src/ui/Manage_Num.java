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

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import bll.NumDao;
import bll.SimpleDao;
import bll.UsersDao;
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

public class Manage_Num extends JFrame {

	private JPanel contentPane,listPanel,settingPanel;
	private JTextField nameTextField;
	private JScrollPane scrollPane;
	private JTable listTable;
	private DefaultTableModel tableModel;
	private TitledBorder titledBorder,titledBorder1;
	private String head[]=null;  //�������
	private Object [][]data=null;   //����б�
	private JLabel nameLabel;
	private JButton saveButton,deleteButton,resetButton,closeButton;
	private static int CURRENTID = 0;
	private JLabel label;
	private JTextField numTextField;

	

	/**
	 * Create the frame.
	 */
	public Manage_Num() {
		try
	    {		
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;			
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();			
			UIManager.put("RootPane.setupButtonVisible", false);
	      //���ô˿�����Ϊfalse����ʾ�ر�֮��BeautyEye LNF��Ĭ����true
	        BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
	    }
	    catch(Exception e)
	    {
	        //TODO exception
	    }
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 554, 483);
		setTitle("��������Ϣ���༭�Ҵ���������͡�");
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listPanel = new JPanel();
		titledBorder = new TitledBorder(UIManager.getBorder("TitledBorder.border"), "��"+NumDao.getInstance().QueryCount()+"��", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED);
		titledBorder.setTitleFont(new Font("����", Font.PLAIN, 20));
		listPanel.setBorder(titledBorder);
		listPanel.setBounds(14, 0, 238, 423);
		contentPane.add(listPanel);
		listPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 24, 210, 386);
		listPanel.add(scrollPane);
		
		//��ʼ�����
		listTable = new JTable();
		listTable.setRowHeight(25);
		listTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		head=new String[] {"���","�༭������"};
		tableModel=new DefaultTableModel(queryData(),head){
		public boolean isCellEditable(int row, int column)
		{
			return false;
			}
		};
		listTable.setFont(new Font("����", Font.BOLD, 14));
		listTable.addMouseListener(new MouseAdapter() {   //�û���������¼�
			@Override
			public void mouseClicked(MouseEvent arg0) {
				titledBorder1.setTitle("�޸�");
				settingPanel.repaint();
				//���༭�ұ༭��
				//String num = Integer.valueOf(listTable.getValueAt(listTable.getSelectedRow(), 0).toString());
				String num = listTable.getValueAt(listTable.getSelectedRow(), 0).toString();
				String bjsName = listTable.getValueAt(listTable.getSelectedRow(), 1).toString();
				nameTextField.setText(bjsName);
				numTextField.setText(num);
				CURRENTID = NumDao.getInstance().QueryIDByName(bjsName);//��õ�ǰѡ�б༭�ҵ�id
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
		titledBorder1 = new TitledBorder(null, "����", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY);
		titledBorder1.setTitleFont(new Font("����", Font.PLAIN, 20));
		settingPanel.setBorder(titledBorder1);
		settingPanel.setBounds(266, 0, 261, 423);
		contentPane.add(settingPanel);
		settingPanel.setLayout(null);
		
		nameLabel = new JLabel("�༭������");
		nameLabel.setBounds(14, 80, 106, 18);
		settingPanel.add(nameLabel);
		nameLabel.setFont(new Font("����", Font.PLAIN, 20));
		
		nameTextField = new JTextField();
		nameTextField.setBounds(118, 77, 129, 24);
		settingPanel.add(nameTextField);
		nameTextField.setFont(new Font("����", Font.PLAIN, 20));
		nameTextField.setColumns(10);
		
		saveButton = new JButton("����");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bjsName = nameTextField.getText();
				String num = numTextField.getText();
				if(num.equals("")) //�����Ϊ��
					JOptionPane.showMessageDialog(null,"�����Ϊ��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				else if(bjsName.equals("")) {//�༭����Ϊ��
					JOptionPane.showMessageDialog(null,"�༭����Ϊ��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
				else {  //�༭������Ϊ��
					if(CURRENTID !=0 ){  //��ǰѡ����һ��
						//�޸�֮�������Ż�༭���Ѿ�����
						if(!isNumeric(num))//���������Ų�Ϊ����
							JOptionPane.showMessageDialog(null,"����ű���Ϊ������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else if(Integer.valueOf(num)<=0)
							JOptionPane.showMessageDialog(null,"����ű���Ϊ������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else if(NumDao.getInstance().QueryNumByID(CURRENTID) != Integer.valueOf(num) && NumDao.getInstance().numIsExist(Integer.valueOf(num)))
							JOptionPane.showMessageDialog(null,"��������Ѿ�����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else if(!NumDao.getInstance().QueryNameByID(CURRENTID).equals(bjsName) && NumDao.getInstance().nameIsExist(bjsName))
							JOptionPane.showMessageDialog(null,"�ñ༭�����Ѿ�����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else//�޸�֮�������š��༭�Ҳ�����					
							NumDao.getInstance().Update(Integer.valueOf(num),bjsName,CURRENTID);
					}
					else if(CURRENTID ==0 ){  //��ӱ༭��
						if(!isNumeric(num))//���������Ų�Ϊ����
							JOptionPane.showMessageDialog(null,"����ű���Ϊ������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else if(Integer.valueOf(num)<=0)
							JOptionPane.showMessageDialog(null,"����ű���Ϊ������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else if(NumDao.getInstance().QueryNumByID(CURRENTID) != Integer.valueOf(num) && NumDao.getInstance().numIsExist(Integer.valueOf(num)))
							JOptionPane.showMessageDialog(null,"��������Ѿ�����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else if(NumDao.getInstance().nameIsExist(bjsName))
							JOptionPane.showMessageDialog(null,"�ñ༭�����Ѿ�����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else {
							NumDao.getInstance().Add(Integer.valueOf(num),bjsName);
							CURRENTID =NumDao.getInstance().QueryIDByName(bjsName);//���µ�ǰidָ��Ϊ�½��û���id
							deleteButton.setEnabled(true);
							resetButton.setEnabled(true);
							titledBorder1.setTitle("�޸�");
							settingPanel.repaint();
						}
					}
					RefreshListPanel();//ˢ���б�
					SetSelected();//����ѡ��
				}
			}
		});
		saveButton.setFont(new Font("����", Font.PLAIN, 20));
		saveButton.setBounds(68, 218, 113, 27);
		settingPanel.add(saveButton);
		
		deleteButton = new JButton("ɾ��");
		deleteButton.setEnabled(false);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selection = JOptionPane.showConfirmDialog(null,"ȷ��ɾ��"+NumDao.getInstance().QueryNameByID(CURRENTID)+"��","ɾ��",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if( selection == JOptionPane.OK_OPTION ){
					String bjsName = nameTextField.getText();
					NumDao.getInstance().Delete(bjsName);
					RefreshListPanel();//ˢ��table
					listTable.clearSelection();
					CURRENTID = 0; //��ǰ�û�id����
					nameTextField.setText("");
					numTextField.setText("");
					nameTextField.requestFocus();
					deleteButton.setEnabled(false);
					titledBorder1.setTitle("����");
					settingPanel.repaint();
				}
			}
		});
		deleteButton.setFont(new Font("����", Font.PLAIN, 20));
		deleteButton.setBounds(68, 268, 113, 27);
		settingPanel.add(deleteButton);
		
		resetButton = new JButton("��λ");
		resetButton.setEnabled(false);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				titledBorder1.setTitle("����");
				settingPanel.repaint();
				nameTextField.setText("");
				numTextField.setText("");
				nameTextField.requestFocus();
				deleteButton.setEnabled(false);
				resetButton.setEnabled(false);
				CURRENTID = 0;
				RefreshListPanel(); //ˢ�±༭���б�
			}
		});
		resetButton.setFont(new Font("����", Font.PLAIN, 20));
		resetButton.setBounds(68, 314, 113, 27);
		settingPanel.add(resetButton);
		
		closeButton = new JButton("�ر�");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		closeButton.setFont(new Font("����", Font.PLAIN, 20));
		closeButton.setBounds(68, 361, 113, 27);
		settingPanel.add(closeButton);
		
		label = new JLabel("\u5E8F\u53F7");
		label.setFont(new Font("����", Font.PLAIN, 20));
		label.setBounds(14, 39, 72, 18);
		settingPanel.add(label);
		
		numTextField = new JTextField();
		numTextField.setFont(new Font("����", Font.PLAIN, 20));
		numTextField.setBounds(117, 36, 130, 24);
		settingPanel.add(numTextField);
		numTextField.setColumns(10);
	}
	
	//�����û����������
    /**
     * @return
     */
    public Object[][] queryData(){
        List<Bjs_Num> list=NumDao.getInstance().QueryAll();
        data=new Object[list.size()][head.length];
        for(int i=0;i<list.size();i++){
            for(int j=0;j<head.length;j++){
                data[i][0]=list.get(i).getNum();
                data[i][1]=list.get(i).getBjsName();
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
    	titledBorder.setTitle("��"+NumDao.getInstance().QueryCount()+"��");
		listPanel.repaint();
		listPanel.validate();
		listPanel.repaint();
	}
	
	//���ñ����ѡ��
	public void SetSelected(){
		String queryName = NumDao.getInstance().QueryNameByID(CURRENTID);
		for(int i =0;i<listTable.getRowCount();i++)
			if(queryName.equals(listTable.getValueAt(i,1).toString())){
				listTable.setRowSelectionInterval(i,i);//����������û���Ϊѡ����ʽ��
				break;
			}
	}
	
	/**
              * ����������ʽ�ж��ַ����Ƿ�������
     * @param str
     * @return
     */
    public boolean isNumeric(String str){
           Pattern pattern = Pattern.compile("[0-9]*");
           Matcher isNum = pattern.matcher(str);
           if( !isNum.matches() ){
               return false;
           }
           return true;
    }
}
