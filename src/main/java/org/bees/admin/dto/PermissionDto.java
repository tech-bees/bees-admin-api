package org.bees.admin.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bees.admin.entity.BaseEntity;

import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDto {

    private Long id;
    private String name;
    private String description;
    private Boolean active;
    private Set<RoleDto> roles;
}

