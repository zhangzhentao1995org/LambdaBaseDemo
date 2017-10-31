package pers.zhentao.LambdaBaseDemo.Register.exception;

/**
 * 注册异常类
 *
 * @author zhangzhentao1995@163.com 
 *         2017-10-27
 */
public class RegisterException extends Exception {

    private static final long serialVersionUID = 8365773790495521094L;
    
    public static final Integer REGISTER_INFO_ERROR_CODE = 1001;
    public static final Integer REGISTER_SYS_ERROR_CODE = 2001;
    public static final Integer REGISTER_DB_ERROR_CODE = 2002;
    
    private Integer code;
    private String msg;

    public RegisterException(String message) {
        super(message);
    }
    
    public RegisterException(Integer code,String msg) {
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
