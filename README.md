# Activiti Demo

这是一个基于 Activiti 的二级审批流程示例项目。项目通过 standalone in-memory `ProcessEngine` 启动流程引擎，部署 `second_approve.bpmn20.xml` 流程定义，并在命令行中依次完成提交、主管审批和 HR 审批任务。

## 技术栈

- Java 17
- Maven
- Spring Boot 4.1.0
- Activiti Engine 7.1.0.M6
- H2 Database 2.4.240
- Logback 1.5.38
- Guava 33.6.0-jre
- JUnit Jupiter 6.1.2

## 项目结构

```text
src/main/java/com/niu/activiti/helloworld
├── DemoMain.java                  # 命令行 demo 启动入口
├── constant                       # 流程任务和审批人常量
└── listener                       # Activiti 任务监听器

src/main/resources
├── logback.xml                    # 日志配置
└── second_approve.bpmn20.xml      # 二级审批 BPMN 流程定义

src/test/java/com/niu/activiti/helloworld
├── ConfigTest.java                # Activiti 配置创建测试
└── DemoMainStartupTest.java       # standalone in-memory 引擎启动冒烟测试
```

## 环境要求

请确认本机已安装：

```bash
java -version
mvn -version
```

推荐使用 Java 17 或更高版本。项目在 `pom.xml` 中通过 `java.version` 指定 Java 17。

## 构建与测试

运行全部测试：

```bash
mvn test
```

`DemoMainStartupTest` 会创建 standalone in-memory Activiti `ProcessEngine`，并检查 `RepositoryService`、`RuntimeService` 和 `TaskService` 是否可用，用于验证升级后的核心依赖是否仍能启动流程引擎。

## 运行 Demo

执行主类：

```bash
mvn -DskipTests compile exec:java -Dexec.mainClass=com.niu.activiti.helloworld.DemoMain
```

也可以在 IDE 中直接运行：

```text
com.niu.activiti.helloworld.DemoMain
```

启动后，程序会：

1. 创建 standalone in-memory Activiti 流程引擎。
2. 部署 `second_approve.bpmn20.xml` 流程定义。
3. 启动二级审批流程实例。
4. 在命令行中按任务表单提示输入审批信息。
5. 根据输入结果流转到主管审批、HR 审批、退回或结束节点。

## 命令行输入说明

流程中主要字段如下：

| 字段 | 含义 | 示例 |
| --- | --- | --- |
| `message` | 申请信息 | `请假一天` |
| `name` | 申请人姓名 | `张三` |
| `submitTime` | 提交日期 | `2026-07-21` |
| `submitType` | 是否提交 | `y` / `n` |
| `tlApprove` | 主管是否通过 | `y` / `n` |
| `tlMessage` | 主管备注 | `同意` |
| `hrApprove` | HR 是否通过 | `y` / `n` |
| `hrMessage` | HR 备注 | `归档` |

输入 `y` 表示通过或提交，输入 `n` 表示拒绝、取消或退回。

## 注意事项

- 这是一个命令行 demo，不是 Web 应用。
- 当前流程引擎使用内存模式，数据不会持久化到外部数据库。
- 升级到 Spring Boot 4 和 Activiti 7 preview 后，请优先执行 `mvn test` 确认依赖解析、编译和引擎启动均正常。
- 如果运行环境无法访问 Maven Central，需要先配置可用的 Maven 镜像或内部制品库。
