package pers.zhentao.LambdaBaseDemo.response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import pers.zhentao.LambdaBaseDemo.dto.Record;

/**
 * 返回消息
 *
 * @author zhangzhentao1995@163.com 
 *         2017-10-30
 */
public class ResponseData {
    public static final Integer RESPONSE_CODE_SUCCESS = 1000;
    public static final Integer RESPONSE_CODE_ERROR = 1001;
    public static final Integer RESPONSE_CODE_SYS_EXCEPTION = 1002;

    private Integer code;
    private String message;
    private List<Record> rows;
    private Map<Object, Object> map;

    public ResponseData(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseData(Integer code, List<Record> rows) {
        this.rows = rows;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Record> getRows() {
        return rows;
    }

    public void setRows(List<Record> rows) {
        this.rows = rows;
    }

    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }

    public static Integer getResponseCodeSuccess() {
        return RESPONSE_CODE_SUCCESS;
    }

    public static Integer getResponseCodeError() {
        return RESPONSE_CODE_ERROR;
    }

    public static Integer getResponseCodeSysException() {
        return RESPONSE_CODE_SYS_EXCEPTION;
    }
}
