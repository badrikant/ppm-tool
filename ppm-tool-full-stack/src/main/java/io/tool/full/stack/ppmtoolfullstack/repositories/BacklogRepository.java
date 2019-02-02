package io.tool.full.stack.ppmtoolfullstack.repositories;

import io.tool.full.stack.ppmtoolfullstack.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author badrikant.soni on 02/02/19
 */
@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {

    Backlog findByProjectIdentifier(String projectIdentifier);
}
