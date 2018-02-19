package com.admxj.mysql;

import com.admxj.DBGenerator;
import com.admxj.domain.Table;
import com.admxj.domain.TableInfo;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlDB extends DBGenerator{

    public MysqlDB(DataSource dataSource) {
        super(dataSource);
    }

    public void insertData(List<Table> tables){
        for (Table table: tables) {
            String sql = "insert into "+table.getTable()+"(";
            List<TableInfo> tableInfos = table.getTableInfo();
            if (tableInfos.size() == 0)
                continue;
            for (int i = 0; i < tableInfos.size()-1; i++){
                TableInfo tableInfo = tableInfos.get(i);
                sql = sql + tableInfo.getName() + ",";
            }
            sql = sql + tableInfos.get(tableInfos.size()-1).getName();
            sql = sql + ") values(";
        }
    }

    public void createTable(List<Table> tables) throws SQLException {
        for (Table table: tables) {
            if (table.getTableInfo().size() == 0)
                continue;
            String sql = "create table "+table.getTable() +"(";
            String primary = "primary KEY(";
            List<TableInfo> tableInfos = table.getTableInfo();
            for (int i = 0; i < tableInfos.size(); i++) {
                TableInfo tableInfo = tableInfos.get(i);
                sql = sql + tableInfo.getName() + " ";
                sql = sql + tableInfo.getType() + " ";
                if (tableInfo.getPk() == 1)
                    primary = primary + tableInfo.getName();
                sql = sql + ",";
            }
            primary = primary + ")";
            sql = sql + primary;
            sql = sql +")";

            Statement statement = connection.createStatement();
            System.out.println(sql);
            int i = statement.executeUpdate(sql);

            System.out.println(sql);
        }
    }

    @Override
    protected List<String> selectTables() throws SQLException {
        List<String> list = new ArrayList<>();

        DatabaseMetaData meta = connection.getMetaData();
        System.out.println(meta);
        ResultSet resultSet = meta.getTables(null, null, null, new String[]{"TABLE"});

        while (resultSet.next()){
            list.add(resultSet.getString("TABLE_NAME"));
        }
        return list;
    }

    @Override
    protected List<TableInfo> selectField(String table) throws SQLException {
        List<TableInfo> list = new ArrayList<>();
        String sql = "desc `"+table+"`";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String name = resultSet.getString("Field");
            String type = resultSet.getString("Type");
            String pkstr = resultSet.getString("Key");
            list.add(new TableInfo(name, type, StringUtils.equalsIgnoreCase("PRI",pkstr) ? 1 : 0));
        }
        return list;
    }
}
