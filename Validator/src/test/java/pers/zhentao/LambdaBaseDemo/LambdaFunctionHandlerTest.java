package pers.zhentao.LambdaBaseDemo;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.amazonaws.services.lambda.runtime.Context;

import pers.zhentao.LambdaBaseDemo.dto.RequestInfo;
import pers.zhentao.LambdaBaseDemo.handler.LambdaFunctionHandler;
import pers.zhentao.LambdaBaseDemo.response.ResponseData;

public class LambdaFunctionHandlerTest {
    
    private static RequestInfo info;
    
    @BeforeClass
    public static void createInput() throws IOException {
        info = new RequestInfo();
        info.setParamJson("{'name':'aaa2'}");
        info.setTarget(3001);
        info.setToken("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1MDkzNjQxNjIsInN1YiI6IntcInVzZXJOYW1lXCI6XCIxXCIsXCJ1c2VySWRcIjoxLFwiZW1haWxcIjpcIjFcIixcImNyZWF0ZURhdGVcIjoxNTA5MDYyNDAwMDAwfSIsImV4cCI6MTUwOTM2Nzc2Mn0.0owK_Umy5FhrnLpWDZw_fQqbgUubwh-qcQsfS7oL03E");
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
        ResponseData response = handler.handleRequest(info, ctx);
        System.out.println(JSON.toJSONString(response));
    }
}
