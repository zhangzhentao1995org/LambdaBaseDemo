package pers.zhentao.LambdaBaseDemo.Login.util;

import pers.zhentao.LambdaBaseDemo.Login.dto.LoginInfo;

/**
 * 校验工具类
 *
 * @author zhangzhentao1995@163.com 
 *         2017-10-27
 */
public class ValidateUtil {
    /**
     * 登录信息校验
     * 
     * @param info 登录信息
     * @return 是否通过校验
     */
    public static Boolean loginInputValidate(LoginInfo info) {
        if (info.getUsername() == null || info.getUsername().trim().equals("")) {
            return false;
        }
        if (info.getPassword() == null || info.getPassword().trim().equals("")) {
            return false;
        }
        return true;
    }
}
