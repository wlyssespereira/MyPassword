package org.wlysses.mypassword.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.wlysses.mypassword.model.Entry;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class EntryDataAccessService implements EntryDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EntryDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer insertEntry(UUID id, Entry entry) {
        return null;
    }

    @Override
    public List<Entry> getAll() {
        final String sql = "SELECT * FROM Entry";
        List<Entry> entryList = jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            return new Entry(
                    id,
                    resultSet.getString("title"),
                    resultSet.getString("user"),
                    resultSet.getString("passwd"),
                    resultSet.getString("notes"),
                    resultSet.getString("url"),
                    resultSet.getString("email")
            );
        });
        return entryList;
    }

    @Override
    public Optional<Entry> getEntryById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Integer deleteEntryById(UUID id) {
        return null;
    }

    @Override
    public Integer updateEntryById(UUID id, Entry entry) {
        return null;
    }
}
