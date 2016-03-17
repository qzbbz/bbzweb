package com.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

public class Test {

    public static void main(String[] args) throws IOException, DocumentException {  
//        Path startingDir = Paths.get("D:\\work");  
//          //遍历目录  
//          Files.walkFileTree(startingDir, new FindJavaVisitor());  
//        }  
//          
//        private static class FindJavaVisitor extends SimpleFileVisitor<Path> {  
//          
//          @Override  
//          public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {  
//          
//            if (file.toString().endsWith(".txt")) {  
//              System.out.println(file.getFileName());  
//            }  
//            return FileVisitResult.CONTINUE;  
//          }  
        
//      //Step 1—Create a Document.  
//        Document document = new Document();  
//        //Step 2—Get a PdfWriter instance.  
//        PdfWriter.getInstance(document, new FileOutputStream("D:/work/" + "createSamplePDF.pdf"));  
//        //Step 3—Open the Document.  
//        document.open();  
//        //Step 4—Add content.  
//        document.add(new Paragraph("Hello World"));  
//        //Step 5—Close the Document.  
//        document.close(); 
//        System.out.println("---");
        
      //页面大小  
        Rectangle rect = new Rectangle(PageSize.B5.rotate());  
        //页面背景色  
        rect.setBackgroundColor(BaseColor.ORANGE);  
          
        Document doc = new Document(rect);  
          
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("D:/work/" + "SamplePDF.pdf"));  
          
        //PDF版本(默认1.4)  
        writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);  
          
        //文档属性  
        doc.addTitle("Title@sample");  
        doc.addAuthor("Author@XiaoMing");  
        doc.addSubject("Subject@iText sample");  
        doc.addKeywords("Keywords@iText");  
        doc.addCreator("Creator@iText");  
          
        //页边空白  
        doc.setMargins(10, 20, 30, 40);  
          
        doc.open();  
        doc.add(new Paragraph("Hello World"));  
        doc.close();
        }  
}
