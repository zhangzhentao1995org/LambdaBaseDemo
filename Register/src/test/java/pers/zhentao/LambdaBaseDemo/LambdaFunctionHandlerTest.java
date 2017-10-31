package pers.zhentao.LambdaBaseDemo;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.amazonaws.services.lambda.runtime.Context;

import pers.zhentao.LambdaBaseDemo.Register.dto.RegisterInfo;
import pers.zhentao.LambdaBaseDemo.Register.handler.LambdaFunctionHandler;
import pers.zhentao.LambdaBaseDemo.Register.response.RegisterResponse;

public class LambdaFunctionHandlerTest {
    
    private static RegisterInfo info;
    
    @BeforeClass
    public static void createInput() throws IOException {
        info = new RegisterInfo();
        info.setUsername("a");
        info.setPassword("b");
        info.setEmail("c@123.com");
    }

    private Context createContext() {
        TestContext ctx = new TestContext();
        ctx.setFunctionName("Register");
        return ctx;
    }
    
    @Test
    public void testLambdaFunctionHandler() {
        LambdaFunctionHandler handler = new LambdaFunctionHandler();
        Context ctx = createContext();
        RegisterResponse response = handler.handleRequest(info, ctx);
        System.out.println(JSON.toJSONString(response));
    }
}
