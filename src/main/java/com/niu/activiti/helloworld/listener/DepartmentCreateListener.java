package com.niu.activiti.helloworld.listener;

import com.niu.activiti.helloworld.constant.AssignConst;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 部门审批前置监听
 *
 * @author [nza]
 * @version 1.0 [2021/02/01 17:10]
 * @createTime [2021/02/01 17:10]
 */
public class DepartmentCreateListener implements TaskListener {

    private static final Logger log = LoggerFactory.getLogger(DepartmentCreateListener.class);

    @Override
    public void notify(DelegateTask delegateTask) {
        // 动态指定代办人
        delegateTask.setAssignee(AssignConst.DEPARTMENT);

        log.info("部门审批创建");
    }
}
