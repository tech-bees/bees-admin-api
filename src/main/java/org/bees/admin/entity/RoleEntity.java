package org.bees.admin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "{role.name.NotEmpty}")
    private String name;
    @NotEmpty(message = "{role.description.NotEmpty}")
    private String description;
    private Boolean active;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "mapping_role_permission",
            joinColumns = {
                @JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "permission_id", referencedColumnName = "id")})
    private Set<PermissionEntity> permissions;

    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;
}
