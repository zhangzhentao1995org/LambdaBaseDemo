package pers.zhentao.LambdaBaseDemo.Register.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import pers.zhentao.LambdaBaseDemo.Register.dto.RegisterInfo;
import pers.zhentao.LambdaBaseDemo.Register.exception.RegisterException;
import pers.zhentao.LambdaBaseDemo.Register.service.IRegisterService;

/**
 * 注册service实现类
 *
 * @author zhangzhentao1995@163.com
 * 		   2017-10-27
 */
public class RegisterServiceImpl implements IRegisterService{
    /**
     * RDS数据库配置信息
     */
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private String url = null;
    private String username = null;
    private String password = null;
    private final String INSERT_SQL = "insert into user_info (user_name,password,email) value (?,?,?)";

    public void register(RegisterInfo info, Context context) throws RegisterException {
        LambdaLogger logger = context.getLogger();
        url = System.getenv("DB_URL");
        username = System.getenv("USERNAME");
        password = System.getenv("PASSWORD");
        if(url == null || username == null || password == null) {
            throw new RegisterException(RegisterException.REGISTER_SYS_ERROR_CODE, "db config is null.");
        }
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RegisterException(RegisterException.REGISTER_SYS_ERROR_CODE,"mysql driver not found.");
        }
        Connection conn;
        try {
            logger.log("start get connection.");
            conn = DriverManager.getConnection(url,username,password);
            logger.log("end get connection.");
            PreparedStatement statement = conn.prepareStatement(INSERT_SQL);
            statement.setString(1, info.getUsername());
            statement.setString(2, info.getPassword());
            statement.setString(3, info.getEmail());
            int count = statement.executeUpdate();
            conn.close();
            if(count != 1) {
                throw new RegisterException(RegisterException.REGISTER_DB_ERROR_CODE,"insert record failed.");
            }
        }catch(SQLException e) {
            throw new RegisterException(RegisterException.REGISTER_DB_ERROR_CODE,e.getMessage());
        }
    }
}
