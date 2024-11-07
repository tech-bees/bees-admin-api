package org.bees.admin.service;


import org.bees.admin.dto.PermissionDto;
import org.bees.admin.entity.PermissionEntity;
import org.bees.admin.repo.PermissionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermissionServiceImpl implements  PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PermissionDto findPermissionByName(String name) {
        Optional<PermissionEntity> perEntity = permissionRepository.findByName(name);
        return modelMapper.map(perEntity, PermissionDto.class);
    }
}
