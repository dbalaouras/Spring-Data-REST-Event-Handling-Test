package gr.bytecode.restapp.configuration;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

/**
 * @author Dimi
 */
@Configuration
public class RestRepositoriesConfig extends RepositoryRestMvcConfiguration {
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration
     * #configureRepositoryRestConfiguration(org.springframework.data.rest.core.
     * config.RepositoryRestConfiguration)
     */
    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        super.configureRepositoryRestConfiguration(config);
        try {

            config.setBaseUri(new URI("/api"));

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
