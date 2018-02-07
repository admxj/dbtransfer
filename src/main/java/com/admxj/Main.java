package com.admxj;

import com.admxj.sqlite.SQLite;
import com.admxj.domain.Table;
import org.sqlite.SQLiteDataSource;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        SQLiteDataSource sqLiteDataSource = new SQLiteDataSource();
        sqLiteDataSource.setUrl("jdbc:sqlite:/Users/admxj/Administrative-divisions-of-China/dist/data.sqlite");
        SQLite sqLite = new SQLite(sqLiteDataSource);
        List<Table> select = sqLite.select();
        System.out.println();

    }


}
