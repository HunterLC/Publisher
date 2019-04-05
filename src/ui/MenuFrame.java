package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bll.UsersDao;
import util.MyUtil;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuFrame extends JFrame {

	private JPanel contentPane;
	private JLabel loginDateLabel,dateLabel,loginAgainLabel,closeLabel;


	/**
	 * Create the frame.
	 */
	public MenuFrame() {
		Thread t1= new Thread(){
            public void run(){
            	String title = "☆出版社管理信息系统☆   当前用户:";
				String userName = UsersDao.MyUser;
                while(true){   //子线程更新顶部时间
                	String time = MyUtil.getCurrentTime();
					setTitle(title+userName+"  "+time);
                }              
            }
		};
		t1.start();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		loginDateLabel = new JLabel("登陆日期：");
		loginDateLabel.setForeground(Color.RED);
		loginDateLabel.setBounds(14, 31, 88, 18);
		contentPane.add(loginDateLabel);
		
		dateLabel = new JLabel(LoginFrame.loginDay);
		dateLabel.setForeground(Color.RED);
		dateLabel.setBounds(101, 31, 72, 18);
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
		loginAgainLabel.setBounds(451, 386, 90, 18);
		contentPane.add(loginAgainLabel);
		
		closeLabel = new JLabel("[关闭]");
		closeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		closeLabel.setForeground(Color.BLUE);
		closeLabel.setBounds(566, 386, 72, 18);
		contentPane.add(closeLabel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new UsersRightsFrame();
			}
		});
		btnNewButton.setBounds(90, 181, 113, 27);
		contentPane.add(btnNewButton);
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
