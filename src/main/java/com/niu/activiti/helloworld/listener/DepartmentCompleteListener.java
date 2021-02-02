package com.niu.activiti.helloworld.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 部门审批后置监听
 *
 * @author [nza]
 * @version 1.0 [2021/02/01 17:14]
 * @createTime [2021/02/01 17:14]
 */
public class DepartmentCompleteListener implements TaskListener {

    private static final Logger log = LoggerFactory.getLogger(DepartmentCompleteListener.class);

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("部门审批完成");
    }
}
