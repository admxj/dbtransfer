package com.admxj.sqlite;

import com.admxj.DBGenerator;
import com.admxj.domain.Table;
import com.admxj.domain.TableInfo;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLite extends DBGenerator{


    public SQLite(DataSource dataSource){
        super(dataSource);

    }

    @Test
    public void test(){

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/admxj/Administrative-divisions-of-China/dist/data.sqlite");

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }

    @Override
    protected List<String> selectTables() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "select name from sqlite_master where type='table' order by name";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            list.add(resultSet.getString("name"));
        }
        return list;
    }

    @Override
    protected List<TableInfo> selectField(String table) throws SQLException {
        List<TableInfo> list = new ArrayList<>();
        String sql = "PRAGMA table_info("+table+")";
        Statement statement = connection.createStatement();
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
