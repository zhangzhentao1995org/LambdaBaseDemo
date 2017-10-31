package pers.zhentao.LambdaBaseDemo.service;

import com.amazonaws.services.lambda.invoke.LambdaFunction;

import pers.zhentao.LambdaBaseDemo.dto.Record;
import pers.zhentao.LambdaBaseDemo.response.ResponseData;

/**
 * lambda接口
 *
 * @author zhangzhentao1995@163.com
 * 		   2017-10-30
 */
public interface LambdaFunctions {
    /**
     * 调用添加接口
     * 
     * @param record
     * @return
     */
    @LambdaFunction
    Object AddRecord(Record record);

    /**
     * 调用删除接口
     * 
     * @param id
     * @return
     */
    @LambdaFunction
    Object DeleteRecord(Integer id);

    /**
     * 调用查询接口
     * 
     * @param record
     * @return
     */
    @LambdaFunction
    Object QueryRecord(Record record);
}
