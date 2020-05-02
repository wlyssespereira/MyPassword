package org.wlysses.mypassword.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.wlysses.mypassword.model.Entry;
import org.wlysses.mypassword.service.EntryService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/password")
@RestController
public class EntryController {

    private final EntryService entryService;

    @Autowired
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping
    public Integer addPassword(@Valid @NonNull @RequestBody Entry password) {
        return entryService.addEntry(password);
    }

    @GetMapping
    public List<Entry> getAll() {
        return entryService.getAll();
    }

    @GetMapping(path = "{id}")
    public Entry getPasswordById(@PathVariable("id") UUID id) {
        return entryService.getEntryById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePasswordById(@PathVariable("id") UUID id) {
        entryService.deleteEntry(id);
    }

    @PutMapping(path = "{id}")
    public void updatePassword(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Entry password) {
        entryService.updateEntry(id, password);
    }


}
