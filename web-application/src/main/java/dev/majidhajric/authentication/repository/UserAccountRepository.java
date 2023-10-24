package dev.majidhajric.authentication.repository;

import dev.majidhajric.authentication.model.UserAccount;

public interface UserAccountRepository {

    UserAccount findByEmail(String email);

    UserAccount save(UserAccount userAccount);
}
