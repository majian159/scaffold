# springboot-mybatis-scaffold

## 介绍
这是一个基于 SpringBoot + MyBatis + MyBatis Generator 的脚手架项目。  
脚手架默认实现了一个 `department` RESTful API，用来演示。

## 脚手架集成内容
- Spring Boot
- Spring Security
- MyBatis
- MyBatis Generator
- PageHelper
- Swagger-UI

## 目录结构
```sh
scaffold
├── scaffold-api    # REST API 模块
├── scaffold-common # 公共代码模块
└── scaffold-mbg    # MyBatis Generator 及生成的内容
```

## 环境
- JDK8
- IDEAJ
  - Lombok Plugin

## 脚手架配置

### 项目、程序包名称
使用 IDE 等工具进行批量全局替换。

### Spring
- 开启 @Async 线程池使用 `ForkJoinPool.commonPool()`
- `@EnableScheduling`
- `@EnableSwagger2`
- datasource
  - MySQL
  - hikari
    - 最小空闲连接 5
    - 连接超时 15秒
    - 最大连接池大小 20
- jackson
  - 不包含 null 值的属性

### MyBatis
 - Mapper扫描路径
   - 当前项目下的 dao/*.xml (项目中自定义的Mapper xml)
   - 全局 mapper/*.xml (用于mbg生成的Mapper xml)

### MBG
> 这边只是简化了 `generatorConfig.xml` 的配置，如有个性化需求可直接修改 `generatorConfig.xml`
> 
`scaffold-mbg/src/main/resources/generator.properties`
| 配置键                | 说明          | 默认值          | 
| -------------------- | ------------- | ------------- |
| jdbc.driverClass     | JDBC驱动类    | com.mysql.cj.jdbc.Driver |
| jdbc.connectionURL   | JDBC URL     | jdbc:mysql://localhost/scaffold?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai |
| jdbc.userId          | 数据库用户名   | root          |
| jdbc.password        | 数据库密码     | root          |
| common.basePackage   | 基础的包名     | com.scaffold.springboot.mybatis.mbg |
| common.basePath      | 基础的路径     | scaffold-mbg |

### Application
`scaffold-api/src/main/resources/application.yml`

| 配置键                   | 说明          | 默认值          | 
| ----------------------- | ------------- | ------------- |
| application.basePackage | 基础 package，用来简化项目改名后带来的负担    | com.scaffold.springboot.mybatis |

### RestControllerAdvice
当 Controller 抛出 `ServiceException`, `ApiException` 异常时会经由 Advice 自动包装成 `ApiErrorViewModel`, 并返回对应的 HttpStatusCode.
