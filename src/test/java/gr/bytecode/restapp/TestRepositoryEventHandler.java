package gr.bytecode.restapp;


import static org.junit.Assert.*;
import gr.bytecode.restapp.configuration.AgentEventHandler;
import gr.bytecode.restapp.model.Agent;
import gr.bytecode.restapp.repositories.AgentRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestRepositoryEventHandler {

    @Autowired
    AgentRepository agentRepository;

    /**
     *
     */
    @Test
    public void testEventHandler() {

        // create and save the Agent
        Agent agent = new Agent();
        agent.setName("some name");
        Agent fetchedAgent = agentRepository.save(agent);

        // entity should be saved with a new name as a result of the Event Handler
        assertNotNull(fetchedAgent.getId());
        assertEquals(AgentEventHandler.NEW_NAME, fetchedAgent.getName());

    }
}
