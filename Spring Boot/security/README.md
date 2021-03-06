

spring-boot-starter-security 组件的使用

### 认证和授权
认证（Authentication）：认证解决的问题通俗来说就是确定“你是谁”。一般是通过用户名+密码+盐来确定请求的身份

授权（Authorization）：授权解决的问题通俗来说就是确定“你能干什么事情”，通常来说是在认证完成之后进行授权，一般是通过定义”角色“来管理用户能够访问的服务器资源

### Spring Security
Spring Security通过添加一系列的过滤器来逐层处理请求，分别进行认证、授权和其他的安全访问控制

常用的过滤器有UsernamePasswordAuthenticationFilter、BasicAuthenticationFilter



## 配置账号密码的方法

### 默认

默认用户名是user，密码在程序启动的控制台有输出，是随机生成的密码

### 在yml文件中配置

```yaml
spring:
  security:
    user:
      name: abc
      password: abc@123
```

### 基于内存的用户信息存储方案

扩展类`WebSecurityConfigurerAdapter`，覆盖`configure`方法，获取`InMemoryUserDetailsManagerConfigurer`类在内存中保存用户的信息，这种方式主要用于测试



### 基于数据库的用户信息存储方案

也是扩展类`WebSecurityConfigurerAdapter`，覆盖`configure`方法，从数据库中的数据表中读取认证和授权信息

数据库中需要提前建好表，并且设置好用户数据

```mysql
create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
 
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
 
create unique index ix_auth_username on authorities (username,authority);
```

### 自定义用户信息方案

1. 创建用户实体MyUserDatils，实现接口`UserDetails`，设置账号密码等字段
2. 创建Repository，从数据库中根据用户名获取用户
3. 创建MyUserDetailsService，实现接口`UserDetailsService`，实现方法`loadUserByUsername`，获取用户信息
4. 最后，继承类`WebSecurityConfigurerAdapter`，实现`configure`方法，将我们创建的`MyUserDetailsService`添加到系统认证中



## 授权

### 配置WebSecurityConfigurerAdapter

创建类继承类`WebSecurityConfigurerAdapter`，实现方法`configure`，在方法里面配置授权信息

### 使用注解@PreAuthorize

在Controller的方法上添加注解，适合用于单独配置特殊的请求接口

注解的内容为SpEL表达式

```java
@RestController
@RequestMapping(value="orders")
public class OrderController {
    
    @PostMapping(value = "/")
    @PreAuthorize("hasRole(ROLE_ADMIN)")
    public void addOrder(@RequestBody Order order) {
    }
}
```

