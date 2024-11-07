package org.bees.admin.entity;

import jakarta.persistence.*;
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
@Entity
@Table(name = "users")
public class UserEntity  extends BaseEntity {

    @NotEmpty(message = "{user.firstName.NotEmpty}")
    private String firstName;
    @NotEmpty(message = "{user.lastName.NotEmpty}")
    private String lastName;
    @NotEmpty(message = "{user.email.NotEmpty}")
    @Email(message = "{user.email.Email}")
    private String email;
    @NotEmpty(message = "{user.mobile.NotEmpty}")
    private String mobile;
    @NotEmpty(message = "{user.username.NotEmpty}")
    private String username;
    @NotEmpty(message = "{user.password.NotEmpty}")
    private String password;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "mapping_user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<RoleEntity> roles;
}
