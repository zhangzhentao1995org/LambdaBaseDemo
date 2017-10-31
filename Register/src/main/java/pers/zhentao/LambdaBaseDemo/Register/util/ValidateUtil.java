package pers.zhentao.LambdaBaseDemo.Register.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pers.zhentao.LambdaBaseDemo.Register.dto.RegisterInfo;

/**
 * 校验工具类
 *
 * @author zhangzhentao1995@163.com 
 *         2017-10-27
 */
public class ValidateUtil {
    /**
     * 校验注册信息
     * 
     * @param info 注册信息
     * @return 是否校验通过
     */
    public static Boolean inputValidate(RegisterInfo info) {
        if (info.getUsername() == null || info.getUsername().trim().equals("")) {
            return false;
        }
        if (info.getEmail()==null || info.getEmail().trim().equals("")) {
            return false;
        }
        //邮箱格式校验
        String regEx = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(info.getEmail().trim());
        if (!matcher.matches()) {
            return false;
        }
        if (info.getPassword()==null || info.getPassword().trim().equals("")) {
            return false;
        }
        return true;
    }
}
