package com.chrise.demo.service;

import com.chrise.demo.bean.SysSubsystem;
import com.chrise.demo.dao.CursorDao;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CursorServiceImp
 *
 * @author hanzhao
 * @date 2021/2/22
 */
@Service
public class CursorServiceImp implements CursorService {

    @Autowired
    private CursorDao cursorDao;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Override
    public List<SysSubsystem> test() {
        return cursorDao.select();
    }

    @Override
    public List testSqlSession() {
        List list = new ArrayList();
        SqlSession sqlSession = null;
        try {
            //1.手动创建sqlsession，管理sqlsession生命周期
            sqlSession = sqlSessionFactory.openSession();
            //2.执行查询获取迭代器
            Cursor<SysSubsystem> cursor = sqlSession.getMapper(CursorDao.class).selectFirst();
            cursor.forEach(sysSubsystem -> {
                list.add(sysSubsystem);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //3.手动关闭sqlsession
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return list;
    }

    @Override
    public List testTransactionTemplate() {
        List list = new ArrayList();
        //创建事务，并且结合callback保证线程安全
        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
        transactionTemplate.execute(status -> {
            try (Cursor<SysSubsystem> cursor = cursorDao.selectFirst()) {
                cursor.forEach(sysSubsystem -> {
                    list.add(sysSubsystem);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            //这个return是函数中返回值，并不是testTransactionTemplate方法的返回值
            return list;
        });
        return list;
    }

    @Override
    @Transactional
    public List testSampleTransactionTemplate() {
        //此方法有重大缺陷，如果在外部调用则会触发不了事务导致sqlsession查询完成就会关闭
        List list = new ArrayList();
        Cursor<SysSubsystem> cursor = cursorDao.selectFirst();
        cursor.forEach(sysSubsystem -> {
            list.add(sysSubsystem);
        });
        return list;
    }
}
