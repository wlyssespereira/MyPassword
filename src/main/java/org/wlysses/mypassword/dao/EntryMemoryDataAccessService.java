package org.wlysses.mypassword.dao;

import org.springframework.stereotype.Repository;
import org.wlysses.mypassword.model.Entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("memory")
public class EntryMemoryDataAccessService implements EntryDao {

    private static final List<Entry> entryList = new ArrayList<>();

    @Override
    public Integer insertEntry(UUID id, Entry entry) {
        entryList.add(new Entry(id, entry.getTitle(), entry.getUser(), entry.getPasswd(), entry.getNotes(), entry.getUrl(), entry.getEmail()));
        return 1;
    }

    @Override
    public List<Entry> getAll() {
        return entryList;
    }

    @Override
    public Optional<Entry> getEntryById(UUID id) {
        return entryList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public Integer deleteEntryById(UUID id) {
        Optional<Entry> password = getEntryById(id);
        if(password.isPresent()){
            entryList.remove(password.get());
            return 0;
        }
        return 1;
    }

    @Override
    public Integer updateEntryById(UUID id, Entry entry) {
        return getEntryById(id)
                .map(p -> {
                    Integer index = entryList.indexOf(p);
                    if (index >= 0) {
                        entryList.set(index, new Entry(id, entry));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }


}
