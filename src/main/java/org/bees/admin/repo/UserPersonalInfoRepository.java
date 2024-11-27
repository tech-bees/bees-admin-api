package org.bees.admin.repo;

import org.bees.admin.entity.PersonalInfoEntity;
import org.bees.admin.entity.UserEntity;
import org.spring.generic.repo.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPersonalInfoRepository extends GenericRepository<PersonalInfoEntity> {

}
