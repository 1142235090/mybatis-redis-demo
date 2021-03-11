package com.chrise.demo.controller;

import com.chrise.demo.bean.SysSubsystem;
import com.chrise.demo.service.CursorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * CursorController
 *
 * @author hanzhao
 * @date 2021/2/22
 */
@RestController
@RequestMapping("test")
public class CursorController {

    @Autowired
    private CursorService cursorService;

    @GetMapping
    public List<SysSubsystem> test() {
        return cursorService.test();
    }

    @GetMapping("sqlsession")
    public List testSlSession(){
        return cursorService.testSqlSession();
    }

    @GetMapping("transaction/template")
    public List testTransactionTemplate(){
        return cursorService.testTransactionTemplate();
    }

    @GetMapping("sample/transaction/template")
    public List testSampleTransactionTemplate(){
        return cursorService.testSampleTransactionTemplate();
    }
}
