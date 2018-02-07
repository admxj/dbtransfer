package com.admxj.domain;

import java.util.List;

public class Table {

    private String table;

    private List<TableInfo> tableInfo;

    public Table(String table, List<TableInfo> tableInfo) {
        this.table = table;
        this.tableInfo = tableInfo;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<TableInfo> getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(List<TableInfo> tableInfo) {
        this.tableInfo = tableInfo;
    }
}
