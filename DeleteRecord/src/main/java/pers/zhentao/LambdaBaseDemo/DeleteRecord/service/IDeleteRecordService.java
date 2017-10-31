package pers.zhentao.LambdaBaseDemo.DeleteRecord.service;

import pers.zhentao.LambdaBaseDemo.DeleteRecord.exception.DeleteRecordException;

/**
 * 删除服务接口
 *
 * @author zhangzhentao1995@163.com
 * 		   2017-10-30
 */
public interface IDeleteRecordService {
    /**
     * 删除记录接口
     * @param id 记录id
     * @throws DeleteRecordException
     */
    public void deleteRecord(Integer id) throws DeleteRecordException;
}
