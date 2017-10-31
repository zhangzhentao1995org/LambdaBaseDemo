package pers.zhentao.LambdaBaseDemo.Login.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import pers.zhentao.LambdaBaseDemo.Login.dto.LoginInfo;
import pers.zhentao.LambdaBaseDemo.Login.dto.User;
import pers.zhentao.LambdaBaseDemo.Login.exception.LoginException;
import pers.zhentao.LambdaBaseDemo.Login.response.LoginResponse;
import pers.zhentao.LambdaBaseDemo.Login.service.ILoginService;
import pers.zhentao.LambdaBaseDemo.Login.util.TokenUtil;

public class LoginServiceImpl implements ILoginService{
    /**
     * RDS数据库配置信息
     */
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private String url = null;
    private String username = null;
    private String password = null;
    private String jwtSecret = null;
    private final String QUERY_SQL = "SELECT user_info_id,user_name,email,create_time from user_info where user_name=? and password=?";

    @Override
    public LoginResponse login(LoginInfo info, Context context) throws LoginException {
        LambdaLogger logger = context.getLogger();
        url = System.getenv("DB_URL");
        username = System.getenv("USERNAME");
        password = System.getenv("PASSWORD");
        jwtSecret = System.getenv("JWT_SECRET");
        if(url == null || username == null || password == null) {
            throw new LoginException(LoginException.LOGIN_SYS_ERROR_CODE, "db config is null.");
        }
        if(jwtSecret == null) {
            throw new LoginException(LoginException.LOGIN_SYS_ERROR_CODE, "jwtSecret is null.");
        }
        try {
            Class.forName(driver);
        } catch(ClassNotFoundException e) {
            throw new LoginException(LoginException.LOGIN_SYS_ERROR_CODE,"mysql driver not found.");
        }
        Connection conn;
        try {
            conn = DriverManager.getConnection(url,username,password);
            PreparedStatement statement = conn.prepareStatement(QUERY_SQL);
            statement.setString(1, info.getUsername());
            statement.setString(2, info.getPassword());
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                User user = new User();
                user.setUserId(result.getInt(1));
                user.setUserName(result.getString(2));
                user.setEmail(result.getString(3));
                user.setCreateDate(result.getDate(4));
                String token = TokenUtil.getToken(user, jwtSecret);
                conn.close();
                if(token == null) {
                    return new LoginResponse(LoginResponse.RESPONSE_CODE_ERROR,"generate token failed.");
                }else {
                    return new LoginResponse(LoginResponse.RESPONSE_CODE_SUCCESS,token);
                }
            }else {
                conn.close();
                return new LoginResponse(LoginResponse.RESPONSE_CODE_ERROR,"login info error.");
            }
        }catch(SQLException e) {
            logger.log("["+new Date()+"]SQL Exception:"+e.getMessage());
            throw new LoginException(LoginException.LOGIN_DB_ERROR_CODE,e.getMessage());
        }
    }

}
