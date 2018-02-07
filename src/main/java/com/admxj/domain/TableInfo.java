package com.admxj.domain;

public class TableInfo {

    private String name;

    private String type;

    private Integer pk;

    public TableInfo(String name, String type, Integer pk) {
        this.name = name;
        this.type = type;
        this.pk = pk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", pk=" + pk +
                '}';
    }
}
