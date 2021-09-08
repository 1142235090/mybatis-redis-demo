package com.chrise.demo;

import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.redisson.codec.SerializationCodec;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public Redisson redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.1.202:6379").setPassword("123456").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }

    /**
     * 主题订阅的方式
     * @param redisson
     * @return
     */
    @Bean
    public String redissonTopic(RedissonClient redisson){
        try{
            RTopic topic = redisson.getTopic("topic",new SerializationCodec());
            topic.addListener(new MessageListener<String>() {
                @Override
                public void onMessage(String s, String str) {
                    System.out.println("onMessage:"+s+"; Thread: "+Thread.currentThread().toString());
                }
            });
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "";
    }

}
