package org.wlysses.mypassword.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.wlysses.mypassword.dao.PasswordDao;
import org.wlysses.mypassword.model.Password;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordService {

    private final PasswordDao passwordDao;

    @Autowired
    public PasswordService(@Qualifier("passwordDao") PasswordDao passwordDao) {
        this.passwordDao = passwordDao;
    }

    public Integer addPassword(Password password) {
        return passwordDao.addPassword(password);
    }

    public List<Password> getAll() {
        return passwordDao.getAll();
    }

    public Optional<Password> getPasswordById(UUID id) {
        return passwordDao.getPasswordById(id);
    }

    public Integer deletePassword(UUID id){
        return passwordDao.deletePasswordById(id);
    }

    public Integer updatePassword(UUID id, Password password){
        return passwordDao.updatePasswordById(id, password);
    }

}
