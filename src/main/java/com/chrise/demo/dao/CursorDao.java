package com.chrise.demo.dao;

import com.chrise.demo.bean.SysSubsystem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.cursor.Cursor;

import java.util.List;

/**
 * CursorDao
 *
 * @author hanzhao
 * @date 2021/2/22
 */
@Mapper
public interface CursorDao{
    List<SysSubsystem> select();

    Cursor<SysSubsystem> selectFirst();
}
