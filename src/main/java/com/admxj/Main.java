package com.admxj;

import com.admxj.mysql.MysqlDB;
import com.admxj.sqlite.SQLite;
import com.admxj.domain.Table;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.sqlite.SQLiteDataSource;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        SQLiteDataSource sqLiteDataSource = new SQLiteDataSource();
        sqLiteDataSource.setUrl("jdbc:sqlite:/Users/admxj/Administrative-divisions-of-China/dist/data.sqlite");
        SQLite sqLite = new SQLite(sqLiteDataSource);
        List<Table> select = sqLite.select();


        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL("jdbc:mysql://localhost:3306/demo");
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("123456");
        MysqlDB mysqlDB = new MysqlDB(mysqlDataSource);
        mysqlDB.createTable(select);

    }


}
