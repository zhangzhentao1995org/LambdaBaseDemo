package pers.zhentao.LambdaBaseDemo.dto;

/**
 * 请求封装
 *
 * @author zhangzhentao1995@163.com 
 *         2017-10-30
 */
public class RequestInfo {
    public static final int TARGET_ADD_RECORD = 3001;
    public static final int TARGET_DELETE_RECORD = 3002;
    public static final int TARGET_QUERY_RECORD = 3003;
    /**
     * token
     */
    private String token;
    /**
     * 目标接口
     */
    private int target;
    /**
     * 参数Json
     */
    private String paramJson;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public String getParamJson() {
        return paramJson;
    }

    public void setParamJson(String paramJson) {
        this.paramJson = paramJson;
    }

}
