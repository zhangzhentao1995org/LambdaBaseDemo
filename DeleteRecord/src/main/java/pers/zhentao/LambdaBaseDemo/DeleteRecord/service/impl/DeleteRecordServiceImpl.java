package pers.zhentao.LambdaBaseDemo.DeleteRecord.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import pers.zhentao.LambdaBaseDemo.DeleteRecord.exception.DeleteRecordException;
import pers.zhentao.LambdaBaseDemo.DeleteRecord.service.IDeleteRecordService;

public class DeleteRecordServiceImpl implements IDeleteRecordService {

    /**
     * RDS数据库配置信息
     */
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private String url = null;
    private String username = null;
    private String password = null;
    private final String DELETE_SQL = "delete from address_book where address_book_id=?";

    public void deleteRecord(Integer id) throws DeleteRecordException {
        url = System.getenv("DB_URL");
        username = System.getenv("USERNAME");
        password = System.getenv("PASSWORD");
        if(url == null || username == null || password == null) {
            throw new DeleteRecordException(DeleteRecordException.SYS_ERROR_CODE, "db config is null.");
        }
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DeleteRecordException(DeleteRecordException.SYS_ERROR_CODE, "mysql driver not found.");
        }
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = conn.prepareStatement(DELETE_SQL);
            statement.setInt(1, id);
            int count = statement.executeUpdate();
            conn.close();
            if (count != 1) {
                throw new DeleteRecordException(DeleteRecordException.DB_ERROR_CODE, "delete record failed.");
            }
        } catch (SQLException e) {
            throw new DeleteRecordException(DeleteRecordException.DB_ERROR_CODE, e.getMessage());
        }

    }

}
