package com.admxj;

import com.admxj.domain.Table;
import com.admxj.domain.TableInfo;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DBGenerator {

    protected Connection connection = null;


    public DBGenerator(DataSource dataSource) {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Table> select() throws SQLException{
        List<Table> tableSet = new ArrayList<>();
        List<String> tables = selectTables();
        for (String table: tables){
            List<TableInfo> list = selectField(table);
            tableSet.add(new Table(table, list));
        }
        return tableSet;
    }

    protected abstract List<String> selectTables() throws SQLException ;

    protected abstract List<TableInfo> selectField(String table) throws SQLException ;

}
