package pers.zhentao.LambdaBaseDemo.AddRecord.handler;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import pers.zhentao.LambdaBaseDemo.AddRecord.dto.Record;
import pers.zhentao.LambdaBaseDemo.AddRecord.exception.AddRecordException;
import pers.zhentao.LambdaBaseDemo.AddRecord.response.ResponseData;
import pers.zhentao.LambdaBaseDemo.AddRecord.service.IAddRecordService;
import pers.zhentao.LambdaBaseDemo.AddRecord.service.impl.AddRecordServiceImpl;

/**
 * Lambda处理程序
 *
 * @author zhangzhentao1995@163.com
 * 		   2017-10-30
 */
public class LambdaFunctionHandler implements RequestHandler<Record, ResponseData> {

    public ResponseData handleRequest(Record input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("[" + new Date() + "]input:" + JSONObject.toJSONString(input));
        try {
            IAddRecordService service = new AddRecordServiceImpl();
            service.addRecord(input,context);
            return new ResponseData(ResponseData.RESPONSE_CODE_SUCCESS, "add success");
        } catch (AddRecordException e) {
            logger.log("[" + new Date() + "]exception:" + e.getMsg());
            return new ResponseData(ResponseData.RESPONSE_CODE_ERROR, "add failed.");
        }
    }

}
