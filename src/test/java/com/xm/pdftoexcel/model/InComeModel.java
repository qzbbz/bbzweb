package com.xm.pdftoexcel.model;

/**
 * <p>Title: InComeModel</p>
 * <p>Description:  </p>
 *  @author XiaoMing
 * 	 @date 2016年3月16日下午3:19:57
 */
public class InComeModel {
    private String projectName;// project name
    
    private String lineNumber;
    
    private String monthMoney;
    
    private String totalMoney;

    public InComeModel() {
    }

    public InComeModel(String projectName, String lineNumber, String monthMoney, String totalMoney) {
        super();
        this.projectName = projectName;
        this.lineNumber = lineNumber;
        this.monthMoney = monthMoney;
        this.totalMoney = totalMoney;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getMonthMoney() {
        return monthMoney;
    }

    public void setMonthMoney(String monthMoney) {
        this.monthMoney = monthMoney;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }
    
    
}
