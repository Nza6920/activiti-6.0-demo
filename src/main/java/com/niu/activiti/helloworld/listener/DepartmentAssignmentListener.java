package com.niu.activiti.helloworld.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务被分配时触发
 * 该事件只在bpmn流程配置了
 * <humanPerformer>
 * <resourceAssignmentExpression>
 * <formalExpression>xxx</formalExpression>
 * </resourceAssignmentExpression>
 * </humanPerformer>
 * 节点时生效
 * 或调用 taskService.claim() 方法时触发
 *
 * @author [nza]
 * @version 1.0 [2021/02/01 17:17]
 * @createTime [2021/02/01 17:17]
 */
public class DepartmentAssignmentListener implements TaskListener {

    private static final Logger log = LoggerFactory.getLogger(DepartmentAssignmentListener.class);

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("任务被指派给: {}", delegateTask.getAssignee());
    }
}
