package org.bees.admin.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.bees.admin.dto.PermissionDto;
import org.bees.admin.entity.PermissionEntity;
import org.bees.admin.service.PermissionService;
import org.spring.generic.controller.GenericController;
import org.spring.generic.repo.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Permissions", description = "Permission management api")
@RestController
@RequestMapping("/admin/permissions")
public class PermissionController extends GenericController<PermissionEntity> {

    @Autowired
    PermissionService permissionService;


    public PermissionController(GenericRepository<PermissionEntity> genericPerRepository) {
        super(genericPerRepository);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> getPermissionByName(@PathVariable(name = "name") String name){
        return new ResponseEntity< PermissionDto >(permissionService.findPermissionByName(name), HttpStatus.OK);
    }

}
