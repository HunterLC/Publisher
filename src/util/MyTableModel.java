package util;

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

@SuppressWarnings("serial")
public class MyTableModel extends DefaultTableModel{
	
	@SuppressWarnings("rawtypes")
	public MyTableModel(Vector data,Vector colnames){
		super(data,colnames);
	}
	
	public void setTablemodel(){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				fireTableDataChanged();
			}
		});
	}
	
	public boolean isCellEditable(int row,int col){
		return false;
	}
}
