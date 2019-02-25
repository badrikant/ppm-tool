package io.tool.full.stack.ppmtoolfullstack.repositories;

import io.tool.full.stack.ppmtoolfullstack.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author badrikant.soni on 22/01/19
 * Q. What is "@Respository" annotation and what it does ?
 * it is a Spring annotation that indicates that the decorated class is a repository.
 * A repository is a mechanism for encapsulating storage, retrieval, and search behavior which emulates a collection of objects.
 * It is a specialization of the @Component annotation allowing for implementation classes to be autodetected through classpath scanning
 */
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findByProjectIdentifier(String projectId);

    @Override
    Iterable<Project> findAll();

    Iterable<Project> findAllByProjectLeader(String username);
}
