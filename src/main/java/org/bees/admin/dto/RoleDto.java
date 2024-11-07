package org.bees.admin.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bees.admin.entity.BaseEntity;
import org.bees.admin.entity.UserEntity;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto  {

    private Long id;
    private String name;
    private String description;
    private Boolean active;
    private Set<PermissionDto> permissions;
}
