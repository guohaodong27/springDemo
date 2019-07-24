# 整合mybatis

1. [添加依赖](springbootMybatis.md#添加依赖)
2. [配置druid连接池](springbootMybatis.md#配置druid连接池)
3. [注入druid配置](springbootMybatis.md#注入druid配置)

## 添加依赖

```xml
<dependencies>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>

<!--mybatis的starter-->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.0.0</version>
</dependency>

<!--mysql驱动-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>

<!--druid连接池-->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.12</version>
</dependency>

<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.1.10</version>
</dependency>
</dependencies>

```

## 配置druid连接池

```yaml
spring:
  datasource:
    username: root
    password: 
    url: jdbc:mysql://
    driver-class-name: com.mysql.jdbc.Driver
    
    # 指定自己使用的数据源
    type: com.alibaba.druid.pool.DruidDataSource

    # DruidDataSource 其他属性配置
    druid:
     initialSize: 5
     minIdle: 5
     maxActive: 20
     maxWait: 60000
     timeBetweenEvictionRunsMillis: 60000
     minEvictableIdleTimeMillis: 300000
     validationQuery: SELECT 1 FROM DUAL
     testWhileIdle: true
     testOnBorrow: false
     testOnReturn: false
     poolPreparedStatements: true
     maxPoolPreparedStatementPerConnectionSize: 20
     useGlobalDataSourceStat: true
     filter:
      stat:
       enabled: true
       log-slow-sql: true
      wall:
       enabled: true
```

## 注入druid配置

```java

@Configurable
public class DruidConfig {
    @Bean
    
    /**
    * 将application.yaml中的prefix为spring.datasource.druid注入
    */
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource dataSourceTwo() {
        return DruidDataSourceBuilder.create().build();
    }
    /**
     * 配置一个管理后台的Servlet
     *
     * @return 
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();

        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "admin");
        initParams.put("allow", "");//默认就是允许所有访问

        bean.setInitParameters(initParams);
        return bean;
    }
    /**
     * 配置一个web监控的filter
     *
     * @return 
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParams = new HashMap<>();
        //通过 localhost:8080/druid/ 可以访问到监控后台
        initParams.put("exclusions", "*.js,*.css,/druid/*");

        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean;
    }
}
```
---
## 配置Mybatis

### 使用注解

1. 定义Mapper接口
```java

@Mapper
public interface DeptMapper {

    @Select("select * from dept")
    List<Dept> selectAll();
}
```

> 遇到NPE有可能是配置文件没有正确写入数据源
