package com.pluralsight.managers;

public enum ShipperField {
    COMPANY_NAME("CompanyName"),
    PHONE("Phone");

    private final String columnName;

    ShipperField(String columnName) {
        this.columnName = columnName;
    }

    public String getColumn() {
        return columnName;
    }
}