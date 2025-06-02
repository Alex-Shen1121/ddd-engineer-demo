# ddd-engineer-demo

DDD（领域驱动设计）工程模板，用于快速搭建符合DDD架构的Java项目，包含领域层、应用层、基础设施层和接口层的标准结构，集成常用中间件和工具，支持快速开发和扩展。

## 项目结构

- `ddd-engineer-api`：接口层，定义对外接口和DTO
- `ddd-engineer-application`：应用层，协调领域层完成业务用例
- `ddd-engineer-domain`：领域层，包含领域模型、领域服务、仓库接口等核心业务逻辑
- `ddd-engineer-infrastructure`：基础设施层，提供数据库访问、消息队列、缓存等技术实现
- `ddd-engineer-starter`：启动器，包含配置、日志、依赖管理等公共模块
- `ddd-engineer-trigger`：触发器，用于定时任务、事件监听等触发型功能

## 功能特性

- 基于Spring Boot 3.x构建，支持快速开发
- 集成MyBatis-Plus作为ORM框架，简化数据库操作
- 使用Spring Cloud Alibaba生态，支持服务治理、配置中心等
- 内置SkyWalking日志收集配置（`logback-spring.xml`），支持分布式追踪
- 提供标准的DDD分层结构，引导正确的领域建模实践
- 包含开发环境（dev）和生产环境（prod）的配置模板

## 安装步骤

1. 克隆项目到本地：`git clone https://github.com/your-repo/ddd-engineer-demo.git`
2. 使用Maven构建项目：`mvn clean install`
3. 配置数据库、Redis等中间件连接信息（修改`application-dev.yml`或`application-prod.yml`）
4. 启动应用：`java -jar ddd-engineer-starter/target/ddd-engineer-starter-1.0.0.jar`

## 日志配置说明

项目使用Logback作为日志框架，配置文件位于`src/main/resources/logback-spring.xml`，支持：
- 控制台输出（INFO级别及以上）
- 文件滚动输出（INFO和ERROR日志分开存储，自动按日期和大小归档）
- SkyWalking日志收集（通过`grpc-log` appender上报分布式追踪ID）
- 异步日志输出（提升性能，避免I/O阻塞）

## 贡献指南

欢迎提交Issue和Pull Request，贡献代码或文档。提交前请确保：
- 代码符合项目编码规范（参考`.idea/codeStyles`）
- 添加必要的单元测试和注释
- 更新README文档以反映变更内容

## 许可证

本项目采用MIT许可证，详见[LICENSE](LICENSE)文件。