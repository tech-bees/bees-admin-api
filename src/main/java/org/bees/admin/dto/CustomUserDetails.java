package org.bees.admin.dto;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUserDetails extends User {

    private final Long id;
    private final String email;

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
        this.id = builder.id;
        this.email = builder.email;
    }

    public static class Builder {
        public Long id;
        private String email;
        private String username;
        private String password;
        private Boolean accountNonExpired;
        private Boolean accountNonLocked;
        private Boolean credentialsNonExpired;
        private Boolean enabled;
        private Collection<? extends GrantedAuthority> authorities;

        public Builder withId(Long id){
            this.id = id;
            return this;
        }

        public Builder withEmail(String email){
            this.email = email;
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
