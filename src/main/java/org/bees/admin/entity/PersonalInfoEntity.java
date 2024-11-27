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
@Table(name = "user_personal_info")
public class PersonalInfoEntity extends BaseEntity {

    @NotEmpty(message = "{user.firstName.NotEmpty}")
    private String firstName;
    @NotEmpty(message = "{user.lastName.NotEmpty}")
    private String lastName;
    @NotEmpty(message = "{user.mobile.NotEmpty}")
    private String mobile;
    private String fatherName;
    private String motherName;
    private Boolean isMarried;

}
