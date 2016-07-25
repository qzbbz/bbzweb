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

	public static void generateContractPdf(String fileName, String companyName,  String code, String address, String owner,String amount, String range, String total, String location, String type, String date, String webRoot) throws IOException,DocumentException {

		
		




        BaseFont bf = BaseFont.createFont("/root/hxb-meixinti.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
       //BaseFont bf = BaseFont.createFont("/Users/veronica/Documents/hxb-meixinti.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        


        //String file = "/Users/veronica/Documents/contract2";
        String file = "/root/contract3";

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
        editList.add("paragraph4_sub2");
        editList.add("paragraph4_sub3");
        editList.add("paragraph4_sub4");
        editList.add("paragraph4_sub5");
        Map infoMap = new HashMap();
        infoMap.put("client", companyName);
        infoMap.put("registerCode", code);
        infoMap.put("legalAddress1", address);
        infoMap.put("legalRep", owner);
        infoMap.put("paragraph4_sub1", amount);
        infoMap.put("paragraph4_sub2", range);
        infoMap.put("paragraph4_sub3", total);
        infoMap.put("paragraph4_sub4", location);
        infoMap.put("paragraph4_sub5", type);
        
        


        try {
        		
                BaseFont bfChinese  = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                PdfWriter.getInstance(doc, new FileOutputStream(fileName));
                doc.open();
                Font titleFont = new Font(bfChinese, 20, Font.BOLD);
                Font bodyFont = new Font(bfChinese, 12, Font.NORMAL);
                Font bodyBoldFont = new Font(bfChinese, 12, Font.BOLD);
                Paragraph title = new Paragraph(properties.getProperty("title"), titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                doc.add(title);
                doc.add(new Paragraph("\r\n\n"));
                boolean hit = false;

                for(Object key:keys) {
                    Paragraph body = new Paragraph("", bodyFont);
                    Paragraph bodyB = new Paragraph("", bodyBoldFont);
                	String keyStr = key.toString();
                	if(keyStr.equals("title")  ||keyStr.equals("paragraph4_sub6") || keyStr.equals("paragraph4_sub7")){
                		
                		continue;
                	}
                	String paragraph="";
                	if (editList.contains(keyStr)) {
  
                		paragraph = "";

                		if(keyStr.contains("paragraph4_sub")) {
                			if(hit == false){
	                			paragraph += properties.getProperty("paragraph4_sub1") + infoMap.get("paragraph4_sub1");
	                			paragraph += properties.getProperty("paragraph4_sub2") + infoMap.get("paragraph4_sub2");
	                			paragraph += properties.getProperty("paragraph4_sub3") + infoMap.get("paragraph4_sub3");
	                			paragraph += properties.getProperty("paragraph4_sub4") + infoMap.get("paragraph4_sub4");
	                			paragraph += properties.getProperty("paragraph4_sub5") + infoMap.get("paragraph4_sub5");
	                			paragraph += properties.getProperty("paragraph4_sub6") + properties.getProperty("paragraph4_sub7"); 
	                			hit = true;
                			}
                			
                		}else{
                			
                			String information = (String) infoMap.get(keyStr);

                    		paragraph = properties.getProperty(keyStr) + information;
                		}
                	}
                	else {
                		if (keyStr.equals("footer1")){
                			paragraph = StringUtils.repeat(" ", 25) + properties.getProperty(keyStr) ;
                			
                		}
                		else if (keyStr.equals("footer2")) {
                			paragraph = "\r\n\n\n" + StringUtils.repeat(" ", 25) + properties.getProperty(keyStr) + date + StringUtils.repeat(" ", (60-(date.length()*2))) + properties.getProperty(keyStr) + date;
                		}
                		else{
                			paragraph = properties.getProperty(keyStr);
                		}
                	}
                	
                	if (keyStr.contains("_main")){
                		bodyB.add("\r\n" + paragraph + "\r\n");
                		doc.add(bodyB);
                	}else{
	               
	                		if (editList.contains(keyStr)|| keyStr.equals("legalAddress2")){
	                			body.add(paragraph+"\r\n");
	                		}
	                		else{
	                			body.add("\r\n" + paragraph + "\r\n");
	                		}
	                			
	                	
	                	doc.add(body);
                	}
                	
                }
               
                //doc.add(body);
                doc.add(new Paragraph());
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
		PdfProcess.generateContractPdf("/Users/veronica/Desktop/contract.pdf", "上海正叶电子科技有限公司", "91310106MA1FY1G028", "上海市市辖区黄浦区余姚路575号438室", "郑聂平","99","6","594","上海","小规模纳税人", "2016-06-05", null);
		System.out.println("");
	}

}
