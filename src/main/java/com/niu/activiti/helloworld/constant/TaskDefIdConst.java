package com.niu.activiti.helloworld.constant;

/**
 * 任务定义ID常量
 *
 * @author [nza]
 * @version 1.0 [2021/02/01 17:40]
 * @createTime [2021/02/01 17:40]
 */
public interface TaskDefIdConst {

    /**
     * 提交表单
     */
    String STEP_ONE = "submitForm";

    /**
     * 主管审批
     */
    String STEP_TWO = "tl_approve";

    /**
     * HR 审批
     */
    String STEP_THIRD = "hr_approve";
}
