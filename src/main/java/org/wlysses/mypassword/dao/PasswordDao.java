package org.wlysses.mypassword.dao;

import org.wlysses.mypassword.model.Password;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PasswordDao {

    Integer insertPassword(UUID id, Password password);

    default Integer addPassword(Password password) {
        UUID id = UUID.randomUUID();
        return insertPassword(id, password);
    }

    List<Password> getAll();

    Optional<Password> getPasswordById(UUID id);

    Integer deletePasswordById(UUID id);

    Integer updatePasswordById(UUID id, Password password);

}
