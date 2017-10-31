package pers.zhentao.LambdaBaseDemo.AddRecord.exception;

/**
 * 添加异常类
 *
 * @author zhangzhentao1995@163.com
 * 		   2017-10-30
 */
public class AddRecordException extends Exception {

    private static final long serialVersionUID = -7268991732813176830L;

    public static final Integer INFO_ERROR_CODE = 1001;
    public static final Integer SYS_ERROR_CODE = 2001;
    public static final Integer DB_ERROR_CODE = 2002;

    private Integer code;
    private String msg;

    public AddRecordException(String message) {
        super(message);
    }

    public AddRecordException(Integer code, String msg) {
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
