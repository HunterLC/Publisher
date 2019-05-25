package ui;

import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

import com.itextpdf.text.DocumentException;

import bll.ALLMethod;
import model.bookin;
import model.zhifang;

public class BB1Window extends JFrame
{

    private JPanel contentPane;
    private JTable table;
    private ALLMethod method;
    private List<bookin> info;

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    BB1Window frame = new BB1Window();
                    frame.setVisible(true);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * 
     * @throws ParseException
     */
    public BB1Window() throws ParseException
    {

        setTitle("\u56FE\u4E66\u5165\u5E93\u4E00\u89C8");
        setBackground(Color.WHITE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 809);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        method = new ALLMethod();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 82, 1052, 688);
        contentPane.add(scrollPane);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new LineBorder(new Color(0, 153, 204)));
        panel.setBackground(Color.WHITE);
        panel.setBounds(10, 10, 523, 62);
        contentPane.add(panel);

        Choice choice = new Choice();

        choice.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        choice.setBounds(124, 10, 132, 30);
        List<String> strings = method.getnian();
        for (String s : strings)
            choice.add(s);
        panel.add(choice);

        JLabel label = new JLabel("\u5165\u5E93\u5E74\u4EFD\u9009\u62E9");
        label.setForeground(new Color(0, 153, 204));
        label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        label.setBounds(10, 10, 108, 31);
        panel.add(label);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBorder(new LineBorder(new Color(0, 153, 204)));
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(543, 10, 519, 62);
        contentPane.add(panel_1);

        JLabel label_3 = new JLabel("\u62A5\u8868\u751F\u6210");
        label_3.setBounds(10, 10, 92, 31);
        panel_1.add(label_3);
        label_3.setForeground(new Color(0, 153, 204));
        label_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));

        JButton button_3 = new JButton("\u76F4\u65B9\u56FE");
        button_3.setBounds(315, 10, 106, 36);
        panel_1.add(button_3);
        button_3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                List<zhifang> zhi = new ArrayList<zhifang>();
                zhi = method.getyuefen("%" + choice.getSelectedItem() + "%");
                myGraph graph = new myGraph(zhi);
                graph.create();
                JOptionPane.showMessageDialog(null, "直方图生成成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        button_3.setForeground(Color.BLACK);
        button_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        button_3.setFocusPainted(false);
        button_3.setBackground(SystemColor.menu);

        JButton button_2 = new JButton("PDF");
        button_2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                myPDF1 my = new myPDF1(info, choice.getSelectedItem());
                try
                {
                    my.createPDF();
                } catch (DocumentException | IOException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "PDF报表生成成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        button_2.setBounds(138, 10, 106, 36);
        panel_1.add(button_2);
        button_2.setForeground(Color.BLACK);
        button_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        button_2.setFocusPainted(false);
        button_2.setBackground(SystemColor.menu);

        table = new JTable();
        table.setRowHeight(30);
        table.setFont(new Font("宋体", Font.PLAIN, 18));
        scrollPane.setViewportView(table);
        info = method.getbookin(choice.getSelectedItem());
        BookInfoTableModel model = new BookInfoTableModel(info);
        table.setModel(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(10);
        table.getColumnModel().getColumn(3).setPreferredWidth(10);
        table.getColumnModel().getColumn(4).setPreferredWidth(10);
        table.getColumnModel().getColumn(5).setPreferredWidth(10);

        choice.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                String string = String.valueOf(e.getItem());
                try
                {
                    info = method.getbookin(string);
                } catch (ParseException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                model.ChangeData(info);
            }
        });

    }

    public class BookInfoTableModel extends AbstractTableModel
    {

        private List<bookin> data = new ArrayList<bookin>();

        @Override
        public int getRowCount()
        {
            return data.size();
        }

        @Override
        public int getColumnCount()
        {
            // 根据实际情况返回列数
            return 6;
        }

        @Override
        public String getColumnName(int column)
        {
            // 根据实际情况返回列名
            switch (column)
            {
                case 0:
                    return "书号";
                case 1:
                    return "书名";
                case 2:
                    return "作者";
                case 3:
                    return "入库册数";
                case 4:
                    return "单价";
                default:
                    return "总码样";
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            bookin info = data.get(rowIndex);
            if (columnIndex == 0)
                return info.getShuh();
            if (columnIndex == 1)
                return info.getShum();
            if (columnIndex == 2)
                return info.getZuozhe();
            if (columnIndex == 3)
                return info.getKuchun();
            if (columnIndex == 4)
                return info.getDj();
            else
                return info.getKuchun() * info.getDj();
        }

        public BookInfoTableModel(List<bookin> data)
        {
            if (data == null)
                throw new IllegalArgumentException("参数data不能为null。");

            this.data = data;

            fireTableDataChanged();
        }

        public void ChangeData(List<bookin> data)
        {
            if (data == null)
                throw new IllegalArgumentException("参数data不能为null。");

            this.data = data;

            fireTableDataChanged();
        }

        public void setData(List<bookin> data)
        {
            this.data = data;
        }
    }
}
