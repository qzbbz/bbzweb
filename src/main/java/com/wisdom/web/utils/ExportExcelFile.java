package com.wisdom.web.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ExportExcelFile {
    /**
     * judge file whether exist
     * 
     * @param filePath
     * @return boolean
     * @author Xiaoming
     * @date 2016年1月20日 下午9:45:44
     */
    public static boolean judgeFileExist(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        }
        return false;
    }
    /**
     * generate excel name
     * @param companyId
     * @param date
     * @return String 
     * @author Xiaoming
     * @date 2016年1月20日 下午10:13:30
     */
    public static String generateExcelName(long companyId,String date) {
        String fileName = date + "_" + String.valueOf(companyId) + ".xls";
        return fileName;
    }
    
    /**
     * Delete file
     * @param path
     * @author Xiaoming
     * @date 2016年1月22日 下午4:30:31
     */
    public static void removeFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }
    /**
     * return ExcelPath
     * @param fileName
     * @return String
     * @author Xiaoming
     * @date 2016年1月22日 下午4:45:21
     */
    public static String getFilePath(String fileName) {
        String path = "/home/" + fileName;
        return path;
    }
}
