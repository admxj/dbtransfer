package com.admxj.sqlite;

import com.admxj.domain.Table;
import com.admxj.domain.TableInfo;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLite {

    private Connection sqliteConnection = null;

    public SQLite(DataSource dataSource){
        try {
            sqliteConnection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test(){

        try {
            Class.forName("org.sqlite.JDBC");
            sqliteConnection = DriverManager.getConnection("jdbc:sqlite:/Users/admxj/Administrative-divisions-of-China/dist/data.sqlite");

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }
    public List<Table> select() throws SQLException {

        List<Table> tableSet = new ArrayList<>();
        List<String> tables = selectTables();
        for (String table: tables){
            List<TableInfo> list = selectField(table);
            tableSet.add(new Table(table, list));
        }
        return tableSet;
    }

    private List<String> selectTables() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "select name from sqlite_master where type='table' order by name";
        Statement statement = sqliteConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            list.add(resultSet.getString("name"));
        }
        return list;
    }

    private List<TableInfo> selectField(String table) throws SQLException {
        List<TableInfo> list = new ArrayList<>();
        String sql = "PRAGMA table_info("+table+")";
        Statement statement = sqliteConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String name = resultSet.getString("name");
            String type = resultSet.getString("type");
            Integer pk = resultSet.getInt("pk");

            list.add(new TableInfo(name, type, pk));
        }
        return list;
    }

}
