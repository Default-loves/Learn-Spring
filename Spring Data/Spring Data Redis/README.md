 

RedisTemplate 包含了序列化(serialization)和连接管理(connection management)

使用 RedisTemplate 读写 Redis，序列化和反序列化使用的是 java 的实现

StringRedisTemplate 使用 String 序列化数据

使用于 Redis Repository 中 RedisTemplate 需要无事务

### Redis客户端
Redis客户端主要有两个：Jedis和Lettuce

两者的区别见。。。



### @CacheXXX相关
- @Cacheable：一般使用在查询方法
- @CachePut：一般使用在新增方法
- @CacheEvict：一般使用在更新或删除方法上

注解中的属性设置：
- value：缓存名称（必填），指定缓存的命名空间；
- key：用于设置在命名空间中的缓存key值，可以使用SpEL表达式定义；
- unless：条件符合则不缓存；
- condition：条件符合则缓存。