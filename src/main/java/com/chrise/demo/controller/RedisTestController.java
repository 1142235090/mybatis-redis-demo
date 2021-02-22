package com.chrise.demo.controller;

import com.chrise.demo.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * RedisTestController
 *
 * @author hanzhao
 * @date 2021/2/22
 */
@RestController
@RequestMapping("redis")
public class RedisTestController {

    @Autowired
    private RedisUtils redis;

    @GetMapping
    public void test(){
        redis.set(UUID.randomUUID().toString(),"这是内容",3);
    };
}
