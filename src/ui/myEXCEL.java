package ui;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import model.Books_test8;

public class myEXCEL
{
    private List<Books_test8> date;

    public myEXCEL(List<Books_test8> date)
    {
        this.date = date;
    }

	public void createExcel() throws WriteException, IOException {
		OutputStream os = new FileOutputStream("图书库存一览表EXCEL.xls");
		// 创建工作薄
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		// 创建新的一页
		WritableSheet sheet = workbook.createSheet("First Sheet", 0);
		// 创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
		Label shuh = new Label(0, 0, "书号");
		sheet.addCell(shuh);
		Label shum = new Label(1, 0, "专业");
		sheet.addCell(shum);
		Label zuozhe = new Label(2, 0, "作者");
		sheet.addCell(zuozhe);
		Label fenlei = new Label(3, 0, "图书分类");
		sheet.addCell(fenlei);
		Label kaiben = new Label(4, 0, "开本");
		sheet.addCell(kaiben);
		Label kuchun = new Label(5, 0, "库存");
		sheet.addCell(kuchun);
		Label dj = new Label(6, 0, "单价");
		sheet.addCell(dj);
		Label mayang = new Label(7, 0, "码样");
		sheet.addCell(mayang);

		for (int i = 1; i <= date.size(); i++)
			for (int j = 0; j < 8; j++) {
				Label shuju = new Label(j, i, String.valueOf(date.get(i - 1).get(j)));
				sheet.addCell(shuju);
			}

		// 把创建的内容写入到输出流中，并关闭输出流
		workbook.write();
		workbook.close();
		os.close();
	}

}
