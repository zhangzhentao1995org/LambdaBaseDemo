package pers.zhentao.LambdaBaseDemo.AddRecord.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.amazonaws.services.lambda.runtime.Context;

import pers.zhentao.LambdaBaseDemo.AddRecord.dto.Record;
import pers.zhentao.LambdaBaseDemo.AddRecord.exception.AddRecordException;
import pers.zhentao.LambdaBaseDemo.AddRecord.service.IAddRecordService;

public class AddRecordServiceImpl implements IAddRecordService {
    /**
     * RDS数据库配置信息
     */
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private String url = null;
    private String username = null;
    private String password = null;
    private final String INSERT_SQL = "insert into address_book (name,phone_number,address,telephone_number,note,created_by) value (?,?,?,?,?,?)";
    private final String UPDATE_SQL = "update address_book set name=?,phone_number=?,address=?,telephone_number=?,note=? where address_book_id=?";

    @Override
    public void addRecord(Record record, Context context) throws AddRecordException {
        url = System.getenv("DB_URL");
        username = System.getenv("USERNAME");
        password = System.getenv("PASSWORD");
        if(url == null || username == null || password == null) {
            throw new AddRecordException(AddRecordException.SYS_ERROR_CODE, "db config is null.");
        }
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new AddRecordException(AddRecordException.SYS_ERROR_CODE, "mysql driver not found.");
        }
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement statement;
            if (record.getAddressBookId() == null) {
                statement = conn.prepareStatement(INSERT_SQL);
                if (record.getCreatedBy() != null) {
                    statement.setInt(6, record.getCreatedBy());
                } else {
                    statement.setInt(6, -1);
                }
            } else {
                statement = conn.prepareStatement(UPDATE_SQL);
                statement.setInt(6, record.getAddressBookId());
            }
            statement.setString(1, record.getName());
            statement.setString(2, record.getPhoneNumber());
            statement.setString(3, record.getAddress());
            statement.setString(4, record.getTelephoneNumber());
            statement.setString(5, record.getNote());
            int count = statement.executeUpdate();
            conn.close();
            if (count != 1) {
                throw new AddRecordException(AddRecordException.DB_ERROR_CODE, "insert/update record faile.");
            }
        } catch (SQLException e) {
            throw new AddRecordException(AddRecordException.DB_ERROR_CODE, e.getMessage());
        }

    }

}
