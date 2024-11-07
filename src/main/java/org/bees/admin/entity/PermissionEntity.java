package org.bees.admin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Table(name = "permissions")
public class PermissionEntity  extends BaseEntity {

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "{permission.name.NotEmpty}")
    private String name;
    @NotEmpty(message = "{permission.description.NotEmpty}")
    private String description;
    private Boolean active;
}

