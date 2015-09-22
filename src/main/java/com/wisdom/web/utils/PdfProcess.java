package com.wisdom.web.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfProcess {

	private static final Logger logger = LoggerFactory.getLogger(PdfProcess.class);

	public static void generateContractPdf(String fileName, String companyName, String amount, String date) {

		Document doc = new Document(PageSize.A4);
		try {
			BaseFont bfChinese  = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			PdfWriter.getInstance(doc, new FileOutputStream(fileName));
			doc.open();
			Font titleFont = new Font(bfChinese, 20, Font.BOLD);
			Font bodyFont = new Font(bfChinese, 15, Font.NORMAL);
			Paragraph title = new Paragraph("上海元升财务咨询", titleFont);
			title.setAlignment(Element.ALIGN_CENTER);
			doc.add(title);
			doc.add(new Paragraph("\r\n"));
			Paragraph body = new Paragraph("欢迎您签约上海元升财务咨询有限公司迎您签约上海元升财务咨询有限公司迎您签约上海元升财务咨询有限公司迎您签约上海元升财务咨询有限公司迎您签约上海元升财务咨询有限公司迎您签约上海元升财务咨询有限公司迎您签约上海元升财务咨询有限公司", bodyFont);
			body.setFirstLineIndent(30); 
			doc.add(body);
			doc.add(new Paragraph("\r\n"));
			doc.close();
		} catch (FileNotFoundException e) {
			logger.error(e.toString());
			System.out.println(e.toString());
		} catch (DocumentException e) {
			logger.error(e.toString());
			System.out.println(e.toString());
		} catch (IOException e) {
			logger.error(e.toString());
			System.out.println(e.toString());
		}
	}

	public static void main(String[] args) throws DocumentException, IOException {
		PdfProcess.generateContractPdf(null, null, null, null);
		System.out.println("");
	}

}
