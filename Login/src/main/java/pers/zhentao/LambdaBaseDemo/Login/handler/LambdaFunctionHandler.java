package pers.zhentao.LambdaBaseDemo.Login.handler;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import pers.zhentao.LambdaBaseDemo.Login.dto.LoginInfo;
import pers.zhentao.LambdaBaseDemo.Login.exception.LoginException;
import pers.zhentao.LambdaBaseDemo.Login.response.LoginResponse;
import pers.zhentao.LambdaBaseDemo.Login.service.ILoginService;
import pers.zhentao.LambdaBaseDemo.Login.service.impl.LoginServiceImpl;
import pers.zhentao.LambdaBaseDemo.Login.util.ValidateUtil;

/**
 * Lambda处理程序
 *
 * @author zhangzhentao1995@163.com
 * 		   2017-10-27
 */
public class LambdaFunctionHandler implements RequestHandler<LoginInfo, LoginResponse>{

    public LoginResponse handleRequest(LoginInfo input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("["+new Date()+"]LoginInfo: "+JSONObject.toJSONString(input));
        if(!ValidateUtil.loginInputValidate(input)) {
            return new LoginResponse(LoginResponse.RESPONSE_CODE_ERROR,"login info error.");
        }
        try {
            ILoginService loginService = new LoginServiceImpl();
            return loginService.login(input, context);
        } catch(LoginException e) {
            return new LoginResponse(LoginResponse.RESPONSE_CODE_SYS_EXCEPTION,e.getMsg());
        }
    }

}
