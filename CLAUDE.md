# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 项目概述

芋道 (Yudao) — 基于 Spring Boot 3.5 + JDK 17 的多模块快速开发平台。当前分支为 `master-jdk17`。

## 构建与开发命令

- `mvn clean install -DskipTests` — 构建所有启用的模块
- `mvn test` — 运行全部单元测试（JUnit 5）
- `mvn -pl yudao-module-infra -am test` — 运行单个模块及其依赖的测试
- `mvn -pl yudao-server -am spring-boot:run -Dspring-boot.run.profiles=local` — 启动本地服务
- `mvn -pl yudao-server -am package -DskipTests` — 打包 server jar

## 多模块架构

```
yudao-dependencies/          # BOM — 统一依赖版本管理
yudao-framework/             # 框架层 — 15+ 个 Spring Boot Starter
  yudao-common/              #   公共工具、枚举、异常、校验
  yudao-spring-boot-starter-web/         # Web 层：全局响应、异常处理、XSS、API 加密
  yudao-spring-boot-starter-security/    # 安全层：Spring Security + Token + RBAC
  yudao-spring-boot-starter-mybatis/     # 持久层：MyBatis Plus + 多数据源 + 数据权限
  yudao-spring-boot-starter-redis/       # 缓存层：Redisson + Redis Stream
  yudao-spring-boot-starter-protection/  # 服务保障：分布式锁、幂等、限流
  yudao-spring-boot-starter-mq/          # 消息队列：Redis/RocketMQ/RabbitMQ/Kafka
  yudao-spring-boot-starter-job/         # 定时任务：Quartz
  yudao-spring-boot-starter-biz-tenant/  # 多租户
  yudao-spring-boot-starter-biz-data-permission/ # 数据权限
  yudao-spring-boot-starter-test/        # 测试基类（BaseDbUnitTest 等）
  ...（其余 starter 按需）
yudao-server/                # Spring Boot 入口，空壳容器，聚合所有 module 依赖
yudao-module-system/         # 系统功能（用户、角色、菜单、租户、字典等）
yudao-module-infra/          # 基础设施（代码生成、文件、配置、定时任务、WebSocket 等）
yudao-module-bpm/            # 工作流程（Flowable 7）
yudao-module-pay/            # 支付系统
yudao-module-mall/           # 商城系统
yudao-module-member/         # 会员中心
yudao-module-mp/             # 微信公众号
yudao-module-crm/            # CRM
yudao-module-erp/            # ERP
yudao-module-mes/            # MES 制造执行
yudao-module-ai/             # AI 大模型
yudao-module-iot/            # IoT 物联网
yudao-module-report/         # 大屏报表
yudao-module-freight/        # 物流（新增模块）
```

**模块启用/禁用：** 修改根 `pom.xml` 的 `<modules>` 和 `yudao-server/pom.xml` 的 `<dependencies>`，两者须同步。

**入口类：** `YudaoServerApplication` 使用 `@SpringBootApplication(scanBasePackages = {"${yudao.info.base-package}.server", "${yudao.info.base-package}.module"})`，其中 `yudao.info.base-package=cn.iocoder.yudao` 在 `application.yaml` 中定义。

## 模块内部包结构（以 yudao-module-system 为例）

```
cn.iocoder.yudao.module.system/
  controller/          # REST 控制器，返回 CommonResult
  controller/admin/    #   管理后台接口（/admin-api/）
  controller/app/      #   用户 App 接口（/app-api/）
  service/             # Service 接口 + ServiceImpl 实现
  dal/                 # 数据访问层
    dataobject/        #   DO（Data Object）
    mysql/             #   Mapper 接口
  convert/             # MapStruct 转换器（*Convert 接口）
  enums/               # 模块枚举
  api/                 # 对外 API 接口声明（供其他模块 Feign 调用）
  framework/           # 模块内框架配置
  job/                 # 定时任务
  mq/                  # 消息队列消费者/生产者
```

## 技术栈与关键约定

- **Java 17** + Spring Boot 3.5，UTF-8 编码，4 空格缩进
- **Lombok** + **MapStruct**（Convert 接口，编译期生成实现）
- **MyBatis Plus**：DO 使用 `@TableName`，Mapper 继承 `BaseMapper<XxxDO>`
- **多数据源**：`dynamic-datasource` + Druid 连接池，`application-local.yaml` 中配置
- **多数据库支持**：MySQL / Oracle / PostgreSQL / SQL Server / 达梦 / KingbaseES，SQL 脚本在 `sql/<数据库类型>/`
- **REST 规范**：Controller 返回 `CommonResult<T>`，使用 `success(...)` 静态方法
- **Swagger**：Springdoc + Knife4j，`/swagger-ui` 访问
- **安全模型**：Spring Security + Token（Redis 存储），`yudao-spring-boot-starter-security`
- **多租户**：SaaS 架构，通过 `yudao-spring-boot-starter-biz-tenant` 透明拦截，`yudao.tenant.enable` 控制开关

## 命名后缀约定

| 类型 | 后缀 | 示例 |
|------|------|------|
| Controller | `*Controller` | `UserController` |
| Service 接口 | `*Service` | `UserService` |
| Service 实现 | `*ServiceImpl` | `UserServiceImpl` |
| Mapper | `*Mapper` | `UserMapper` |
| 数据对象 | `*DO` | `UserDO` |
| 请求 VO | `*ReqVO`，`*PageReqVO` | `UserReqVO` |
| 响应 VO | `*RespVO` | `UserRespVO` |
| MapStruct 转换器 | `*Convert` | `UserConvert` |

## 配置体系

- `application.yaml` — 通用配置，`spring.profiles.active: local`
- `application-local.yaml` — 本地开发（数据库、Redis、MQ 连接信息），**含敏感信息，不提交到版本库**
- `application-dev.yaml` — 开发/测试环境
- 配置前缀：`yudao.*` 为平台自定义配置项

## 测试规范

- JUnit 5 + Mockito，测试类继承 `BaseDbUnitTest` 或相关基类
- 测试命名：`ClassNameTest`，方法命名：`testXxx_success`、`testXxx_fieldInvalid`
- Fixtures 放在 `src/test/resources/`
- 单模块测试：`mvn -pl <module> -am test`

## 数据库初始化

SQL 脚本按数据库类型分目录存放（`sql/mysql/`、`sql/postgresql/` 等）。首次启动需要执行对应数据库的初始化脚本。

## 注意事项

- AGENTS.md 中的 "Java 8 兼容" 描述已过时，当前分支使用 **JDK 17**
- 直接编辑根 `pom.xml` 和 `yudao-server/pom.xml` 来启用/禁用业务模块，两者须同步
- 不要提交 `application-local.yaml` 中的实际密钥和密码
