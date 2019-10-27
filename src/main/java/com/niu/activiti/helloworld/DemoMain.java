package com.niu.activiti.helloworld;

import com.google.common.collect.Maps;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.form.DateFormType;
import org.activiti.engine.impl.form.StringFormType;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 启动类
 *
 * @PackgeName: com.niu.activiti.helloworld
 * @ClassName: DemoMain
 * @Author: nza
 * Date: 2019/10/26 20:25
 */
public class DemoMain {

    private static final Logger log = LoggerFactory.getLogger(DemoMain.class);

    public static void main(String[] args) throws ParseException {
        log.info("启动程序");

        // 创建流程引擎
        ProcessEngine processEngine = getProcessEngine();

        // 部署流程定义文件
        ProcessDefinition processDefinition = getProcessDefinition(processEngine);

        // 启动流程
        ProcessInstance processInstance = getProcessInstance(processEngine, processDefinition);

        // 处理流程任务
        processTask(processEngine, processInstance);

        log.info("结束程序");
    }

    private static void processTask(ProcessEngine processEngine, ProcessInstance processInstance) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        TaskService taskService = processEngine.getTaskService();
        while (processInstance != null && ! processInstance.isEnded()) {
            List<Task> taskList = taskService.createTaskQuery().list();         // 列出所有task
            log.info("待处理任务数量 [{}]", taskList.size());
            for (Task task : taskList) {
                log.info("待处理任务 [{}]", task.getName());
                FormService formService = processEngine.getFormService();

                Map<String, Object> variables = getMap(scanner, task, formService);     // 提取参数变量

                taskService.complete(task.getId(), variables);
                processInstance = processEngine
                        .getRuntimeService()
                        .createProcessInstanceQuery()
                        .processInstanceId(processInstance.getId())
                        .singleResult();
            }
        }

        scanner.close();
    }

    private static Map<String, Object> getMap(Scanner scanner, Task task, FormService formService) throws ParseException {
        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
        List<FormProperty> formProperties = taskFormData.getFormProperties();
        Map<String, Object> variables = Maps.newHashMap();
        for (FormProperty property : formProperties) {
            String line = null;
            if (property.getType() instanceof StringFormType) {
                log.info("清输入 {} ?", property.getName());
                line = scanner.nextLine();
                variables.put(property.getId(), line);
            } else if (property.getType() instanceof DateFormType) {
                log.info("清输入 {} ? 格式 {yyyy-MM-dd}", property.getName());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                line = scanner.nextLine();
                Date date = dateFormat.parse(line);
                variables.put(property.getId(), date);
            } else {
                log.info("类型暂不支持", property.getType());
            }
            log.info("您输入的内容是 [{}]", line);
        }
        return variables;
    }

    private static ProcessInstance getProcessInstance(ProcessEngine processEngine, ProcessDefinition processDefinition) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
        log.info("启动流程 [{}]", processInstance.getProcessDefinitionKey());
        return processInstance;
    }

    private static ProcessDefinition getProcessDefinition(ProcessEngine processEngine) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.addClasspathResource("second_approve.bpmn");
        Deployment deployment = deploymentBuilder.deploy();
        String deploymentId = deployment.getId();
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()
                .deploymentId(deploymentId)
                .singleResult();
        log.info("流程定义文件 [{}], 流程id [{}]", processDefinition.getName(), processDefinition.getId());
        return processDefinition;
    }

    private static ProcessEngine getProcessEngine() {
        ProcessEngineConfiguration cfg = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
        ProcessEngine processEngine = cfg.buildProcessEngine();
        String name = processEngine.getName();
        String version = processEngine.VERSION;

        log.info("流程引擎名称{}, 版本{}", name, version);
        return processEngine;
    }
}
