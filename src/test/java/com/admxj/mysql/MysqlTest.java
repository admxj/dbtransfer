package com.admxj.mysql;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.junit.Test;

import java.util.List;

public class MysqlTest {

    @Test
    public void select(){

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/xsy");
        dataSource.setUser("root");
        dataSource.setPassword("123456");

        MysqlDB mysqlDB = new MysqlDB(dataSource);


        try {
            List<String> list = mysqlDB.selectTables();
            System.out.println(list);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }
}
