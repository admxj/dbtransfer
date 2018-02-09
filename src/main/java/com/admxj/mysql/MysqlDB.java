package com.admxj.mysql;

import com.admxj.DBGenerator;
import com.admxj.domain.TableInfo;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlDB extends DBGenerator{

    public MysqlDB(DataSource dataSource) {
        super(dataSource);
    }



    @Override
    protected List<String> selectTables() throws SQLException {
        List<String> list = new ArrayList<>();

        DatabaseMetaData meta = connection.getMetaData();
        System.out.println(meta);
        ResultSet resultSet = meta.getTables(null, null, null, new String[]{"TABLE"});

        while (resultSet.next()){
            list.add(resultSet.getString("name"));
        }
        return list;
    }

    @Override
    protected List<TableInfo> selectField(String table) throws SQLException {
        return null;
    }
}
