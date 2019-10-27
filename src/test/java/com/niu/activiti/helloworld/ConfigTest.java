package com.niu.activiti.helloworld;

import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @PackgeName: com.niu.activiti.helloworld
 * @ClassName: ConfigTest
 * @Author: nza
 * Date: 2019/10/27 19:19
 */
public class ConfigTest {

    private static final Logger log = LoggerFactory.getLogger(ConfigTest.class);

    @Test
    public void testConfig1() {
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
        log.info("configuration [{}]", configuration.toString());
    }

    @Test
    public void testConfig2() {
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        log.info("configuration [{}]", configuration.toString());
    }
}
