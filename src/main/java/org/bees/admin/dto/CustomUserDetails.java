package org.bees.admin.dto;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUserDetails extends User {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String mobile;

    public CustomUserDetails(Builder builder) {
        super(
                builder.username,
                builder.password,
                builder.enabled,
                builder.accountNonExpired,
                builder.accountNonLocked,
                builder.credentialsNonExpired,
                builder.authorities
        );
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.mobile = builder.mobile;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private String mobile;
        private String username;
        private String password;
        private Boolean accountNonExpired;
        private Boolean accountNonLocked;
        private Boolean credentialsNonExpired;
        private Boolean enabled;
        private Collection<? extends GrantedAuthority> authorities;

        public Builder withFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public Builder withMobile(String mobile){
            this.mobile = mobile;
            return this;
        }

        public Builder withUsername(String username){
            this.username = username;
            return this;
        }

        public Builder withPassword(String password){
            this.password = password;
            return this;
        }

        public Builder withEnabled(boolean enabled){
            this.enabled = enabled;
            return this;
        }

        public Builder withAccountNonExpired(boolean accountNonExpired){
            this.accountNonExpired = accountNonExpired;
            return this;
        }

        public Builder withAccountNonLocked(boolean accountNonLocked){
            this.accountNonLocked = accountNonLocked;
            return this;
        }

        public Builder withCredentialsNonExpired(boolean credentialsNonExpired){
            this.credentialsNonExpired = credentialsNonExpired;
            return this;
        }

        public Builder withAuthorities(Collection<? extends GrantedAuthority> authorities){
            this.authorities = authorities;
            return this;
        }

        public CustomUserDetails build(){
            return new CustomUserDetails(this);
        }


    }
}
