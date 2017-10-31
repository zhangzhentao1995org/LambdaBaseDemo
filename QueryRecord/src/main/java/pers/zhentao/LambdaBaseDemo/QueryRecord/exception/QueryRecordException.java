package pers.zhentao.LambdaBaseDemo.QueryRecord.exception;

/**
 * 查询异常类
 *
 * @author zhangzhentao1995@163.com 
 *         2017-10-30
 */
public class QueryRecordException extends Exception {

    private static final long serialVersionUID = -3483738766858412487L;

    public static final Integer INFO_ERROR_CODE = 1001;
    public static final Integer SYS_ERROR_CODE = 2001;
    public static final Integer DB_ERROR_CODE = 2002;

    private Integer code;
    private String msg;

    public QueryRecordException(String message) {
        super(message);
    }

    public QueryRecordException(Integer code, String msg) {
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
