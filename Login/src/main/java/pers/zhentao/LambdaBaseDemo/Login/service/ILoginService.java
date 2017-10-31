package pers.zhentao.LambdaBaseDemo.Login.service;

import com.amazonaws.services.lambda.runtime.Context;

import pers.zhentao.LambdaBaseDemo.Login.dto.LoginInfo;
import pers.zhentao.LambdaBaseDemo.Login.exception.LoginException;
import pers.zhentao.LambdaBaseDemo.Login.response.LoginResponse;

/**
 * 登录服务接口
 *
 * @author zhangzhentao1995@163.com
 * 		   2017-10-30
 */
public interface ILoginService {
    /**
     * 登录接口
     * @param info
     * @return 登录信息
     * @throws LoginException
     */
    public LoginResponse login(LoginInfo info, Context context) throws LoginException;
}
