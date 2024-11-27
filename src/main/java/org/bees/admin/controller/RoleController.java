package org.bees.admin.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.bees.admin.entity.PermissionEntity;
import org.bees.admin.entity.RoleEntity;
import org.spring.generic.controller.GenericController;
import org.spring.generic.repo.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Roles", description = "Roles management api")
@RestController
@RequestMapping("/api/roles")
public class RoleController extends GenericController<RoleEntity> {

    public RoleController(GenericRepository<RoleEntity> genericRepository) {
        super(genericRepository);
    }

}
