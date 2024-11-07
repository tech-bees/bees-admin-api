package org.bees.admin.service;

import org.bees.admin.dto.RoleDto;
import org.bees.admin.dto.UserDto;
import org.bees.admin.entity.PermissionEntity;
import org.bees.admin.entity.RoleEntity;
import org.bees.admin.entity.UserEntity;
import org.bees.admin.repo.PermissionRepository;
import org.bees.admin.repo.RoleRepository;
import org.bees.admin.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDto signup(UserDto userDto) {
        UserEntity user = modelMapper.map(userDto, UserEntity.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setRoles(getRoles());
        UserEntity userEntity = userRepository.save(user);
        return modelMapper.map(userEntity, UserDto.class);
    }


    private Set<RoleEntity> getRoles() {
        Set<RoleEntity> roles = new HashSet<>();
        Optional<RoleEntity> existingRoleEntity = roleRepository.findByName("role_user");
        RoleEntity roleEntity;
        if(existingRoleEntity.isPresent()){
            roleEntity = existingRoleEntity.get();
            roles.add(roleEntity);
        } else {
            roleEntity = new RoleEntity();
            roleEntity.setName("role_user");
            roleEntity.setDescription("Role for user only");
            roleEntity.setPermissions(getPermissions());
            roles.add(roleEntity);
        }
        return roles;
    }

    private Set<PermissionEntity> getPermissions() {
        Set<PermissionEntity> permissions = new HashSet<>();
        Optional<PermissionEntity> existingPermissionEntity = permissionRepository.findByName("can_create_user");
        PermissionEntity permissionEntity = null;
        if(existingPermissionEntity.isPresent()){
            permissionEntity = existingPermissionEntity.get();
           permissions.add(permissionEntity);
        } else {
            permissionEntity = new PermissionEntity();
            permissionEntity.setName("can_create_user");
            permissionEntity.setDescription("Permission for create new user");
            permissions.add(permissionEntity);
        }
        return permissions;
    }
}
