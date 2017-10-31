package pers.zhentao.LambdaBaseDemo.AddRecord.service;

import com.amazonaws.services.lambda.runtime.Context;

import pers.zhentao.LambdaBaseDemo.AddRecord.dto.Record;
import pers.zhentao.LambdaBaseDemo.AddRecord.exception.AddRecordException;

/**
 * 添加记录接口
 *
 * @author zhangzhentao1995@163.com
 * 		   2017-10-30
 */
public interface IAddRecordService {
    /**
     * 添加记录
     * @param record
     * @exception AddRecordException
     */
    public void addRecord(Record record, Context context) throws AddRecordException;
}
