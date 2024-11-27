package org.bees.admin.service;

import org.bees.admin.controller.response.UserInfoResponse;
import org.bees.admin.dto.CustomUserDetails;
import org.bees.admin.dto.LoginRequest;
import org.bees.admin.dto.SignupRequest;
import org.bees.admin.entity.PermissionEntity;
import org.bees.admin.entity.RoleEntity;
import org.bees.admin.entity.UserEntity;
import org.bees.admin.repo.PermissionRepository;
import org.bees.admin.repo.RoleRepository;
import org.bees.admin.repo.UserRepository;
import org.bees.admin.service.jwt.JwtUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;


    public ResponseEntity<?> login(LoginRequest loginRequest){

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getUsername(),userDetails.getEmail(),roles));
    }

    public ResponseEntity<?> signup(SignupRequest signupRequest) {
        UserEntity user = modelMapper.map(signupRequest, UserEntity.class);
        user.setUsername(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setRoles(getRoles());
        UserEntity userEntity = userRepository.save(user);
        return ResponseEntity.ok(userEntity);
    }


    private Set<RoleEntity> getRoles() {
        Set<RoleEntity> roles = new HashSet<>();
        String userRole = "ROLE_USER";
        Optional<RoleEntity> existingRoleEntity = roleRepository.findByName(userRole);
        RoleEntity roleEntity;
        if(existingRoleEntity.isPresent()){
            roleEntity = existingRoleEntity.get();
            roles.add(roleEntity);
        } else {
            roleEntity = new RoleEntity();
            roleEntity.setName(userRole);
            roleEntity.setDescription("Role for user only");
            roleEntity.setPermissions(getPermissions());
            roles.add(roleEntity);
        }
        return roles;
    }

    private Set<PermissionEntity> getPermissions() {
        Set<PermissionEntity> permissions = new HashSet<>();
        String defaultPermission = "CAN_CREATE_USER";
        Optional<PermissionEntity> existingPermissionEntity = permissionRepository.findByName(defaultPermission);
        PermissionEntity permissionEntity = null;
        if(existingPermissionEntity.isPresent()){
            permissionEntity = existingPermissionEntity.get();
           permissions.add(permissionEntity);
        } else {
            permissionEntity = new PermissionEntity();
            permissionEntity.setName(defaultPermission);
            permissionEntity.setDescription("Permission for create new user");
            permissions.add(permissionEntity);
        }
        return permissions;
    }
}
