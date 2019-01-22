package io.tool.full.stack.ppmtoolfullstack.repositories;

import io.tool.full.stack.ppmtoolfullstack.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author badrikant.soni on 22/01/19
 */
@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {
    @Override
    Iterable<Project> findAllById(Iterable<Long> iterable);
}
