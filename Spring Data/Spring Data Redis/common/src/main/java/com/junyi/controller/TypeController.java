package com.junyi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis 常用数据类型的 demo
 * <p/>
 * 包括 String、list、set、hash
 * @time: 2021/2/1 11:42
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    StringRedisTemplate redisTemplate;

    /** Redis String 类型 */
    @RequestMapping("/string")
    public String stringRedis(@RequestParam String key, @RequestParam String value) {
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        //添加
        opsForValue.set(key, value);
        //添加并设置过期时间
        opsForValue.set(key + "expiration", value,120, TimeUnit.SECONDS);
        //自增
        opsForValue.increment(key,1);
        opsForValue.set("apple", "bad");
        //获取重新设置value
        opsForValue.getAndSet("apple","good");
        //追加到字符串的末尾
        opsForValue.append("apple","123");
        //获取字符长度
        System.out.println(opsForValue.size(key));;

        return opsForValue.get(key);
    }

    /** Redis Hash 类型 */
    @GetMapping("/hash")
    public Map<Object, Object>  hashRedis(){
        //添加单个
        redisTemplate.opsForHash().put("food","apple","10");
        //点加多个
        Map<String,Object> map = new HashMap<>();
        map.put("id","003");
        map.put("name","durian");
        map.put("count", "1");
        redisTemplate.opsForHash().putAll("durian",map);

        //键集合
        Set<Object> keys =  redisTemplate.opsForHash().keys("food");
        System.out.println("keys:"+keys);
        //value集合
        List<Object> values =  redisTemplate.opsForHash().values("food");
        System.out.println("values:"+values);
        //遍历map
        Cursor<Map.Entry<Object,Object>> entryCursor  = redisTemplate.opsForHash().scan("food", ScanOptions.NONE);
        while (entryCursor.hasNext()){
            Map.Entry<Object,Object> entry =entryCursor.next();
            System.out.println("键："+entry.getKey()+"值："+entry.getValue());
        }
        //获取整个map
        return redisTemplate.opsForHash().entries("food");
    }

    /** Redis Set 类型 */
    @GetMapping("/set")
    public Set<String> setRedis(){
        String key = "food";
        //添加一个或者多个
        String[] ste = new String[]{"123","456","789","45","6"};
        redisTemplate.opsForSet().add(key, ste);
        //移除一个或多个
        ste= new String[]{"123"};
        redisTemplate.opsForSet().remove(key, ste);
        //遍历
        Cursor<String> cursor = redisTemplate.opsForSet().scan(key, ScanOptions.NONE);
        while (cursor.hasNext()){
            System.out.println("set成员元素："+cursor.next());
        }
        //获取所有元素
        return  redisTemplate.opsForSet().members(key);
    }

    /** Redis List 类型 */
    @GetMapping("/list")
    public List<String> listRedis(){
        //表头插入单个
        redisTemplate.opsForList().leftPush("left-list","java");
        //表头插入多个
        String [] arr = new String[]{"js","html","c#","C++"};
        redisTemplate.opsForList().leftPushAll("left-list",arr);
        //表尾插入单个
        redisTemplate.opsForList().rightPush("right-list","java");
        //表尾插入多个
        redisTemplate.opsForList().rightPushAll("right-list",arr);
        //设置位置
        redisTemplate.opsForList().set("right-list",0,"第一个");
        //删除:count> 0：删除等于从头到尾移动的值的元素。count <0：删除等于从尾到头移动的值的元素。count = 0：删除等于value的所有元素。
        redisTemplate.opsForList().remove("right-list",1,"js");//

        return  redisTemplate.opsForList().range("left-list",0,100);
    }

}
