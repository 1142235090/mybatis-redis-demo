package com.chrise.demo.controller;

import com.chrise.demo.utils.RedisUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RTopic;
import org.redisson.codec.SerializationCodec;
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

    @Autowired
    private Redisson redisson;

    /**
     * 测试3秒后过期
     */
    @GetMapping
    public void test(){
        redis.set(UUID.randomUUID().toString(),"这是内容",3);
    };

    /**
     * 1.为了防止因为一直占用锁比如服务重启，所以要设置lock的超时时间
     * 2.防止逻辑错误要加catch finally释放锁
     * 3.超时时间不够程序运行可以做锁续命，使用redisson
     * @return
     */
    @GetMapping("/buy")
    public String buy(){
        String lockName = "buyLock";
//        // -------------------普通redis操作-----------------------------
//        //clientid是为了防止错误释放锁，进行认证
//        String clientId = UUID.randomUUID().toString();
//        //模拟商品
//        redis.set("stock",100);
//        //1.尝试获取锁，设置超时时间是防止一直占用锁
//        if(redis.setNX(lockName,clientId,10)){
//            return "当前获取锁失败,商品抢购失败！";
//        }
//        try{
//            //2.商品数量-1
//            redis.set("stock",100-1);
//        }catch (Exception e){
//            //这里加catch是为了防止如果出现逻辑异常引发死锁
//            e.printStackTrace();
//        }finally {
//            //只有锁持有人才可以释放锁
//            if(redis.get(lockName).equals(clientId)){
//                //3.释放锁
//                redis.delNX(lockName);
//            }
//        }
//        return "购买成功！";
//        // -------------------------------------------------------------------

        // -----------------------------redisson操作------------------------------
        RLock redisLock = redisson.getLock(lockName);//创建锁
        redisLock.lock();//加锁,默认10s超时，自动续命，自旋锁
        try{
            //2.商品数量-1
            redis.set("stock",100-1);
        }catch (Exception e){
            //这里加catch是为了防止如果出现逻辑异常引发死锁
            e.printStackTrace();
        }finally {
            //3.释放锁
            redisLock.unlock();
        }
        return "购买成功！";
        // -----------------------------redisson操作------------------------------
    }

    /**
     * 测试redis主题发布消息
     */
    @GetMapping("/topic/put")
    public void putTopic(){
        try{
            RTopic topic1 = redisson.getTopic("topic", new SerializationCodec());
            topic1.publish("测试主题消息发布1");
            topic1.publish("测试主题消息发布1");
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {
            redisson.shutdown();
        }
    };
}
