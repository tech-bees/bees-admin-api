package org.bees.admin.dto;

import java.util.Set;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    @NotBlank
    @Size(max = 80)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

}