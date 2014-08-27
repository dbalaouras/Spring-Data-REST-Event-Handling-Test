package gr.bytecode.restapp;


import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import gr.bytecode.restapp.configuration.AgentEventHandler;
import gr.bytecode.restapp.repositories.AgentRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

/**
 * Unit test for simple App.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestRepositoryEventHandler {

    @Autowired
    AgentRepository agentRepository;


    /**
     * A {@link MockMvc} object to use for the test requests
     */
    protected MockMvc mockMvc;

    /**
     * A {@link WebApplicationContext} object to use for setting up Servlet filters
     */
    @Autowired
    private WebApplicationContext webApplicationContext;


    /**
     * Basic bootstraping of the integration test
     */
    @Before
    public final void baseSetUp() {

        mockMvc = webAppContextSetup(webApplicationContext).build();

        // clean up everything in the DB
        agentRepository.deleteAll();
    }

    /**
     * @throws Exception
     *
     */
    @Test
    public void testEventHandler() throws Exception {

        String agentName = "agent-0103";

        // Initiates the request
        ResultActions postAgentRequest =
                mockMvc.perform(post("/api/agents").content("{\"name\": \"" + agentName + "\"}"))
                        .andDo(MockMvcResultHandlers.print());

        // Check if the response code is 201 Created
        postAgentRequest.andExpect(status().is(201));

        // get the result
        MvcResult mvcResult = postAgentRequest.andReturn();

        // get the location of the new Agent
        String newAgentLocation = mvcResult.getResponse().getHeader("Location");

        // Initiate the request
        ResultActions getNewAgentRequest =
                mockMvc.perform(get(newAgentLocation)).andDo(MockMvcResultHandlers.print());

        // Check the returned data
        getNewAgentRequest.andExpect(jsonPath("$_links", notNullValue()))
                .andExpect(jsonPath("$name", notNullValue()))
                .andExpect(jsonPath("$name").value(AgentEventHandler.NEW_NAME));

    }
}
