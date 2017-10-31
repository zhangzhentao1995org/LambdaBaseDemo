package pers.zhentao.LambdaBaseDemo.DeleteRecord.handler;

import java.util.Date;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import pers.zhentao.LambdaBaseDemo.DeleteRecord.exception.DeleteRecordException;
import pers.zhentao.LambdaBaseDemo.DeleteRecord.response.ResponseData;
import pers.zhentao.LambdaBaseDemo.DeleteRecord.service.IDeleteRecordService;
import pers.zhentao.LambdaBaseDemo.DeleteRecord.service.impl.DeleteRecordServiceImpl;

/**
 * lambda处理程序
 *
 * @author zhangzhentao1995@163.com
 * 		   2017-10-30
 */
public class LambdaFunctionHandler implements RequestHandler<Integer, ResponseData> {

    public ResponseData handleRequest(Integer input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("[" + new Date() + "]id:" + input);
        IDeleteRecordService service = new DeleteRecordServiceImpl();
        try {
            service.deleteRecord(input);
        } catch (DeleteRecordException e) {
            return new ResponseData(ResponseData.RESPONSE_CODE_SYS_EXCEPTION, e.getMsg());
        }
        return new ResponseData(ResponseData.RESPONSE_CODE_SUCCESS, "delete success.");
    }

}
