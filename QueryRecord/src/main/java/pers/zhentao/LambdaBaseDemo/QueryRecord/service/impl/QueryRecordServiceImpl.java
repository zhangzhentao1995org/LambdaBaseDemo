package pers.zhentao.LambdaBaseDemo.QueryRecord.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pers.zhentao.LambdaBaseDemo.QueryRecord.dto.Record;
import pers.zhentao.LambdaBaseDemo.QueryRecord.exception.QueryRecordException;
import pers.zhentao.LambdaBaseDemo.QueryRecord.service.IQueryRecordService;

public class QueryRecordServiceImpl implements IQueryRecordService {
    /**
     * RDS数据库配置信息
     */
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://lambdabasedemodb.cloqkold5jlj.ap-southeast-2.rds.amazonaws.com:3306/lambdabasedemodb";
    private final String username = "root";
    private final String password = "lambdabasedemodb";
    private final String QUERY_SQL = "select * from address_book";

    @Override
    public List<Record> queryAll() throws QueryRecordException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new QueryRecordException(QueryRecordException.SYS_ERROR_CODE, "mysql driver not found.");
        }
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = conn.prepareStatement(QUERY_SQL);
            ResultSet result = statement.executeQuery();
            List<Record> list = new ArrayList<>();
            while (result.next()) {
                Record record = new Record();
                record.setAddressBookId(result.getInt(1));
                record.setName(result.getString(2));
                record.setPhoneNumber(result.getString(3));
                record.setAddress(result.getString(4));
                record.setTelephoneNumber(result.getString(5));
                record.setNote(result.getString(6));
                record.setCreateTime(result.getDate(7));
                record.setCreatedBy(result.getInt(8));
                list.add(record);
            }
            conn.close();
            return list;
        } catch (SQLException e) {
            throw new QueryRecordException(QueryRecordException.DB_ERROR_CODE, e.getMessage());
        }
    }
}
