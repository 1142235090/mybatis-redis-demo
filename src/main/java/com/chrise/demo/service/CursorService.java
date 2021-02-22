package com.chrise.demo.service;

import com.chrise.demo.bean.SysSubsystem;

import java.util.List;

/**
 * CursorService
 *
 * @author hanzhao
 * @date 2021/2/22
 */
public interface CursorService {

    /**
     * 测试是否可以执行查询
     */
    List<SysSubsystem> test();

    /**
     * 测试sqlsession方式
     * @return
     */
    List testSqlSession();

    /**
     * 测试事务方式
     * @return
     */
    List testTransactionTemplate();

    /**
     * 测试spring事务方式
     * @return
     */
    List testSampleTransactionTemplate();
}
