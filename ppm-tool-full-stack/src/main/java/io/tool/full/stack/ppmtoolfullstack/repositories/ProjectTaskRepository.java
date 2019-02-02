package io.tool.full.stack.ppmtoolfullstack.repositories;

import io.tool.full.stack.ppmtoolfullstack.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author badrikant.soni on 02/02/19
 */
@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
}
