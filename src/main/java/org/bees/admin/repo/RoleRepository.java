package org.bees.admin.repo;

import org.bees.admin.entity.RoleEntity;
import org.spring.generic.repo.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends GenericRepository<RoleEntity> {

    Optional<RoleEntity> findByName(String name);
}
