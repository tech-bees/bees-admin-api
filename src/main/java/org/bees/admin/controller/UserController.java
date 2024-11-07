package org.bees.admin.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.bees.admin.entity.RoleEntity;
import org.bees.admin.entity.UserEntity;
import org.spring.generic.controller.GenericController;
import org.spring.generic.repo.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users", description = "User management api")
@RestController
@RequestMapping("/admin/users")
public class UserController extends GenericController<UserEntity> {

    public UserController(GenericRepository<UserEntity> genericRepository) {
        super(genericRepository);
    }

}
