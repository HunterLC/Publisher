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

import bll.IdentifierDao;
import bll.NumDao;
import bll.SimpleDao;
import bll.UsersDao;
import model.Bjs_Identifier;
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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class Manage_Identifier extends JFrame {

	private JPanel contentPane,listPanel,settingPanel;
	private JTextField nameTextField;
	private JScrollPane scrollPane;
	private JTable listTable;
	private DefaultTableModel tableModel;
	private TitledBorder titledBorder,titledBorder1;
	private String head[]=null;  //�������
	private Object [][]data=null;   //����б�
	private JLabel nameLabel,label,isUse;
	private JButton saveButton,deleteButton,resetButton,closeButton;
	private static int CURRENTID = 0;
	private JTextField numTextField;
	private JRadioButton yesRadioButton,noRadioButton;
	private ButtonGroup selectedGroup;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manage_Identifier frame = new Manage_Identifier();
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
	public Manage_Identifier() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 697, 554);
		setTitle("��������Ϣ���༭�Ҵ��б�ʶ���͡�");
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listPanel = new JPanel();
		titledBorder = new TitledBorder(UIManager.getBorder("TitledBorder.border"), "��"+IdentifierDao.getInstance().QueryCount()+"��", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED);
		titledBorder.setTitleFont(new Font("����", Font.PLAIN, 20));
		listPanel.setBorder(titledBorder);
		listPanel.setBounds(14, 0, 345, 494);
		contentPane.add(listPanel);
		listPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 24, 317, 457);
		listPanel.add(scrollPane);
		
		//��ʼ�����
		listTable = new JTable();
		listTable.setRowHeight(25);
		listTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		head=new String[] {"���","�༭������","�Ƿ�����"};
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
				String isUse = listTable.getValueAt(listTable.getSelectedRow(), 2).toString();
				nameTextField.setText(bjsName);
				numTextField.setText(num);
				if(isUse.equals("��"))
					yesRadioButton.setSelected(true);
				else
					noRadioButton.setSelected(true);
				CURRENTID = IdentifierDao.getInstance().QueryIDByName(bjsName);//��õ�ǰѡ�б༭�ҵ�id
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
		settingPanel.setBounds(395, 0, 261, 494);
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
				String isUse = yesRadioButton.isSelected() ? "��" : "��";
				if(num.equals("")) //�����Ϊ��
					JOptionPane.showMessageDialog(null,"�����Ϊ��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				else if(bjsName.equals("")) {//�༭����Ϊ��
					JOptionPane.showMessageDialog(null,"�༭����Ϊ��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
				else if(!yesRadioButton.isSelected() && !noRadioButton.isSelected()) { //ʹ��״̬δѡ��
					JOptionPane.showMessageDialog(null,"δѡ��༭��ʹ��״̬","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
				else {  //�༭������Ϊ��
					if(CURRENTID !=0 ){  //��ǰѡ����һ��
						//�޸�֮�������Ż�༭���Ѿ�����
						if(!isNumeric(num))//���������Ų�Ϊ����
							JOptionPane.showMessageDialog(null,"���������Ų�Ϊ����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else if(Integer.valueOf(num)<=0)
							JOptionPane.showMessageDialog(null,"����ű���Ϊ������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else if(IdentifierDao.getInstance().QueryNumByID(CURRENTID) != Integer.valueOf(num) && IdentifierDao.getInstance().numIsExist(Integer.valueOf(num)))
							JOptionPane.showMessageDialog(null,"��������Ѿ�����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else if(!IdentifierDao.getInstance().QueryNameByID(CURRENTID).equals(bjsName) && IdentifierDao.getInstance().nameIsExist(bjsName))
							JOptionPane.showMessageDialog(null,"�ñ༭�����Ѿ�����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else//�޸�֮�������š��༭�Ҳ�����					
							IdentifierDao.getInstance().Update(Integer.valueOf(num),bjsName,isUse,CURRENTID);
					}
					else if(CURRENTID ==0 ){  //��ӱ༭��
						if(!isNumeric(num))//���������Ų�Ϊ����
							JOptionPane.showMessageDialog(null,"���������Ų�Ϊ����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else if(Integer.valueOf(num)<=0)
							JOptionPane.showMessageDialog(null,"����ű���Ϊ������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else if(IdentifierDao.getInstance().QueryNumByID(CURRENTID) != Integer.valueOf(num) && IdentifierDao.getInstance().numIsExist(Integer.valueOf(num)))
							JOptionPane.showMessageDialog(null,"��������Ѿ�����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else if(IdentifierDao.getInstance().nameIsExist(bjsName))
							JOptionPane.showMessageDialog(null,"�ñ༭�����Ѿ�����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else {
							IdentifierDao.getInstance().Add(Integer.valueOf(num),bjsName,isUse);
							CURRENTID =IdentifierDao.getInstance().QueryIDByName(bjsName);//���µ�ǰidָ��Ϊ�½��û���id
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
				int selection = JOptionPane.showConfirmDialog(null,"ȷ��ɾ��"+IdentifierDao.getInstance().QueryNameByID(CURRENTID)+"��","ɾ��",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if( selection == JOptionPane.OK_OPTION ){
					String bjsName = nameTextField.getText();
					IdentifierDao.getInstance().Delete(bjsName);
					RefreshListPanel();//ˢ��table
					listTable.clearSelection();
					CURRENTID = 0; //��ǰ�û�id����
					nameTextField.setText("");
					numTextField.setText("");
					nameTextField.requestFocus();
					selectedGroup.clearSelection();
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
				selectedGroup.clearSelection();
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
		
		isUse = new JLabel("\u662F\u5426\u5728\u7528");
		isUse.setFont(new Font("����", Font.PLAIN, 20));
		isUse.setBounds(14, 124, 106, 18);
		settingPanel.add(isUse);
		
		yesRadioButton = new JRadioButton("\u662F");
		yesRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(yesRadioButton.isSelected())
					yesRadioButton.setSelected(false);
				else
					yesRadioButton.setSelected(true);
			}
		});
		yesRadioButton.setFont(new Font("����", Font.PLAIN, 20));
		yesRadioButton.setBounds(116, 120, 65, 27);
		settingPanel.add(yesRadioButton);
		
		noRadioButton = new JRadioButton("\u5426");
		noRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(noRadioButton.isSelected())
					noRadioButton.setSelected(false);
				else
					noRadioButton.setSelected(true);
			}
		});
		noRadioButton.setFont(new Font("����", Font.PLAIN, 20));
		noRadioButton.setBounds(117, 154, 157, 27);
		settingPanel.add(noRadioButton);
		
		selectedGroup = new ButtonGroup();//��ѡ��
		selectedGroup.add(yesRadioButton);
		selectedGroup.add(noRadioButton);
	}
	
	//�����û����������
    /**
     * @return
     */
    public Object[][] queryData(){
        List<Bjs_Identifier> list=IdentifierDao.getInstance().QueryAll();
        data=new Object[list.size()][head.length];
        for(int i=0;i<list.size();i++){
            for(int j=0;j<head.length;j++){
                data[i][0]=list.get(i).getNum();
                data[i][1]=list.get(i).getBjsName();
                data[i][2]=list.get(i).getIsUse();
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
    	titledBorder.setTitle("��"+IdentifierDao.getInstance().QueryCount()+"��");
		listPanel.repaint();
		listPanel.validate();
		listPanel.repaint();
	}
	
	//���ñ����ѡ��
	public void SetSelected(){
		String queryName = IdentifierDao.getInstance().QueryNameByID(CURRENTID);
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
