package org.bees.admin.repo;

import org.bees.admin.entity.UserEntity;
import org.spring.generic.repo.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<UserEntity> {

    UserEntity findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
