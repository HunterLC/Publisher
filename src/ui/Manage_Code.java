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
	private String head[]=null;  //�������
	private Object [][]data=null;   //����б�
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
		setTitle("��������Ϣ��ͼ�����ʹ��б��롿");
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listPanel = new JPanel();
		titledBorder = new TitledBorder(UIManager.getBorder("TitledBorder.border"), "��"+CodeDao.getInstance().QueryCount()+"��", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED);
		titledBorder.setTitleFont(new Font("����", Font.PLAIN, 20));
		listPanel.setBorder(titledBorder);
		listPanel.setBounds(14, 0, 407, 423);
		contentPane.add(listPanel);
		listPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 24, 368, 386);
		listPanel.add(scrollPane);
		
		//��ʼ�����
		listTable = new JTable();
		listTable.setRowHeight(25);
		listTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		head=new String[] {"����","ͼ��������"};
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
				String code = listTable.getValueAt(listTable.getSelectedRow(), 0).toString();
				String bookType = listTable.getValueAt(listTable.getSelectedRow(), 1).toString();
				nameTextField.setText(bookType);
				numTextField.setText(code);
				CURRENTID = CodeDao.getInstance().QueryIDByName(bookType);//��õ�ǰѡ�б༭�ҵ�id
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
		settingPanel.setBounds(423, 0, 376, 423);
		contentPane.add(settingPanel);
		settingPanel.setLayout(null);
		
		nameLabel = new JLabel("ͼ������");
		nameLabel.setBounds(14, 80, 106, 18);
		settingPanel.add(nameLabel);
		nameLabel.setFont(new Font("����", Font.PLAIN, 20));
		
		nameTextField = new JTextField();
		nameTextField.setBounds(118, 77, 244, 24);
		settingPanel.add(nameTextField);
		nameTextField.setFont(new Font("����", Font.PLAIN, 20));
		nameTextField.setColumns(10);
		
		saveButton = new JButton("����");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookType = nameTextField.getText();
				String code = numTextField.getText();
				if(code.equals("")) //�����Ϊ��
					JOptionPane.showMessageDialog(null,"����Ϊ��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				else if(bookType.equals("")) {//ͼ��������Ϊ��
					JOptionPane.showMessageDialog(null,"ͼ��������Ϊ��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
				else {  //ͼ����������Ϊ��
					if(CURRENTID !=0 ){  //��ǰѡ����һ��
						//�޸�֮�������Ż�༭���Ѿ�����
						if(!CodeDao.getInstance().QueryCodeByID(CURRENTID).equals(code)  && CodeDao.getInstance().codeIsExist(code))
							JOptionPane.showMessageDialog(null,"�ñ����Ѿ�����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else if(!CodeDao.getInstance().QueryNameByID(CURRENTID).equals(bookType) && CodeDao.getInstance().nameIsExist(bookType))
							JOptionPane.showMessageDialog(null,"��ͼ���������Ѿ�����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else//�޸�֮�������š��༭�Ҳ�����					
							CodeDao.getInstance().Update(code,bookType,CURRENTID);
					}
					else if(CURRENTID ==0 ){  //��ӱ༭��
						if(CodeDao.getInstance().codeIsExist(code))
							JOptionPane.showMessageDialog(null,"�ñ�����Ѿ�����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else if(CodeDao.getInstance().nameIsExist(bookType))
							JOptionPane.showMessageDialog(null,"�ñ༭�����Ѿ�����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						else {
							CodeDao.getInstance().Add(code,bookType);
							CURRENTID =CodeDao.getInstance().QueryIDByName(bookType);//���µ�ǰidָ��Ϊ�½��û���id
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
				int selection = JOptionPane.showConfirmDialog(null,"ȷ��ɾ��"+CodeDao.getInstance().QueryNameByID(CURRENTID)+"��","ɾ��",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if( selection == JOptionPane.OK_OPTION ){
					String bookType = nameTextField.getText();
					CodeDao.getInstance().Delete(bookType);
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
		
		label = new JLabel("����");
		label.setFont(new Font("����", Font.PLAIN, 20));
		label.setBounds(14, 39, 72, 18);
		settingPanel.add(label);
		
		numTextField = new JTextField();
		numTextField.setFont(new Font("����", Font.PLAIN, 20));
		numTextField.setBounds(117, 36, 245, 24);
		settingPanel.add(numTextField);
		numTextField.setColumns(10);
	}
	
	//�����û����������
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
    	titledBorder.setTitle("��"+CodeDao.getInstance().QueryCount()+"��");
		listPanel.repaint();
		listPanel.validate();
		listPanel.repaint();
	}
	
	//���ñ����ѡ��
	public void SetSelected(){
		String queryName = CodeDao.getInstance().QueryNameByID(CURRENTID);
		for(int i =0;i<listTable.getRowCount();i++)
			if(queryName.equals(listTable.getValueAt(i,1).toString())){
				listTable.setRowSelectionInterval(i,i);//����������û���Ϊѡ����ʽ��
				break;
			}
	}
	

}
