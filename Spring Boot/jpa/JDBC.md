JDBC规范中个几个重要的概念：

- DriverManager：负责加载各种不同的驱动程序
- DataSource：由于创建销毁一个连接有较大的系统开销，DataSource可以认为是一个连接池，负责维护与数据库的连接，返回给应用程序连接（Connection）
- Connection：应用程序与数据库的连接，可以理解是一个长连接的会话，负责传递应用程序与数据库的数据
- ResultSet：数据库执行返回给应用程序的结果集



### 原生JDBC访问数据库

使用JDBC规范访问DB

```java
// 创建池化的数据源
PooledDataSource dataSource = new PooledDataSource ();
// 设置 MySQL Driver
dataSource.setDriver ("com.mysql.jdbc.Driver");
// 设置数据库 URL、用户名和密码
dataSource.setUrl ("jdbc:mysql://localhost:3306/test");
dataSource.setUsername("root");
dataSource.setPassword("root");
// 获取连接
Connection connection = dataSource.getConnection();
 
// 执行查询
PreparedStatement statement = connection.prepareStatement ("select * from user");
// 获取查询结果进行处理
ResultSet resultSet = statement.executeQuery();
while (resultSet.next()) {
	…
}
 
// 关闭资源
statement.close();
resultSet.close();
connection.close();
```



### JdbcTemplate

JdbcTemplate是Spring提供的基于JDBC规范实现的数据库访问的模板工具类

JdbcTemplate使用了设计模式模板方法，将固定的流程方法写好，具体的操作执行通过回调机制来完成



### Repository

JdbcTemplate还是偏向于底层，操作繁琐

Spring Data JPA提供了JPA Repository，更加简化了应用程序访问获取数据库的操作

通过实现接口`JpaRepository`，即能够提供增删改查等操作

JPA规范和JDBC规范类似，也是一种对象关系映射ORM，JPA规范的实现主要就是Hibernate和Spring Data JPA



还可以通过@Query注解来指定Sql查询语句，使用的是类似SQL的语句JPQL，值得注意的是`from`后面的是对象，而不是表的名称

```java
@Query("select a from Account a where a.userName = ?1") 
Account findByUserName(String userName);
```



使用方法名衍生查询，通过定义符合语义的方法名，Spring Data JPA框架会自动识别方法并且正确执行对应的语句

![image-20210309155232988](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210309155232988.png)

![image-20210309155438140](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210309155438140.png)



对于同时有@Query注解和方法名衍生方法，Spring Data 默认使用的是 CREATE_IF_NOT_FOUND 策略，也就是说系统会先查找 @Query 注解，如果查到没有，会再去找与方法名相匹配的查询。