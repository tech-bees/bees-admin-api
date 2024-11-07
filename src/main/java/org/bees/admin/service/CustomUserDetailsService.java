package org.bees.admin.service;

import org.bees.admin.dto.CustomUserDetails;
import org.bees.admin.entity.PermissionEntity;
import org.bees.admin.entity.RoleEntity;
import org.bees.admin.entity.UserEntity;
import org.bees.admin.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetails customUser = null;
        UserEntity user = userRepository.findByEmail(username);
        if(user != null){
            customUser = new CustomUserDetails.Builder()
                    .withFirstName(user.getFirstName())
                    .withLastName(user.getLastName())
                    .withEmail(user.getEmail())
                    .withMobile(user.getMobile())
                    .withEnabled(user.getEnabled())
                    .withUsername(user.getUsername())
                    .withPassword(user.getPassword())
                    .withAccountNonExpired(user.getAccountNonExpired())
                    .withAccountNonLocked(user.getAccountNonLocked())
                    .withCredentialsNonExpired(user.getCredentialsNonExpired())
                    .withAuthorities(mapRoleToAuthorities(user.getRoles()))
                    .build();
        }  else {
            throw new UsernameNotFoundException("Invalid username or password!");
        }
        return customUser;
    }

    private Collection<? extends GrantedAuthority> mapRoleToAuthorities(Set<RoleEntity> roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        roles.forEach(role ->{
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            grantedAuthorities.addAll(mapPermissionToAuthorities(role.getPermissions()));
        } );
        return grantedAuthorities;
    }

    private Collection<? extends GrantedAuthority> mapPermissionToAuthorities(Set<PermissionEntity> permissions) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        permissions.forEach(per ->{
            grantedAuthorities.add(new SimpleGrantedAuthority(per.getName()));
        } );
        return grantedAuthorities;
    }
}
