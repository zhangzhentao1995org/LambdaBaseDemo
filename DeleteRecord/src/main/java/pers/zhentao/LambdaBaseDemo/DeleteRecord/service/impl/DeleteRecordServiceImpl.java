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
    private final String url = "jdbc:mysql://lambdabasedemodb.cloqkold5jlj.ap-southeast-2.rds.amazonaws.com:3306/lambdabasedemodb";
    private final String username = "root";
    private final String password = "lambdabasedemodb";
    private final String DELETE_SQL = "delete from address_book where address_book_id=?";

    public void deleteRecord(Integer id) throws DeleteRecordException {
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
