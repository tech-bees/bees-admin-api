package org.bees.admin.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.bees.admin.entity.PersonalInfoEntity;
import org.bees.admin.entity.RoleEntity;
import org.spring.generic.controller.GenericController;
import org.spring.generic.repo.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users", description = "User personal info management api")
@RestController
@RequestMapping("/api/users/details")
public class UserDetailsController extends GenericController<PersonalInfoEntity> {

    public UserDetailsController(GenericRepository<PersonalInfoEntity> genericRepository) {
        super(genericRepository);
    }

}
