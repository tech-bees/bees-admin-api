package org.bees.admin.repo;

import org.bees.admin.entity.PermissionEntity;
import org.spring.generic.repo.GenericRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends GenericRepository<PermissionEntity> {

    Optional<PermissionEntity> findByName(@Param("name") String name);

}
