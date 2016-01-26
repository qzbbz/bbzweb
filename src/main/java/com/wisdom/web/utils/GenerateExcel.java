package com.wisdom.web.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.wisdom.common.model.SheetBalance;
import com.wisdom.common.model.SheetCash;
import com.wisdom.common.model.SheetIncome;

public class GenerateExcel {
   private  HSSFWorkbook workbook = null;
   private final static String PTAH ="/home/excelfile";
   private final static String EXCEL_PATH="/home/excelfile/Excel_Temple_download.xls";
   private SheetBalance sheetBalance ;
   private SheetIncome sheetIncome;
   private SheetCash sheetCash;
   public void instantiation(Map<String, Object> map) {
       if(map.size() == 3) {
           sheetBalance = (SheetBalance) map.get("sheetBalance");
           sheetIncome = (SheetIncome) map.get("sheetIncome");
           sheetCash = (SheetCash) map.get("sheetCash");
       }
   }
    public String generateSheetBalanceExcel() {
        try {
            String[] sheetNames =new String[]{"资产负债表", "利润表", "现金流量表"};
            workbook = new HSSFWorkbook(new FileInputStream(GenerateExcel.PTAH+"/Excel_Temple.xls"));
            for (String sheetName : sheetNames) {
                HSSFSheet sheet = workbook.getSheet(sheetName);
                List<List<String>>list = readingConfigurationFile(judgePath(sheetName));
                Map<String,List<String>> map = getMethodNameMap(list);
                for(int i = 6; i < sheet.getLastRowNum(); i++) {
                    HSSFRow row = sheet.getRow((short) i);
                    if (null == row) {
                        continue;
                    } else {
                        if (sheetName.equals("资产负债表")) {
                            setExcelBalanceCellValue(row, map);
                        } else if (sheetName.equals("利润表")) {
                            setExcelIncomeCellValue(row, map);
                        } else if (sheetName.equals("现金流量表")) {
                            setExcelCashCellValue(row, map);
                        }
                }
            }
           }
           
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(EXCEL_PATH);
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(workbook != null) {
                    workbook.close();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return EXCEL_PATH;
   }
    /**
     * Set value at sheet_income or sheet_cash excel
     * @param row
     * @param map
     * @author Xiaoming
     * @date 2016年1月24日 下午10:53:40
     */
    public void setExcelCashCellValue( HSSFRow row ,  Map<String,List<String>> map) {
        HSSFCell cellOne = row.getCell((short) 0);
        if (map.get(cellOne.getStringCellValue()) !=null) {
            HSSFCell cell2 = row.getCell((short) 2);
            HSSFCell cell3 = row.getCell((short) 3);
            //通过String 做方法名
            cell2.setCellValue(getExcelCellValue("sheetCash",map.get(cellOne.getStringCellValue()).get(0)));
            cell3.setCellValue(getExcelCellValue("sheetCash",map.get(cellOne.getStringCellValue()).get(1)));
        }
    }
    public void setExcelIncomeCellValue( HSSFRow row ,  Map<String,List<String>> map) {
        HSSFCell cellOne = row.getCell((short) 0);
        if (map.get(cellOne.getStringCellValue()) !=null) {
            HSSFCell cell2 = row.getCell((short) 2);
            HSSFCell cell3 = row.getCell((short) 3);
            //通过String 做方法名
            cell2.setCellValue(getExcelCellValue("sheetIncome",map.get(cellOne.getStringCellValue()).get(0)));
            cell3.setCellValue(getExcelCellValue("sheetIncome",map.get(cellOne.getStringCellValue()).get(1)));
        }
    }
    /**
     * Set value at sheet_balance excel
     * @param row
     * @param map
     * @author Xiaoming
     * @date 2016年1月24日 下午10:53:43
     */
    public void setExcelBalanceCellValue( HSSFRow row ,  Map<String,List<String>> map) {
//       for (int i = 0; i <= Integer.valueOf(row.getFirstCellNum()); i++) {
           HSSFCell cellOne = row.getCell((short) 0);
           if (map.get(cellOne.getStringCellValue()) !=null) {
               HSSFCell cell2 = row.getCell((short) 2);
               HSSFCell cell3 = row.getCell((short) 3);
               //通过String 做方法名
               cell2.setCellValue(getExcelCellValue("sheetBalance",map.get(cellOne.getStringCellValue()).get(0)));
               cell3.setCellValue(getExcelCellValue("sheetBalance",map.get(cellOne.getStringCellValue()).get(1)));
               System.out.println("'");
           }
           HSSFCell cellTwo = row.getCell((short) 4);
           if (map.get(cellTwo.getStringCellValue()) !=null) {
               HSSFCell cell6 = row.getCell((short) 6);
               HSSFCell cell7 = row.getCell((short) 7);
               //通过String 做方法名
               cell6.setCellValue(getExcelCellValue("sheetBalance",map.get(cellTwo.getStringCellValue()).get(0)));
               cell7.setCellValue(getExcelCellValue("sheetBalance",map.get(cellTwo.getStringCellValue()).get(1)));
               System.out.println();
           }
           
//       }
    }
    public String getExcelCellValue(String claName, String methodString) {
        String result = "";
        Object boj = null;
        try {
//            //获取方法  
            if (claName.equals("sheetBalance")) {
                Method sheetBalanceMethod = 
                        sheetBalance.getClass().getDeclaredMethod(methodString,null);
                result = String.valueOf(sheetBalanceMethod.invoke(this.sheetBalance, new Object[]{}));
            } else if (claName.equals("sheetIncome")) {
                Method sheetIncomeMethod = 
                        sheetIncome.getClass().getDeclaredMethod(methodString,null);
                result = String.valueOf(sheetIncomeMethod.invoke(this.sheetIncome, new Object[]{}));
            } else if (claName.equals("sheetCash")) {
                Method sheetCashMethod = 
                        sheetCash.getClass().getDeclaredMethod(methodString,null);
                result = String.valueOf(sheetCashMethod.invoke(this.sheetCash, new Object[]{}));
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * judge properties path
     * @param sheetName
     * @return
     * @author Xiaoming
     * @date 2016年1月24日 下午10:50:04
     */
    public String judgePath(String sheetName) {
        if(sheetName.equals("资产负债表")) {
            return GenerateExcel.PTAH+"/sheet_balance.txt";
        } else if (sheetName.equals("利润表")) {
            return GenerateExcel.PTAH+"/sheet_income.txt";
        } else if (sheetName.equals("现金流量表")) {
            return GenerateExcel.PTAH+"/sheet_cash.txt"; 
        }
        return null;
    }
    /**
     * @param path
     * @return
     * @author Xiaoming
     * @date 2016年1月21日 上午11:08:26
     */
    public static List<List<String>> readingConfigurationFile(String path) {
        List<List<String>> infoList = new ArrayList<>();
        File file = new File(path);
        if(file.isFile() && file.exists()) {
            try {
                InputStreamReader input = new InputStreamReader(new FileInputStream(file),"UTF-8");
                BufferedReader reader = new BufferedReader(input);
                String lineText = null;
                while((lineText = reader.readLine()) != null) {
                    infoList.add(disposeString(lineText));
                }
                reader.close();
                input.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return infoList;
    }
    
    /**
     * return Map<String,String> key = chineseCharacters , value = methodName
     * @param list
     * @return Map<String,String>
     * @author Xiaoming
     * @date 2016年1月22日 下午5:22:38
     */
    public Map<String,List<String>> getMethodNameMap(List<List<String>> list) {
        Map<String,List<String>> map = new HashMap<>();
        List<String> properList ;
        for (int outerNum = 0; outerNum < list.size(); outerNum++) {
//            for(int innerNum =0; innerNum < list.get(outerNum).size(); innerNum ++ ) {
                if (list.get(outerNum).get(0) != null) {
                   if ( list.get(outerNum).size()< 3 ) {
                       map.put(list.get(outerNum).get(0), null);
                   } else {
                       properList = new ArrayList<>();
                       if (list.get(outerNum).size()== 3) { 
                           properList.add(splitProperList(list.get(outerNum).get(1)));
                           properList.add(splitProperList(list.get(outerNum).get(2)));
                       }
                      map.put(list.get(outerNum).get(0), properList);
                   }
                }
//            }
        }
        return map;
    }
    public String splitProperList(String methodString) {
        String[] methodArrays = methodString.split("_");
        StringBuffer buffer = new StringBuffer();
        buffer.append("get");
        for (int i = 0; i < methodArrays.length; i++) {
            buffer.append(captureName(methodArrays[i]));
        }
//        buffer.append("()");
        return buffer.toString();
    }
    /**
     * Convert the first letter is capitalized
     * @param methodName
     * @return
     * @author Xiaoming
     * @date 2016年1月24日 下午10:28:04
     */
    public String captureName(String methodName) {
        char[] cs=methodName.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }
    public static void main(String[] args) {
        List<Object[]>  dataList = new ArrayList<Object[]>();  
        
    }
    /**
     * This is dispose BufferedReader readLine's String
     * @param readLineString
     * @return List<String>
     * @author Xiaoming
     * @date 2016年1月21日 上午11:05:58
     */
    public static List<String> disposeString(String readLineString) {
        String[] txts = null;
        List<String> list = new ArrayList<>();
        if (readLineString.indexOf(" ") != -1) {
            txts = readLineString.split(" ");
            for (int num = 0; num < txts.length; num++) {
                if (txts[num].length() > 1) {
                    list.add(txts[num]);
                }
            }
        } else {
            list.add(readLineString);
        }
        return list;
    }
//    public static List<String> getMethodName(Object obj) {
//        Class clazz = obj.getClass();
//        Method getMethod = null ;
//        Field[] fields = obj.getClass().getDeclaredFields(); //获得属性(字段)
//        for (Field field : fields) {
//            PropertyDescriptor pd;
//            try {
//                pd = new PropertyDescriptor(field.getName(), clazz);
//                getMethod = pd.getReadMethod(); //获得get方法
//            } catch (IntrospectionException e) {
//                e.printStackTrace();
//            }
//        }
//        return getMethod.getName();
//    }
}
