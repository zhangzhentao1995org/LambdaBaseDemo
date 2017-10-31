package pers.zhentao.LambdaBaseDemo.Register.handler;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import pers.zhentao.LambdaBaseDemo.Register.dto.RegisterInfo;
import pers.zhentao.LambdaBaseDemo.Register.exception.RegisterException;
import pers.zhentao.LambdaBaseDemo.Register.response.RegisterResponse;
import pers.zhentao.LambdaBaseDemo.Register.service.IRegisterService;
import pers.zhentao.LambdaBaseDemo.Register.service.impl.RegisterServiceImpl;
import pers.zhentao.LambdaBaseDemo.Register.util.ValidateUtil;

/**
 * Lambda处理程序
 *
 * @author zhangzhentao1995@163.com
 * 		   2017-10-27
 */
public class LambdaFunctionHandler implements RequestHandler<RegisterInfo, RegisterResponse> {

    public RegisterResponse handleRequest(RegisterInfo input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("[" + new Date() + "]RegisterInfo: " + JSONObject.toJSONString(input));
        if(!ValidateUtil.inputValidate(input)) {
            return new RegisterResponse(RegisterResponse.RESPONSE_CODE_ERROR,"register info error.");
        }
        IRegisterService registerService = new RegisterServiceImpl();
        try {
            registerService.register(input,context);
        } catch (RegisterException e) {
            return new RegisterResponse(RegisterResponse.RESPONSE_CODE_SYS_EXCEPTION,e.getMsg());
        }
        return new RegisterResponse(RegisterResponse.RESPONSE_CODE_SUCCESS,"register success.");
    }

}
