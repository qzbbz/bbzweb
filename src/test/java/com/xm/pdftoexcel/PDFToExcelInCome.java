package com.xm.pdftoexcel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.xm.pdftoexcel.model.InComeModel;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * <p>Title: PDFToExcelInCome</p>
 * <p>Description:  </p>
 *  @author XiaoMing
 * 	 @date 2016年3月16日下午3:20:04
 */
public class PDFToExcelInCome {
    private static final String PATH = "D:/work/表2";
    /**
     * This is PDF path
     */
    private static  String FILEPath = "";
    /**
     * This is a fabrication name
     */
    private String fabricationName = "";
    
    /**
     * fabrication date
     */
    private String fabricationDate = "";

    /**
     * @param content
     * @return String[] --> array
     * @author XiaoMing
     * @date 2016年3月16日 下午3:29:11
     */
    private String[] getCutOutArrayByLineFeed(String content) {
        String[] contentArrays = null;
        if (content != null) {
            contentArrays = content.split("\n");
        }
        return contentArrays.length > 0 ? contentArrays : null;
    }
    
    /**
     * @param content
     * @return boolean
     * @author XiaoMing
     * @date 2016年3月16日 下午3:37:20
     * This method is set Excel title eg: 编制单位 && 所属期
     */
    private boolean setTitle(String[] content) {
        if (((content[3].indexOf("编制单位")) != -1) && ((content[3].indexOf("所属期")) != -1)){
            String[] titleArray = content[3].split(" ");
            fabricationName = titleArray[0] + " : "+ titleArray[1];
            fabricationDate =  titleArray[2] + " : " + titleArray[3];
        }
        if (!fabricationName.equals("") && !fabricationDate.equals("")) {
            return true;
        }
        return false;
    }
    /**
     * @param content
     * @return InComeModel
     * @author XiaoMing
     * @date 2016年3月16日 下午3:51:14
     * Set model By String array (PDF every line content)
     */
    private InComeModel getModelByBlank(String[] content) {
        if (content != null) {
            InComeModel income = new InComeModel();
                if (content.length == 3) {
                    income.setProjectName(content[0]); //Set project
                    income.setLineNumber(content[1]); // Set line number
                    income.setTotalMoney(content[2]); // Set total money
                } else if (content.length == 2) {
                    income.setProjectName(content[0]); 
                    income.setLineNumber(content[1]); 
                }
           return  income != null ? income : null;
        }
        return null;
    }
    /**
     * @param absolutePath  <is a file absolute path>
     * @return String
     * @throws IOException
     * @author XiaoMing
     * @date 2016年3月16日 下午3:20:18
     *  This is IText jar methods
     */
    private  String getPdfFileText(String absolutePath) throws IOException {  
        PdfReader reader = new PdfReader(absolutePath);  
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
    /**
     * @param os
     * @param list
     * @throws IOException
     * @throws WriteException
     * @author XiaoMing
     * @date 2016年3月16日 下午4:03:27
     */
    private void generateExcel(OutputStream os, List<InComeModel> list) throws IOException, WriteException {
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("利润表",0);
        setExcelLabel(sheet); //invoke set Excel label method
        setExcelContent(sheet, list); //incoke set Excel content (for circulation)
        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
    }
    /**
     * @param sheet
     * @param list
     * @throws RowsExceededException
     * @throws WriteException
     * @throws IOException
     * @author XiaoMing
     * @date 2016年3月16日 下午4:03:17
     */
    private void setExcelContent(WritableSheet sheet, List<InComeModel> list) throws RowsExceededException, WriteException, IOException {
        //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
        int lineNum = 5;
        for (InComeModel lcm : list) {
                Label content = new Label(0,lineNum, lcm.getProjectName());
                sheet.addCell(content);
                Label two = new Label(1,lineNum, lcm.getLineNumber());
                sheet.addCell(two);
                Label three = new Label(3,lineNum, lcm.getTotalMoney() != null ? lcm.getTotalMoney() : "");
                sheet.addCell(three);
                lineNum++;
        }
    }
    /**
     * @param sheet
     * @throws RowsExceededException
     * @throws WriteException
     * @author XiaoMing
     * @date 2016年3月16日 下午4:03:21
     */
    private void setExcelLabel(WritableSheet sheet) throws RowsExceededException, WriteException {
        Label title1 = new Label(1,0,"利润表");
        sheet.addCell(title1);
        Label title2 = new Label(1,1,"(小企业会计准则适用)");
        sheet.addCell(title2);
        Label title3 = new Label(3,2,"会计02表");
        sheet.addCell(title3);
        Label title4 = new Label(0,3,fabricationName);
        sheet.addCell(title4);
        Label title5 = new Label(1,3,fabricationDate);
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
    }
    
    /**
     * @param args
     * @author XiaoMing
     * @date 2016年3月16日 下午3:23:40
     */
    public static void main(String[] args) {
        PDFToExcelInCome excelInCome = new PDFToExcelInCome();
        excelInCome.startGenerateExcel();
    }
    /**
     * @return List<String>
     * @author XiaoMing
     * @date 2016年3月16日 下午4:29:38
     */
    private static List<String> setPath() {
        ArrayList<String> fileNewPath = new ArrayList<>();
        try {
            Files.walk(Paths.get(PATH)).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                   fileNewPath.add(filePath.toString());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileNewPath;
    }
    /**
     * @author XiaoMing
     * @date 2016年3月16日 下午4:29:52
     */
    private void startGenerateExcel() {
        PDFToExcelInCome excelInCome = new PDFToExcelInCome();
        List<String> pathList = setPath();
        try {
            for (String s : pathList) {
                if ((s.indexOf("利润表.pdf")) != -1) {
                    FILEPath = s;
                }
                if (!FILEPath .equals("") ) {
                    ArrayList<InComeModel> list = new ArrayList<>();
                    String pdfContent = excelInCome.getPdfFileText(s);
                    String[] contentArrays = excelInCome.getCutOutArrayByLineFeed(pdfContent);
                    excelInCome.setTitle(contentArrays);
                    for (int i = 5; i < contentArrays.length; i ++) {
                        String[] arrays = contentArrays[i].split(" ");
                        list.add(excelInCome.getModelByBlank(arrays));
                    }
                    OutputStream os = new FileOutputStream(FILEPath.substring(0,FILEPath.length()-3) + "xls");
                    excelInCome.generateExcel(os, list);
                    System.out.println("Finish : " + FILEPath);
                    FILEPath = "";
                }
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}