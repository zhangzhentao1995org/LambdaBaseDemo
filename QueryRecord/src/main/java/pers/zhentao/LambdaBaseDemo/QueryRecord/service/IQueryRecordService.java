package pers.zhentao.LambdaBaseDemo.QueryRecord.service;

import java.util.List;

import pers.zhentao.LambdaBaseDemo.QueryRecord.dto.Record;
import pers.zhentao.LambdaBaseDemo.QueryRecord.exception.QueryRecordException;

/**
 * 查询服务接口
 *
 * @author zhangzhentao1995@163.com
 * 		   2017-10-30
 */
public interface IQueryRecordService {
    /**
     * 查询全部
     * @return
     */
    public List<Record> queryAll() throws QueryRecordException;
}
