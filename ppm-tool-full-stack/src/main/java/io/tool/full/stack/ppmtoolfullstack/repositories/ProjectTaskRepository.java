package io.tool.full.stack.ppmtoolfullstack.repositories;

import io.tool.full.stack.ppmtoolfullstack.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author badrikant.soni on 02/02/19
 */
@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

    List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);

    ProjectTask findByProjectSequence(String seqId);
}
