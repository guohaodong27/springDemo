[restController]:

[springBootApplication]:

## Introduction
###springBoot如何工作

* Spring Boot会根据使用@EnableAutoConfiguration批注添加到项目中的依赖项自动配置应用程序。
* spring boot应用程序的入口点是包含@SpringBootApplication注释和main方法的类。
* Spring Boot使用@ComponentScan注释自动扫描项目中包含的所有组件

### 常用注解
| 注解                     | 作用                   | 位置       |
| ------------------------ | ---------------------- | ---------- |
| @EnableAutoConfiguration | 自动配置springBoot程序 | 主类文件中 |
| @SpringBootApplication   | 自动配置/扫描/                  |            |

## [初始化(Initializr)](https://start.spring.io/)
### 编写REST端点
1. 在类中添加[@RestController][restController]
2. 使用[@RequestMapping][requestMapping]拦截URL

## 部署到Tomcat

1. 添加依赖[spring-boot-starter-web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
2. 更改打包方式(war)
3. 设置main类
   ```xml
   <start-class>[类名]</start-class>
   ```
   ```text
   mainClassName="[类名]"
   ```
4. 打包应用程序
   1. mvn package
   2. gradle clean build

## 依赖注入

**在被[@SpringBootApplication][springBootApplication]注解的类里相当于使用{@EnableAutoConfiguration,@ComponentScan}**

1. 可以直接使用@Bean注释声明Bean
2. 可以使用@AutoWired自动织入

## 运行器(Runner)
void run(String...arg0)

应用程序启动后执行代码的接口

## @Value
    用于读取Java代码中的属性值
    @Value("$(property)")

> @ConfigurationProperties("prefix")
> 自动读取属性值,但**需要get/set方法**
## 日志框架

### 常用的日志框架
* logback
* log4j
* log4j2
* commons logging
* slf4j

> **springBoot中使用slf4j+logback**


## 配置文件
springBoot的配置文件是resource文件夹下的application\*.properties文件


## 整合Mybatis

1. 导入依赖(mybatis-spring-boot-starter,mysql-connector-java)
2. 配置连接信息

    |属性|key|
    |---|---|
    |驱动|datasource.driver|
    |用户名|datasource.username|
    |密码|datasource.password|

