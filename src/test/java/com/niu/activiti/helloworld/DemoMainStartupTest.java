package com.niu.activiti.helloworld;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Verifies that the upgraded dependencies can still create the standalone
 * in-memory Activiti process engine used by the demo entry point.
 */
class DemoMainStartupTest {

    @Test
    void shouldStartStandaloneInMemoryProcessEngine() {
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createStandaloneInMemProcessEngineConfiguration()
                .buildProcessEngine();

        try {
            assertNotNull(processEngine);
            assertNotNull(processEngine.getRepositoryService());
            assertNotNull(processEngine.getRuntimeService());
            assertNotNull(processEngine.getTaskService());
        } finally {
            processEngine.close();
        }
    }
}
