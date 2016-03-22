package com.test.model;

public class LineContentModel {
    
    private String columnOneContent;
    
    private String columnTwoContent;

    private String columnThreeContent;
    
    private String columnFourContent;

    public String getColumnOneContent() {
        return columnOneContent;
    }

    public void setColumnOneContent(String columnOneContent) {
        this.columnOneContent = columnOneContent;
    }

    public String getColumnTwoContent() {
        return columnTwoContent;
    }

    public void setColumnTwoContent(String columnTwoContent) {
        this.columnTwoContent = columnTwoContent;
    }

    public String getColumnThreeContent() {
        return columnThreeContent;
    }

    public void setColumnThreeContent(String columnThreeContent) {
        this.columnThreeContent = columnThreeContent;
    }

    public String getColumnFourContent() {
        return columnFourContent;
    }

    public void setColumnFourContent(String columnFourContent) {
        this.columnFourContent = columnFourContent;
    }

    

    public LineContentModel() {
    }

    public LineContentModel(String columnOneContent, String columnTwoContent, String columnThreeContent,
            String columnFourContent) {
        super();
        this.columnOneContent = columnOneContent;
        this.columnTwoContent = columnTwoContent;
        this.columnThreeContent = columnThreeContent;
        this.columnFourContent = columnFourContent;
    }

    @Override
    public String toString() {
        return "LineContent [columnOneContent=" + columnOneContent + ", columnTwoContent=" + columnTwoContent
                + ", columnThreeContent=" + columnThreeContent + ", columnFourContent=" + columnFourContent + "]";
    }
    
    
}
