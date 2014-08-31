package gr.bytecode.restapp.configuration;

import gr.bytecode.restapp.model.Agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

/**
 * @author Dimi
 *
 */
@Component
@RepositoryEventHandler(Agent.class)
public class AgentEventHandler {

    /**
     * A logger
     */
    Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public static final String NEW_NAME = "**modified**";


    /**
     * Called before {@link Agent} is persisted
     * 
     * @param agent
     */
    @HandleBeforeCreate
    public void handleBeforeCreates(Agent agent) {

        agent.setName(NEW_NAME);
        log.info("[Before Create] Setting Agent name to " + agent.getName());

    }

    /**
     * Called before {@link Agent} is persisted
     * 
     * @param agent
     */
    @HandleBeforeSave
    public void handleBeforeSave(Agent agent) {
        agent.setName(NEW_NAME + "..update");
        log.info("[Before Save] Setting Agent name to " + agent.getName());

    }
}
