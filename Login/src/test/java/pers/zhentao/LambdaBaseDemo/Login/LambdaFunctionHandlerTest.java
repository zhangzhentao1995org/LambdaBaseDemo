package pers.zhentao.LambdaBaseDemo.Login;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.amazonaws.services.lambda.runtime.Context;

import pers.zhentao.LambdaBaseDemo.Login.dto.LoginInfo;
import pers.zhentao.LambdaBaseDemo.Login.handler.LambdaFunctionHandler;
import pers.zhentao.LambdaBaseDemo.Login.response.LoginResponse;

public class LambdaFunctionHandlerTest {
    
    private static LoginInfo info;
    
    @BeforeClass
    public static void createInput() throws IOException {
        info = new LoginInfo();
        info.setUsername("a");
        info.setPassword("b");
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
        LoginResponse response = handler.handleRequest(info, ctx);
        System.out.println(JSON.toJSONString(response));
    }
}
