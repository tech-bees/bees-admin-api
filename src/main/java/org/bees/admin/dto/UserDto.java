package org.bees.admin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    @NotEmpty(message = "{user.firstName.NotEmpty}")
    private String firstName;
    @NotEmpty(message = "{user.lastName.NotEmpty}")
    private String lastName;
    @NotEmpty(message = "{user.email.NotEmpty}")
    @Email(message = "{user.email.Email}")
    private String email;
    @NotEmpty(message = "{user.mobile.NotEmpty}")
    private String mobile;
    private String username;
    @NotEmpty(message = "{user.password.NotEmpty}")
    private String password;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    private Set<RoleDto> roles;
}
