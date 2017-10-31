package pers.zhentao.LambdaBaseDemo.Login.util;

/**
 * JWT配置常量
 *
 * @author zhangzhentao1995@163.com
 * 		   2017-10-30
 */
public class Constant {
	public static final String JWT_ID = "jwt";
	public static final int JWT_TTL = 60*60*1000;
	public static final int JWT_REFRESH_INTERVAL = 55*60*1000;
	public static final int JWT_REFRESH_TTL = 12*60*60*1000;
}
