package pers.zhentao.LambdaBaseDemo.Login.exception;

/**
 * 登录异常类
 *
 * @author zhangzhentao1995@163.com 
 *         2017-10-27
 */
public class LoginException extends Exception {

    private static final long serialVersionUID = -4707902553431533762L;

    public static final Integer LOGIN_INFO_ERROR_CODE = 1001;
    public static final Integer LOGIN_SYS_ERROR_CODE = 2001;
    public static final Integer LOGIN_DB_ERROR_CODE = 2002;

    private Integer code;
    private String msg;

    public LoginException(String message) {
        super(message);
    }

    public LoginException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
