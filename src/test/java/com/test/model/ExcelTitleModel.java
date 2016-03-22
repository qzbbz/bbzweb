package com.test.model;

public class ExcelTitleModel {
    private String titleName;
    //别名
    private String alias;
    //Table name eg:会小企01表
    private String tableName;
    //producer name eg:XX有限公司
    private String producer;
    //
    private String dateTime;
    
    private String unit;
    
    private String columnOneDescription ;
    
    private String columnTwoDescription;
    
    private String columnThreeDescription;
    
    private String columnFourDescription;
    
    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getColumnOneDescription() {
        return columnOneDescription;
    }

    public void setColumnOneDescription(String columnOneDescription) {
        this.columnOneDescription = columnOneDescription;
    }

    public String getColumnTwoDescription() {
        return columnTwoDescription;
    }

    public void setColumnTwoDescription(String columnTwoDescription) {
        this.columnTwoDescription = columnTwoDescription;
    }

    public String getColumnThreeDescription() {
        return columnThreeDescription;
    }

    public void setColumnThreeDescription(String columnThreeDescription) {
        this.columnThreeDescription = columnThreeDescription;
    }

    public String getColumnFourDescription() {
        return columnFourDescription;
    }

    public void setColumnFourDescription(String columnFourDescription) {
        this.columnFourDescription = columnFourDescription;
    }
    
    public ExcelTitleModel(String titleName, String alias, String tableName, String producer, String dateTime,
            String unit, String columnOneDescription, String columnTwoDescription, String columnThreeDescription,
            String columnFourDescription) {
        super();
        this.titleName = titleName;
        this.alias = alias;
        this.tableName = tableName;
        this.producer = producer;
        this.dateTime = dateTime;
        this.unit = unit;
        this.columnOneDescription = columnOneDescription;
        this.columnTwoDescription = columnTwoDescription;
        this.columnThreeDescription = columnThreeDescription;
        this.columnFourDescription = columnFourDescription;
    }
    
    public ExcelTitleModel() {
    }

    @Override
    public String toString() {
        return "ExcelTitle [titleName=" + titleName + ", alias=" + alias + ", tableName=" + tableName + ", producer="
                + producer + ", dateTime=" + dateTime + ", unit=" + unit + ", columnOneDescription="
                + columnOneDescription + ", columnTwoDescription=" + columnTwoDescription + ", columnThreeDescription="
                + columnThreeDescription + ", columnFourDescription=" + columnFourDescription + "]";
    }
    
   
    
}
