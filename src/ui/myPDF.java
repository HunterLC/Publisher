package ui;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import model.Books_test8;

public class myPDF {

	private static final String DEST = "图书库存一览表PDF.pdf";
	private List<Books_test8> date;

	public myPDF(List<Books_test8> date) {
		this.date = date;
	}

	public void createPDF() throws DocumentException, IOException {
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
		// 创建汉字字体
		BaseFont bfSong = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
		Font font = new Font(bfSong, 20, Font.NORMAL);
		Font font1 = new Font(bfSong, 10, Font.NORMAL);
		writer.setViewerPreferences(PdfWriter.PageModeUseThumbs);
		document.setPageSize(PageSize.A4);// 设置A4
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		writer.setPageEvent(new PdfPageEventHelper() {

			public void onEndPage(PdfWriter writer, Document document) {

				PdfContentByte cb = writer.getDirectContent();
				cb.saveState();

				cb.beginText();
				BaseFont bf = null;
				try {
					bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
				} catch (Exception e) {
					e.printStackTrace();
				}
				cb.setFontAndSize(bf, 10);
				// Footer
				float y = document.bottom(-20);
				cb.showTextAligned(PdfContentByte.ALIGN_CENTER, String.valueOf(writer.getPageNumber()),
						(document.right() + document.left()) / 2, y, 0);
				cb.endText();

				cb.restoreState();
			}
		});

		document.open();
		PdfContentByte canvas = writer.getDirectContent();

		Phrase phrase1 = new Phrase("图书库存一览表", font);
		Phrase phrase2 = new Phrase("报表生成日期：" + df.format(new Date()), font1);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase1, 230, 780, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase2, 420, 735, 0);

		float[] widths = { 10, 8, 3, 6, 3, 3, 3, 4 }; // 设置三列表格的宽度
		PdfPTable table = new PdfPTable(widths);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.setLockedWidth(true);
		table.setTotalWidth(523);

		PdfPCell pdfCell = new PdfPCell();
		pdfCell.setMinimumHeight(30);// 设置表格行高
		pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		Paragraph paragraph = new Paragraph("书号", font1);
		pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("书名", font1);
		pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("作者", font1);
		pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("图书分类", font1);
		pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("开本", font1);
		pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("库存数", font1);
		pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("单价", font1);
		pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("总码样", font1);
		pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		float kuchun = 0, mayang = 0;
		for (int i = 0; i < date.size(); i++) {
			for (int j = 0; j < 8; j++) {
				PdfPCell pdfCell1 = new PdfPCell();
				pdfCell1.setMinimumHeight(30);// 设置表格行高
				if (j == 0 || j == 4) {
					pdfCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				}
				if (j > 4) {
					pdfCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
				}
				Paragraph paragraph1 = new Paragraph(String.valueOf(date.get(i).get(j)), font1);
				pdfCell1.setPhrase(paragraph1);
				table.addCell(pdfCell1);
				if (j == 5) {
					kuchun += Float.parseFloat(date.get(i).get(j).toString());
				}
				if (j == 7) {
					mayang += date.get(i).getCs() * date.get(i).getDj();
				}
			}
		}

		PdfPCell pdfCell1 = new PdfPCell();
		pdfCell1.setMinimumHeight(20);// 设置表格行高
		pdfCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		Paragraph paragraph1 = new Paragraph("合计", font1);
		pdfCell1.setPhrase(paragraph1);
		table.addCell(pdfCell1);
		paragraph1 = new Paragraph(" ", font1);
		pdfCell1.setPhrase(paragraph1);
		table.addCell(pdfCell1);
		paragraph1 = new Paragraph(" ", font1);
		pdfCell1.setPhrase(paragraph1);
		table.addCell(pdfCell1);
		paragraph1 = new Paragraph(" ", font1);
		pdfCell1.setPhrase(paragraph1);
		table.addCell(pdfCell1);
		paragraph1 = new Paragraph(" ", font1);
		pdfCell1.setPhrase(paragraph1);
		table.addCell(pdfCell1);
		pdfCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		paragraph1 = new Paragraph(new DecimalFormat("###,###,###.##").format(kuchun), font1);
		pdfCell1.setPhrase(paragraph1);
		table.addCell(pdfCell1);
		paragraph1 = new Paragraph(" ", font1);
		pdfCell1.setPhrase(paragraph1);
		table.addCell(pdfCell1);
		paragraph1 = new Paragraph(new DecimalFormat("###,###,##0.00").format(mayang), font1);
		pdfCell1.setPhrase(paragraph1);
		table.addCell(pdfCell1);

		document.add(new Paragraph("\n\n\n\n\n"));
		document.add(table);
		document.add(new Paragraph("\n"));
		document.add(new Paragraph(
				"制表人（签字）：                                                                                                  审核人(签字):",
				font1));
		document.close();
		writer.close();
	}

}