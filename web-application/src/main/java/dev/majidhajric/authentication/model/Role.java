package dev.majidhajric.authentication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Role implements GrantedAuthority {
    
    private final String ROLE_PREFIX = "ROLE_";

    @ToString.Include
    private String authority;

    private Collection<? extends Privilege> privileges = Collections.emptyList();

    @Override
    public String getAuthority() {
        if (authority != null && !authority.startsWith(ROLE_PREFIX)) {
            return ROLE_PREFIX + authority;
        } else {
            return authority;
        }
    }
}
