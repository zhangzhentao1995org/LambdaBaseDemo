package pers.zhentao.LambdaBaseDemo.QueryRecord.handler;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import pers.zhentao.LambdaBaseDemo.QueryRecord.dto.Record;
import pers.zhentao.LambdaBaseDemo.QueryRecord.exception.QueryRecordException;
import pers.zhentao.LambdaBaseDemo.QueryRecord.response.ResponseData;
import pers.zhentao.LambdaBaseDemo.QueryRecord.service.IQueryRecordService;
import pers.zhentao.LambdaBaseDemo.QueryRecord.service.impl.QueryRecordServiceImpl;

/**
 * Lambda处理程序
 *
 * @author zhangzhentao1995@163.com
 * 		   2017-10-30
 */
public class LambdaFunctionHandler implements RequestHandler<Record, ResponseData> {

    public ResponseData handleRequest(Record input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("[" + new Date() + "]Query:" + JSONObject.toJSONString(input));
        IQueryRecordService service = new QueryRecordServiceImpl();
        try {
            List<Record> list = service.queryAll();
            return new ResponseData(ResponseData.RESPONSE_CODE_SUCCESS,list);
        } catch (QueryRecordException e) {
            return new ResponseData(ResponseData.RESPONSE_CODE_SYS_EXCEPTION, e.getMsg());
        }
    }

}
