package com.test.model;

public class PDFUtils {
    private int titleColumn;
    
    private int columnNumber;
    
    private int rowNumber;

    public PDFUtils() {
    }

    public int getTitleColumn() {
        return titleColumn;
    }

    public void setTitleColumn(int titleColumn) {
        this.titleColumn = titleColumn;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public PDFUtils(int titleColumn, int columnNumber, int rowNumber) {
        super();
        this.titleColumn = titleColumn;
        this.columnNumber = columnNumber;
        this.rowNumber = rowNumber;
    }
    
    
    
}
