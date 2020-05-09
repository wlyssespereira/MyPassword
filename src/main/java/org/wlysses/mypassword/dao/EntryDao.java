package org.wlysses.mypassword.dao;

import org.wlysses.mypassword.model.Entry;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EntryDao {

    default Integer addEntry(Entry entry) {
        UUID id = UUID.randomUUID();
        return insertEntry(id, entry);
    }

    Integer insertEntry(UUID id, Entry entry);

    List<Entry> getAll();

    List<Entry> getAllPaginated(Integer pageNo, Integer pageSize, String sortBy);

    Optional<Entry> getEntryById(UUID id);

    Integer deleteEntryById(UUID id);

    Integer updateEntryById(UUID id, Entry entry);

}
