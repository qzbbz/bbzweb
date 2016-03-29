package com.xm.pdftoexcel;

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

public class PDFToExcelBalance2 {
    private static String organizationUnit;
    private static String dateTime;
    private   ArrayList<LineContentModel> list = new ArrayList<>();
    private static  String PDFPATH = "";
    private static  String[] pdfString;
    public static void main(String[] args) throws IOException {  
        List<String> newList = setPath();
        PDFToExcelBalance2 pte = new PDFToExcelBalance2();
        for (String path : newList) {
            if ((path.indexOf("资产负债表.pdf")) != -1) {
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
               System.out.println("Finish" + PDFPATH);
               PDFPATH = "";
               pte.list.clear();
             
            }
        }
       }  
    private static List<String> setPath() {
        ArrayList<String> fileNewPath = new ArrayList<>();
        try {
            Files.walk(Paths.get("D:/work/表3")).forEach(filePath -> {
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
        organizationUnit = s[0] + "   "+ s[1];
        dateTime = s[2] + " " + s[3];
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
        for (int i = 6; i < pdfString.length; i ++) {//从编制单位开始
          String[] lineString =  pdfString[i].split(" ");
          List<LineContentModel> modelList = setExcelTitleModelContent(lineString);
          for (LineContentModel model : modelList) {
              list.add(model);
          }
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
    private List<LineContentModel> setExcelTitleModelContent(String[] lineString ) {
        if (lineString.length == 4 && (lineString[0].indexOf("非流动资产合计")) != -1) {
            return getLineContentSpecialContents294(lineString);
         } 
        else if ((lineString[0].indexOf("非流动资产合计")) != -1 ) {
            return getLineContentModelSevenContents(lineString);
        }else  if (lineString.length == 7 && (lineString[0].indexOf("其他非流动资产")) != -1) {
            return getLineContentModelSevenContents(lineString);
        } else if (lineString.length == 7 && (lineString[0].indexOf("非流动资产")) != -1) {
           return getLineContentSpecialSevenContents(lineString);
        }else if ((lineString.length == 3) && (lineString[0].indexOf("非流动资产")) != -1) {
           return getLineContentSpecialThreeContents(lineString);
        }
        else if (lineString.length == 7) { //金额和描述一起的
           return getLineContentModelSevenContents(lineString);
        } 
        else if (lineString.length == 4) { // 没有金额
           return  getLineContentModelFourContents(lineString);
        } else if (lineString.length == 8) { // 金额和描述没有一起的
            return  getLineContentModelEightContents(lineString);
         } 
        else if (lineString.length == 3) {
             return getLineContentModelThreeContents(lineString);
         } else if (lineString.length == 2) {
             return getLineContentModelTwoContents(lineString);
         } else if (lineString.length == 6 &&( lineString[0].equals("非流动资产"))) {
             return getLineContentModelSpecificContents1(lineString);
         }  else if (lineString.length == 6 &&( lineString[0].equals("生产性生物资产"))) {
             return getLineContentModelSpecificContents246(lineString);
         }  else if (lineString.length == 3 &&( lineString[0].equals("生产性生物资产"))) {
             return getLineContentModelSpecificContents243(lineString);
         }  else if (lineString.length == 4 &&( lineString[0].equals("生产性生物资产"))) {
             return getLineContentModelSpecificContents244(lineString);
         }  else if (lineString.length == 5 &&( lineString[0].equals("生产性生物资产"))) {
             return getLineContentModelSpecificContents245(lineString);
         }  
         else if (lineString.length == 6 &&( lineString[3].indexOf("非流动负债")) != -1) {
             return getLineContentModelSpecificContents(lineString);
         }  
         else if (lineString.length == 6) {
             return getLineContentModelSixContents(lineString);
         } 
        return null;
    }
    private List<LineContentModel> getLineContentSpecialContents294(String[] lineString) {
        List<LineContentModel> listModel = new ArrayList<>();
        LineContentModel lcm = new LineContentModel();
        lcm.setColumnOneContent(lineString[0]); //内容
        lcm.setColumnTwoContent(lineString[1]);
        listModel.add(lcm);
        LineContentModel lcm2 = new LineContentModel();
        lcm2.setColumnOneContent(lineString[2]); //内容
        lcm2.setColumnTwoContent(lineString[3]);
        listModel.add(lcm2);
        return listModel;
    }
    //--------------------------------------------------------
    private List<LineContentModel> getLineContentModelSpecificContents246(String[] lineString) {
        String price = "";
        String description = "";
        if(lineString.length == 6) {
             price = lineString[3].substring(0,  lineString[3].indexOf(".") + 3);
             description = lineString[3].substring(lineString[3].indexOf(".") + 3,  lineString[3].length());
        }
        List<LineContentModel> listModel = new ArrayList<>();
        LineContentModel lcm = new LineContentModel();
        lcm.setColumnOneContent(lineString[0]); //内容
        lcm.setColumnTwoContent(lineString[1]);
        lcm.setColumnThreeContent(lineString[2]);
        lcm.setColumnFourContent(price);
        listModel.add(lcm);
        LineContentModel lcm2 = new LineContentModel();
        lcm2.setColumnOneContent(description); //内容
        lcm2.setColumnThreeContent(lineString[4]); //
        lcm2.setColumnFourContent(lineString[5]);
        listModel.add(lcm2);
        return listModel;
    }
    private List<LineContentModel> getLineContentModelSpecificContents243(String[] lineString) {
        List<LineContentModel> listModel = new ArrayList<>();
        LineContentModel lcm = new LineContentModel();
        lcm.setColumnOneContent(lineString[0]); //内容
        lcm.setColumnTwoContent(lineString[1]);
        listModel.add(lcm);
        LineContentModel lcm2 = new LineContentModel();
        lcm2.setColumnOneContent(lineString[2]); //内容
        listModel.add(lcm2);
        return listModel;
    }
    private List<LineContentModel> getLineContentModelSpecificContents244(String[] lineString) {
        String price = "";
        String description = "";
        price = lineString[3].substring(0,  lineString[3].indexOf(".") + 3);
        description = lineString[3].substring(lineString[3].indexOf(".") + 3,  lineString[3].length());
        List<LineContentModel> listModel = new ArrayList<>();
        LineContentModel lcm = new LineContentModel();
        lcm.setColumnOneContent(lineString[0]); //内容
        lcm.setColumnTwoContent(lineString[1]);
        lcm.setColumnThreeContent(lineString[2]);
        lcm.setColumnFourContent(price);
        listModel.add(lcm);
        LineContentModel lcm2 = new LineContentModel();
        lcm2.setColumnOneContent(description); //内容
        listModel.add(lcm2);
        return listModel;
    }
    private List<LineContentModel> getLineContentModelSpecificContents245(String[] lineString) {
        List<LineContentModel> listModel = new ArrayList<>();
        LineContentModel lcm = new LineContentModel();
        lcm.setColumnOneContent(lineString[0]); //内容
        lcm.setColumnTwoContent(lineString[1]);
        listModel.add(lcm);
        LineContentModel lcm2 = new LineContentModel();
        lcm2.setColumnOneContent(lineString[2]); //内容
        lcm2.setColumnThreeContent(lineString[3]); //
        lcm2.setColumnFourContent(lineString[4]);
        listModel.add(lcm2);
        return listModel;
    }
    //-------------------------------------------------------------------
    private List<LineContentModel> getLineContentModelSpecificContents1(String[] lineString) {
        String price = "";
        String description = "";
        if(lineString.length == 6) {
             price = lineString[2].substring(0,  lineString[2].indexOf(".") + 3);
             description = lineString[2].substring(lineString[2].indexOf(".") + 3,  lineString[2].length());
        }
        List<LineContentModel> listModel = new ArrayList<>();
        LineContentModel lcm = new LineContentModel();
        lcm.setColumnOneContent(lineString[0]); //内容
        lcm.setColumnThreeContent(lineString[1]);
        lcm.setColumnFourContent(price);
        listModel.add(lcm);
        LineContentModel lcm2 = new LineContentModel();
        lcm2.setColumnOneContent(description); //内容
        lcm2.setColumnTwoContent(lineString[3]);
        lcm2.setColumnThreeContent(lineString[4]); //
        lcm2.setColumnFourContent(lineString[5]);
        listModel.add(lcm2);
        return listModel;
    }
   
    private List<LineContentModel> getLineContentModelSpecificContents(String[] lineString) {
        String price = "";
        String description = "";
        if(lineString.length == 6) {
             price = lineString[3].substring(0,  lineString[3].indexOf(".") + 3);
             description = lineString[3].substring(lineString[3].indexOf(".") + 3,  lineString[3].length());
        }
        List<LineContentModel> listModel = new ArrayList<>();
        LineContentModel lcm = new LineContentModel();
        lcm.setColumnOneContent(lineString[0]); //内容
        lcm.setColumnTwoContent(lineString[1]); //行次
        lcm.setColumnThreeContent(lineString[2]);
        lcm.setColumnFourContent(price);
        listModel.add(lcm);
        LineContentModel lcm2 = new LineContentModel();
        lcm2.setColumnOneContent(description); //内容
        lcm2.setColumnThreeContent(lineString[4]); //
        lcm2.setColumnFourContent(lineString[5]);
        listModel.add(lcm2);
        return listModel;
    }

    private List<LineContentModel> getLineContentModelSevenContents(String[] lineString) {
        String price = "";
        String description = "";
        if(lineString.length == 7) {
             price = lineString[3].substring(0,  lineString[3].indexOf(".") + 3);
             description = lineString[3].substring(lineString[3].indexOf(".") + 3,  lineString[3].length());
        }
        List<LineContentModel> listModel = new ArrayList<>();
        LineContentModel lcm = new LineContentModel();
        lcm.setColumnOneContent(lineString[0]); //内容
        lcm.setColumnTwoContent(lineString[1]); //行次
        lcm.setColumnThreeContent(lineString[2]); //期末金额
        lcm.setColumnFourContent(price);//年初余额
        listModel.add(lcm);
        LineContentModel lcm2 = new LineContentModel();
        lcm2.setColumnOneContent(description); //内容
        lcm2.setColumnTwoContent(lineString[4]); //行次
        lcm2.setColumnThreeContent(lineString[5]); //期末金额
        lcm2.setColumnFourContent(lineString[6]);//年初余额
        listModel.add(lcm2);
        return listModel;
    }
    private List<LineContentModel> getLineContentModelFourContents(String[] lineString) {
        List<LineContentModel> listModel = new ArrayList<>();
        LineContentModel lcm = new LineContentModel();
        lcm.setColumnOneContent(lineString[0]); //内容
        lcm.setColumnTwoContent(lineString[1]); //行次
        listModel.add(lcm);
        LineContentModel lcm2 = new LineContentModel();
        lcm2.setColumnOneContent(lineString[2]); //内容
        lcm2.setColumnTwoContent(lineString[3]); //行次
        listModel.add(lcm2);
        return listModel;
    }
    private List<LineContentModel> getLineContentModelTwoContents(String[] lineString) {
        List<LineContentModel> listModel = new ArrayList<>();
        LineContentModel lcm = new LineContentModel();
        lcm.setColumnOneContent(lineString[0]); //内容
        lcm.setColumnTwoContent(lineString[1]); //行次
        listModel.add(lcm);
        return listModel;
    }
    private List<LineContentModel> getLineContentModelSixContents(String[] lineString) {
        List<LineContentModel> listModel = new ArrayList<>();
        LineContentModel lcm = new LineContentModel();
        lcm.setColumnOneContent(lineString[0]); //内容
        lcm.setColumnTwoContent(lineString[1]); //行次
        lcm.setColumnThreeContent(lineString[2]);
        lcm.setColumnFourContent(lineString[3]);
        listModel.add(lcm);
        return listModel;
    }
    private List<LineContentModel> getLineContentModelThreeContents(String[] lineString) {
        List<LineContentModel> listModel = new ArrayList<>();
        LineContentModel lcm = new LineContentModel();
        lcm.setColumnOneContent(lineString[0]); //内容
        lcm.setColumnTwoContent(lineString[1]); //行次
        listModel.add(lcm);
        LineContentModel lcm2 = new LineContentModel();
        lcm2.setColumnOneContent(lineString[2]); //内容
        listModel.add(lcm2);
        return listModel;
    }
    private List<LineContentModel> getLineContentSpecialThreeContents(String[] lineString) {
        List<LineContentModel> listModel = new ArrayList<>();
        LineContentModel lcm = new LineContentModel();
        lcm.setColumnOneContent(lineString[0]); //内容
        listModel.add(lcm);
        LineContentModel lcm2 = new LineContentModel();
        lcm2.setColumnOneContent(lineString[1]);
        lcm2.setColumnTwoContent(lineString[2]); //内容
        listModel.add(lcm2);
        return listModel;
    }
    
    private List<LineContentModel> getLineContentSpecialSevenContents(String[] lineString) {
        List<LineContentModel> listModel = new ArrayList<>();
        LineContentModel lcm = new LineContentModel();
        lcm.setColumnOneContent(lineString[0]); //内容
        lcm.setColumnThreeContent(lineString[1]);
        lcm.setColumnFourContent(lineString[2]);
        listModel.add(lcm);
        LineContentModel lcm2 = new LineContentModel();
        lcm2.setColumnOneContent(lineString[3]);
        lcm2.setColumnOneContent(lineString[4]); //内容
        lcm2.setColumnThreeContent(lineString[5]);
        lcm2.setColumnFourContent(lineString[6]);
        listModel.add(lcm2);
        return listModel;
    }
    private List<LineContentModel> getLineContentModelEightContents(String[] lineString) {
        List<LineContentModel> listModel = new ArrayList<>();
        LineContentModel lcm = new LineContentModel();
        lcm.setColumnOneContent(lineString[0]); //内容
        lcm.setColumnTwoContent(lineString[1]); //行次
        lcm.setColumnThreeContent(lineString[2]); //期末金额
        lcm.setColumnFourContent(lineString[3]);//年初余额
        listModel.add(lcm);
        LineContentModel lcm2 = new LineContentModel();
        lcm2.setColumnOneContent(lineString[4]); //内容
        lcm2.setColumnTwoContent(lineString[5]); //行次
        lcm2.setColumnThreeContent(lineString[6]); //期末金额
        lcm2.setColumnFourContent(lineString[7]);//年初余额
        listModel.add(lcm2);
        return listModel;
    }
    public void generateIncomeExcel(OutputStream os) throws IOException, RowsExceededException, WriteException {
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("利润表",0);
        //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
        Label title1 = new Label(1,0,"资产负债表");
        sheet.addCell(title1);
        Label title2 = new Label(1,1,"(小企业会计准则适用)");
        sheet.addCell(title2);
        Label title3 = new Label(8,2,"会计01表");
        sheet.addCell(title3);
        Label title4 = new Label(0,3,organizationUnit);
        sheet.addCell(title4);
        Label title5 = new Label(1,3,dateTime);
        sheet.addCell(title5);
        Label title6 = new Label(8,3,"单位：元");
        sheet.addCell(title6);
        Label xiangmu = new Label(0,4,"资产");
        sheet.addCell(xiangmu);
        Label line = new Label(1,4,"行次");
        sheet.addCell(line);
        Label money = new Label(2,4,"期末余额");
        sheet.addCell(money);
        Label total = new Label(3,4,"年初余额");
        sheet.addCell(total);
        Label total1 = new Label(4,4,"负债和所有者权益（或股东权益）");
        sheet.addCell(total1);
        Label total2 = new Label(5,4,"行次");
        sheet.addCell(total2);
        Label total3 = new Label(6,4,"期末余额");
        sheet.addCell(total3);
        Label total4= new Label(7,4,"年初余额");
        sheet.addCell(total4);
        Label total5= new Label(0,5,"流动资产");
        sheet.addCell(total5);
        Label total6= new Label(4,5,"流动负债");
        sheet.addCell(total6);
        int lineNum = 6;
        System.out.println("List Size : " + list.size());
        for (int i = 0; i < list.size();) {
            Label content = new Label(0,lineNum, list.get(i).getColumnOneContent());
            sheet.addCell(content);
            if (list.get(i).getColumnTwoContent() != null) {
                Label two = new Label(1,lineNum, list.get(i).getColumnTwoContent());
                sheet.addCell(two);
            }
            if ( list.get(i).getColumnThreeContent() != null) {
                Label three = new Label(2,lineNum, list.get(i).getColumnThreeContent());
                sheet.addCell(three);
            }
            if (list.get(i).getColumnFourContent() != null) {
                Label four = new Label(3,lineNum,  list.get(i).getColumnFourContent());
                sheet.addCell(four);
            }
            if (list.size() == 55) {
                if (lineNum >=24 && lineNum <= 30 ) {
                    i =  i + 1;
                 } else {
                     if (list.get(i + 1).getColumnOneContent() != null) {
                         Label five = new Label(4,lineNum, list.get(i+1).getColumnOneContent());
                         sheet.addCell(five);
                     }
                     if (list.get(i + 1).getColumnTwoContent() != null) {
                         Label six = new Label(5,lineNum, list.get(i + 1).getColumnTwoContent());
                         sheet.addCell(six);
                     }
                     if ( list.get(i + 1 ).getColumnThreeContent() != null) {
                         Label seven = new Label(6,lineNum, list.get(i + 1).getColumnThreeContent());
                         sheet.addCell(seven);
                     }
                     if (list.get(i + 1).getColumnFourContent() != null) {
                         Label eight = new Label(7,lineNum,  list.get(i + 1).getColumnFourContent());
                         sheet.addCell(eight);
                     }
                     i = i + 2;
                 }
            } else {
                if (lineNum >=24 && lineNum <= 29 ) {
                    i =  i + 1;
                 } else {
                     if (list.get(i + 1).getColumnOneContent() != null) {
                         Label five = new Label(4,lineNum, list.get(i+1).getColumnOneContent());
                         sheet.addCell(five);
                     }
                     if (list.get(i + 1).getColumnTwoContent() != null) {
                         Label six = new Label(5,lineNum, list.get(i + 1).getColumnTwoContent());
                         sheet.addCell(six);
                     }
                     if ( list.get(i + 1 ).getColumnThreeContent() != null) {
                         Label seven = new Label(6,lineNum, list.get(i + 1).getColumnThreeContent());
                         sheet.addCell(seven);
                     }
                     if (list.get(i + 1).getColumnFourContent() != null) {
                         Label eight = new Label(7,lineNum,  list.get(i + 1).getColumnFourContent());
                         sheet.addCell(eight);
                     }
                     i = i + 2;
                 }
            }
            lineNum++;
    }
        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
    }
}
