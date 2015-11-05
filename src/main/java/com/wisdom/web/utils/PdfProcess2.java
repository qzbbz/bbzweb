package com.wisdom.web.utils;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
public class PdfProcess2 {


        public static void generateContractPdf(String fileName, String companyName,  String code, String address, String owner,String amount, String date, String webRoot) throws IOException,DocumentException {


        webRoot = "/Users/veronica/Documents";
        fileName = "/Users/veronica/Documents/test3.pdf";
        PdfReader reader = new PdfReader(webRoot + "/test.pdf"); // input PDF

        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileName)); // output PDF

        //BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        //BaseFont bf = BaseFont.createFont("/root/hxb-meixinti.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
          BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
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
                System.out.println("hello");
                PdfProcess2.generateContractPdf(null, "company name", "xxxxx", "address", "owner","12000", "2015-11-04", null);
                System.out.println("");
        }

}