package dev.majidhajric.authentication.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserAccount implements UserDetails {

    @Id
    private String id;

    private String email;

    private String password;

    private boolean enabled;

    private boolean accountNonExpired;

    private boolean credentialsNonExpired;

    private boolean accountNonLocked;

    private Collection<? extends Role> roles;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> result = new HashSet<>();
        if (roles != null && !roles.isEmpty()) {
            result.addAll(roles);
            roles.forEach(role -> result.addAll(role.getPrivileges()));
        }
        return result;
    }
}
