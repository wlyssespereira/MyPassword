package org.wlysses.mypassword.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.wlysses.mypassword.model.Password;
import org.wlysses.mypassword.service.PasswordService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/password")
@RestController
public class PasswordController {

    private final PasswordService passwordService;

    @Autowired
    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping
    public Integer addPassword(@Valid @NonNull @RequestBody Password password) {
        return passwordService.addPassword(password);
    }

    @GetMapping
    public List<Password> getAll() {
        return passwordService.getAll();
    }

    @GetMapping(path = "{id}")
    public Password getPasswordById(@PathVariable("id") UUID id) {
        return passwordService.getPasswordById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePasswordById(@PathVariable("id") UUID id) {
        passwordService.deletePassword(id);
    }

    @PutMapping(path = "{id}")
    public void updatePassword(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Password password) {
        passwordService.updatePassword(id, password);
    }


}
