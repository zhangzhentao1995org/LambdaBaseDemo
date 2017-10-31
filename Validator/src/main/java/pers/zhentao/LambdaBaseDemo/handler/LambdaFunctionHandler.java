package pers.zhentao.LambdaBaseDemo.handler;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.lambda.AWSLambdaAsyncClientBuilder;
import com.amazonaws.services.lambda.invoke.LambdaInvokerFactory;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import pers.zhentao.LambdaBaseDemo.dto.Record;
import pers.zhentao.LambdaBaseDemo.dto.RequestInfo;
import pers.zhentao.LambdaBaseDemo.dto.User;
import pers.zhentao.LambdaBaseDemo.response.ResponseData;
import pers.zhentao.LambdaBaseDemo.service.LambdaFunctions;
import pers.zhentao.LambdaBaseDemo.util.TokenUtil;

/**
 * lambda处理程序
 *
 * @author zhangzhentao1995@163.com
 * 		   2017-10-30
 */
public class LambdaFunctionHandler implements RequestHandler<RequestInfo, ResponseData> {
    private static String awsAccessKeyId = null;
    private static String awsSecretAccessKey = null;
    private static String regionName = null;
    private static String jwtSecret = null;

    public ResponseData handleRequest(RequestInfo input, Context context) {
        LambdaLogger logger = context.getLogger();
        awsAccessKeyId = System.getenv("ACCESS_KEY");
        awsSecretAccessKey = System.getenv("SECRET_KEY");
        regionName = System.getenv("REGION");
        jwtSecret = System.getenv("JWT_SECRET");
        if(awsAccessKeyId == null || awsSecretAccessKey == null || regionName == null) {
            return new ResponseData(ResponseData.RESPONSE_CODE_ERROR, "aws config is null.");
        }
        if(jwtSecret == null) {
            return new ResponseData(ResponseData.RESPONSE_CODE_ERROR, "jwtSecret is null.");
        }
        logger.log("[" + new Date() + "]RequestInfo:" + JSONObject.toJSONString(input));
        User user = TokenUtil.validateToken(input.getToken(), jwtSecret);
        logger.log("[" + new Date() + "]user:" + JSONObject.toJSONString(user));
        if (user == null) {
            return new ResponseData(ResponseData.RESPONSE_CODE_ERROR, "invalid token.");
        } else {
            AWSCredentials credentials = new BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKey);
            AWSStaticCredentialsProvider provider = new AWSStaticCredentialsProvider(credentials);
            LambdaFunctions functions = LambdaInvokerFactory.builder().lambdaClient(
                    AWSLambdaAsyncClientBuilder.standard().withCredentials(provider).withRegion(regionName).build())
                    .build(LambdaFunctions.class);
            switch (input.getTarget()) {
            case RequestInfo.TARGET_ADD_RECORD:
                try {
                    Record record = JSONObject.parseObject(input.getParamJson(), Record.class);
                    record.setCreatedBy(user.getUserId());
                    Object addRecordResponse = functions.AddRecord(record);
                    logger.log("[" + new Date() + "]invoke addRecordFunction:"
                            + JSONObject.toJSONString(addRecordResponse));
                    return new ResponseData(
                            JSONObject.parseObject(JSONObject.toJSONString(addRecordResponse)).getInteger("code"),
                            JSONObject.parseObject(JSONObject.toJSONString(addRecordResponse)).getString("message"));
                } catch (Exception e) {
                    logger.log("[" + new Date() + "]exception:" + e.getMessage());
                    return new ResponseData(ResponseData.RESPONSE_CODE_ERROR, "invalid input.");
                }
            case RequestInfo.TARGET_DELETE_RECORD:
                Object deleteRecordResponse = functions.DeleteRecord(Integer.parseInt(input.getParamJson()));
                logger.log("[" + new Date() + "]invoke deleteRecordFunction:"
                        + JSONObject.toJSONString(deleteRecordResponse));
                return new ResponseData(
                        JSONObject.parseObject(JSONObject.toJSONString(deleteRecordResponse)).getInteger("code"),
                        JSONObject.parseObject(JSONObject.toJSONString(deleteRecordResponse)).getString("message"));
            case RequestInfo.TARGET_QUERY_RECORD:
                try {
                    Record record2 = JSONObject.parseObject(input.getParamJson(), Record.class);
                    Object queryRecordResponse = functions.QueryRecord(record2);
                    logger.log("[" + new Date() + "]invoke queryRecordFunction:"
                            + JSONObject.toJSONString(queryRecordResponse));
                    Integer code = JSONObject.parseObject(JSONObject.toJSONString(queryRecordResponse))
                            .getInteger("code");
                    if (code.equals(ResponseData.RESPONSE_CODE_SUCCESS)) {
                        return new ResponseData(code,
                                JSONObject.parseObject(JSONObject.toJSONString(queryRecordResponse)).getObject("rows",
                                        List.class));
                    }
                } catch (Exception e) {
                    logger.log("[" + new Date() + "]exception:" + e.getMessage());
                    return new ResponseData(ResponseData.RESPONSE_CODE_ERROR, "invalid input.");
                }
            default:
                return new ResponseData(ResponseData.RESPONSE_CODE_ERROR, "invalid target.");
            }
        }
    }

}
