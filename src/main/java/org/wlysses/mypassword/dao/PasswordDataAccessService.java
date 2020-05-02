package org.wlysses.mypassword.dao;

import org.springframework.stereotype.Repository;
import org.wlysses.mypassword.model.Password;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("passwordDao")
public class PasswordDataAccessService implements PasswordDao {

    private static final List<Password> passwordList = new ArrayList<>();

    @Override
    public Integer insertPassword(UUID id, Password password) {
        passwordList.add(new Password(id, password.getTitle(), password.getUser(), password.getPasswd(), password.getNotes(), password.getUrl(), password.getEmail()));
        return 1;
    }

    @Override
    public List<Password> getAll() {
        return passwordList;
    }

    @Override
    public Optional<Password> getPasswordById(UUID id) {
        return passwordList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public Integer deletePasswordById(UUID id) {
        Optional<Password> password = getPasswordById(id);
        if(password.isPresent()){
            passwordList.remove(password.get());
            return 0;
        }
        return 1;
    }

    @Override
    public Integer updatePasswordById(UUID id, Password password) {
        return getPasswordById(id)
                .map(p -> {
                    Integer index = passwordList.indexOf(p);
                    if (index >= 0) {
                        passwordList.set(index, new Password(id, password));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }


}
