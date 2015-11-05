package com.wisdom.web.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
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

		
		



        //BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        BaseFont bf = BaseFont.createFont("/root/hxb-meixinti.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		//BaseFont bf = BaseFont.createFont("/Users/veronica/Downloads/hxb-meixinti.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        //String file = "/Users/veronica/Downloads/contract2";
        //fileName = "/Users/veronica/Documents/new.pdf";
        String file = "/root/contract2";

       // String 
        Properties properties = new OrderedProperties();
        Set<Object> keys = null;
        try {
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            properties.load(inputStreamReader);
           
            inputStream.close();


            System.out.println(properties.getProperty("client"));
         
            keys = properties.keySet();
           


        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        
        
        Document doc = new Document(PageSize.A4);
        List editList = new ArrayList();
        editList.add("client");
        editList.add("registerCode");
        editList.add("legalAddress1");
        editList.add("legalRep");
        editList.add("paragraph4_sub1");
        Map infoMap = new HashMap();
        infoMap.put("client", companyName);
        infoMap.put("registerCode", code);
        infoMap.put("legalAddress1", address);
        infoMap.put("legalRep", owner);
        infoMap.put("paragraph4_sub1", amount);
        


        try {
        		
                BaseFont bfChinese  = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                PdfWriter.getInstance(doc, new FileOutputStream(fileName));
                doc.open();
                Font titleFont = new Font(bfChinese, 20, Font.BOLD);
                Font bodyFont = new Font(bfChinese, 12, Font.NORMAL);
                Paragraph title = new Paragraph(properties.getProperty("title"), titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                doc.add(title);
                doc.add(new Paragraph("\r\n"));
                Paragraph body = new Paragraph("", bodyFont);
                for(Object key:keys) {
                	String keyStr = key.toString();
                	if(keyStr.equals("title")){
                		
                		continue;
                	}
                	String paragraph="";
                	if (editList.contains(keyStr)) {
                		String information = (String) infoMap.get(keyStr);

                		paragraph = properties.getProperty(keyStr) + information;
                	}
                	else {
                		if (keyStr.equals("footer1")){
                			paragraph = StringUtils.repeat(" ", 25) + properties.getProperty(keyStr) + StringUtils.repeat(" ", 70);
                			
                		}
                		else if (keyStr.equals("footer3")) {
                			paragraph = "\r\n\n\n" + StringUtils.repeat(" ", 25) + properties.getProperty(keyStr) + date + StringUtils.repeat(" ", (70-(date.length()*2))) + properties.getProperty(keyStr) + date;
                		}
                		else{
                			paragraph = properties.getProperty(keyStr);
                		}
                	}
                	if (keyStr.equals("paragraph4_sub1") || keyStr.equals("footer1")) {
                		body.add(paragraph);
                	}else{
                		if (editList.contains(keyStr)|| keyStr.contains("footer")||keyStr.equals("paragraph4_sub2")){
                			body.add(paragraph+"\r\n");
                		}
                		else{
                			body.add("\r\n" + paragraph + "\r\n");
                		}
                			
                	}
                	
                }
               
                doc.add(body);
                doc.add(new Paragraph("\r\n"));
                doc.close();
        } catch (FileNotFoundException e) {
                System.out.println(e.toString());
        } catch (DocumentException e) {
                System.out.println(e.toString());
        } catch (IOException e) {
                System.out.println(e.toString());
        }
	}

	public static void main(String[] args) throws DocumentException, IOException {
		PdfProcess.generateContractPdf(null, "上海元升财务咨询有限公司", "xxxxx", "上海市国定东路200号", "蒋中植","12000", "2015-11-04", null);
		System.out.println("");
	}

}
