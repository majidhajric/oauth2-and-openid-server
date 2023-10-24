package dev.majidhajric.authentication.repository;

import dev.majidhajric.authentication.model.UserAccount;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserAccountRepository {

    UserAccount findByEmail(String email) throws UsernameNotFoundException;
}
