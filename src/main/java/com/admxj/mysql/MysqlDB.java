package com.admxj.mysql;

import com.admxj.DBGenerator;
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
