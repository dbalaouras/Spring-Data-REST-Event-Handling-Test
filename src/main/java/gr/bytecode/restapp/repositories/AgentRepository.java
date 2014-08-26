package gr.bytecode.restapp.repositories;

import gr.bytecode.restapp.model.Agent;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * @author Dimi
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "agents", path = "/agents")
public interface AgentRepository extends PagingAndSortingRepository<Agent, Long> {
    // no implementation required; Spring Data will create a basic Repository
}
