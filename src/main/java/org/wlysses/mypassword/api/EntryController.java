package org.wlysses.mypassword.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Integer> addEntry(@Valid @NonNull @RequestBody Entry entry) {
        Integer response = entryService.addEntry(entry);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Entry> getEntryById(@PathVariable("id") UUID id) {
        Entry response = entryService.getEntryById(id)
                .orElse(null);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Entry>> getAllPaginated(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        List<Entry> response = entryService.getAllPaginated(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public void deleteEntryById(@PathVariable("id") UUID id) {
        entryService.deleteEntry(id);
    }

    @PutMapping(path = "{id}")
    public void updateEntry(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Entry entry) {
        entryService.updateEntry(id, entry);
    }
}
