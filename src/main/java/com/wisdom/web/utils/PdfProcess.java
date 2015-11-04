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
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
public class PdfProcess {

	private static final Logger logger = LoggerFactory.getLogger(PdfProcess.class);

	public static void generateContractPdf(String fileName, String companyName,  String code, String address, String owner,String amount, String date, String webRoot) throws IOException,DocumentException {

		
		
        PdfReader reader = new PdfReader(webRoot + "/img/webimg/contract.pdf"); // input PDF
        fileName = "/Users/veronica/Documents/test2.pdf";
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileName)); // output PDF

        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

        
        PdfContentByte content = stamper.getOverContent(1);
        content.beginText();
        content.setFontAndSize(bf, 12);
        content.setTextMatrix(175, 705);
        content.showText(companyName);
        content.setTextMatrix(141, 686);      
        content.showText(code);
        content.setTextMatrix(150, 667);       
        content.showText(address);
        content.setTextMatrix(163, 647);
        content.showText(owner);
        content.endText();
        content = stamper.getOverContent(3);
        content.beginText();
        content.setFontAndSize(bf, 12);
        content.setTextMatrix(315, 660);
        content.showText(amount);
        content.endText();
        content = stamper.getOverContent(4);
        content.beginText();
        content.setFontAndSize(bf, 12);
        content.setTextMatrix(125, 525);
        content.showText(date);
        content.setTextMatrix(350, 525);
        content.showText(date);
        content.endText();
        


        stamper.close();
	}

	public static void main(String[] args) throws DocumentException, IOException {
		PdfProcess.generateContractPdf(null, "company name", "company code", "company address", "company owner","12000", "2015-11-04", null);
		System.out.println("");
	}

}
