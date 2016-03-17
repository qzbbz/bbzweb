package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.test.model.ExcelTitleModel;
import com.test.model.LineContentModel;
import com.test.model.PDFUtils;
import com.wisdom.common.model.SheetBalance;
import com.wisdom.common.model.SheetIncome;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class PDFToExcelInCome2 {
    private static String organizationUnit;
    private static String dateTime;
    private   List<LineContentModel> list = new ArrayList<>();
    private static  String PDFPATH = "";
    private static  String[] pdfString;
    public static void main(String[] args) throws IOException {  
        List<String> newList = setPath();
        PDFToExcelInCome2 pte = new PDFToExcelInCome2();
        for (String path : newList) {
            if ((path.indexOf("利润表.pdf")) != -1) {
                PDFPATH = path;
            }
            if (!PDFPATH .equals("") ) {
                String txtString = null;
                try {
                    txtString = pte.getPdfFileText(PDFPATH);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pdfString = txtString.split("\n");
                setModelTitle(pdfString);
               pte.setModelValue(pdfString);
               PDFPATH = "";
               pte.list.clear();
            }
        }
       }  
    private static List<String> setPath() {
        ArrayList<String> fileNewPath = new ArrayList<>();
        try {
            Files.walk(Paths.get("D:/work/新建文件夹 - 副本 (2)")).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                   fileNewPath.add(filePath.toString());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileNewPath;
    }
    private static void setModelTitle(String[] txtString) {
        String[] s = txtString[3].split(" ");
        organizationUnit = s[0] + " : "+ s[1];
        dateTime = s[2] + "：" + s[3];
    }
    private  String getPdfFileText(String fileName) throws IOException {  
        PdfReader reader = new PdfReader(fileName);  
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);  
        StringBuffer buff = new StringBuffer();  
        TextExtractionStrategy strategy;  
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {  
         strategy = parser.processContent(i,  
           new SimpleTextExtractionStrategy());  
         buff.append(strategy.getResultantText());  
        }  
        return buff.toString();  
       }  
       
    private void setModelValue(String[] pdfString) {
        for (int i = 5; i < pdfString.length; i ++) {//从编制单位开始
          String[] lineString =  pdfString[i].split(" ");
          list.add(setExcelTitleModelIncomeContent(lineString));
        }
        try {
            File file = new File(PDFPATH.substring(0,PDFPATH.length()-3) + "xls");
            OutputStream out = null ; // 准备好一个输出的对象
            out = new FileOutputStream(file);  
            generateIncomeExcel(out);
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private LineContentModel setExcelTitleModelIncomeContent(String[] lineString ) {
        if (lineString.length == 3) { //后面又金额
           return getLineContentModelInComeThreeContents(lineString);
        } else if (lineString.length == 2) { // 没有金额
           return  getLineContentModelInComeTwoContents(lineString);
        }
        return null;
    }
    

    private LineContentModel getLineContentModelInComeThreeContents(String[] lineString) {
        LineContentModel lcm = new LineContentModel();
        lcm.setColumnOneContent(lineString[0]); //内容
        lcm.setColumnTwoContent(lineString[1]); //行次
        lcm.setColumnFourContent(lineString[2]); //金额
        return lcm;
    }
    private LineContentModel getLineContentModelInComeTwoContents(String[] lineString) {
        LineContentModel lcm = new LineContentModel();
        lcm.setColumnOneContent(lineString[0]); //内容
        lcm.setColumnTwoContent(lineString[1]); //行次
        return lcm;
    }
    public void generateIncomeExcel(OutputStream os) throws IOException, RowsExceededException, WriteException {
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("利润表",0);
        //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
        Label title1 = new Label(1,0,"利润表");
        sheet.addCell(title1);
        Label title2 = new Label(1,1,"(小企业会计准则适用)");
        sheet.addCell(title2);
        Label title3 = new Label(3,2,"会计02表");
        sheet.addCell(title3);
        Label title4 = new Label(0,3,organizationUnit);
        sheet.addCell(title4);
        Label title5 = new Label(1,3,dateTime);
        sheet.addCell(title5);
        Label title6 = new Label(3,3,"单位：元");
        sheet.addCell(title6);
        Label xiangmu = new Label(0,4,"项目");
        sheet.addCell(xiangmu);
        Label line = new Label(1,4,"行次");
        sheet.addCell(line);
        Label money = new Label(2,4,"本月金额");
        sheet.addCell(money);
        Label total = new Label(3,4,"本年累计金额");
        sheet.addCell(total);
        int lineNum = 5;
        for (LineContentModel lcm : list) {
                Label content = new Label(0,lineNum, lcm.getColumnOneContent());
                sheet.addCell(content);
                Label two = new Label(1,lineNum, lcm.getColumnTwoContent());
                sheet.addCell(two);
                if (lcm.getColumnFourContent() != null) {
                    Label three = new Label(3,lineNum, lcm.getColumnFourContent());
                    sheet.addCell(three);
                }
                lineNum++;
     
        }
        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
    }
}
