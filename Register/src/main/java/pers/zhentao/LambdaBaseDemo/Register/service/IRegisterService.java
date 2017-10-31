package pers.zhentao.LambdaBaseDemo.Register.service;

import com.amazonaws.services.lambda.runtime.Context;

import pers.zhentao.LambdaBaseDemo.Register.dto.RegisterInfo;
import pers.zhentao.LambdaBaseDemo.Register.exception.RegisterException;

/**
 * 注册service接口
 *
 * @author zhangzhentao1995@163.com
 * 		   2017-10-27
 */
public interface IRegisterService {
    /**
     * 注册
     * @param info 注册信息
     * @throws RegisterException
     */
    public void register(RegisterInfo info, Context context) throws RegisterException;
}
