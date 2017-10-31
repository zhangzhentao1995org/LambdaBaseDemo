package pers.zhentao.LambdaBaseDemo.Login.util;

import com.alibaba.fastjson.JSONObject;

import io.jsonwebtoken.Claims;
import pers.zhentao.LambdaBaseDemo.Login.dto.User;

/**
 * token工具类
 *
 * @author zhangzhentao1995@163.com 
 *         2017-10-30
 */
public class TokenUtil {

    public static User validateToken(String token) {
        try {
            Claims claims = JwtUtil.parseJwt(token);
            String subject = claims.getSubject();
            User user = JSONObject.toJavaObject(JSONObject.parseObject(subject), User.class);
            return user;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String getToken(User user) {
        String subject = JwtUtil.generalSubject(user);
        String token = null;
        try {
            token = JwtUtil.createJwt(Constant.JWT_ID, subject, Constant.JWT_TTL);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return token;
    }

}
