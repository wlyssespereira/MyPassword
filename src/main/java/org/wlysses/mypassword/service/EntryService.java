package org.wlysses.mypassword.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.wlysses.mypassword.dao.EntryDao;
import org.wlysses.mypassword.model.Entry;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EntryService {

    private final EntryDao entryDao;

    @Autowired
    public EntryService(@Qualifier("postgres") EntryDao passwordDao) {
        this.entryDao = passwordDao;
    }

    public Integer addEntry(Entry entry) {
        return entryDao.addEntry(entry);
    }

    public List<Entry> getAll() {
        return entryDao.getAll();
    }

    public Optional<Entry> getEntryById(UUID id) {
        return entryDao.getEntryById(id);
    }

    public Integer deleteEntry(UUID id){
        return entryDao.deleteEntryById(id);
    }

    public Integer updateEntry(UUID id, Entry entry){
        return entryDao.updateEntryById(id, entry);
    }

}
