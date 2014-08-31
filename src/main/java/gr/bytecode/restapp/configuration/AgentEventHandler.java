package gr.bytecode.restapp.configuration;

import gr.bytecode.restapp.model.Agent;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

/**
 * @author Dimi
 *
 */
@Component
@RepositoryEventHandler(Agent.class)
public class AgentEventHandler extends AbstractRepositoryEventListener<Agent> {

    public static final String NEW_NAME = "**modified**";

    /**
     * Called before {@link Agent} is persisted
     * 
     * @param agent
     */
    @HandleBeforeCreate
    public void handleBeforeSave(Agent agent) {

        agent.setName(NEW_NAME);
        System.out.println("[Before Save] Setting Agent name to " + agent.getName());

    }

}
