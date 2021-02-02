package com.niu.activiti.helloworld.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务被删除时触发
 *
 * @author [nza]
 * @version 1.0 [2021/02/01 17:20]
 * @createTime [2021/02/01 17:20]
 */
public class DepartmentDeleteListener implements TaskListener {

    private static final Logger log = LoggerFactory.getLogger(DepartmentDeleteListener.class);

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("任务被删除");
    }
}
