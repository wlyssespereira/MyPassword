package org.wlysses.mypassword.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        final String sql = "INSERT INTO Entry (id, title, \"user\", passwd, notes, url, email) values (?, ?, ?, ?, ?, ?, ?);";
        final Object[] params = {id, entry.getTitle(), entry.getUser(), entry.getPasswd(), entry.getNotes(), entry.getUrl(), entry.getEmail()};
        return jdbcTemplate.update(sql, params);
    }

    @Override
    public List<Entry> getAll() {
        final String sql = "SELECT * FROM Entry";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
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
    }

    @Override
    public Optional<Entry> getEntryById(UUID id) {
        final String sql = "SELECT * FROM Entry where id = ?";
        try {
            Entry entry = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id},
                    (resultSet, i) -> {
                        UUID entryId = UUID.fromString(resultSet.getString("id"));
                        return new Entry(
                                entryId,
                                resultSet.getString("title"),
                                resultSet.getString("user"),
                                resultSet.getString("passwd"),
                                resultSet.getString("notes"),
                                resultSet.getString("url"),
                                resultSet.getString("email")
                        );
                    });
            return Optional.ofNullable(entry);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Integer deleteEntryById(UUID id) {
        final String sql = "DELETE FROM Entry where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Integer updateEntryById(UUID id, Entry entry) {
        final String sql = "UPDATE Entry SET title = ?, \"user\" = ? , passwd = ? , notes = ?, url = ? , email = ? where id = ?";
        final Object[] params = {entry.getTitle(), entry.getUser(), entry.getPasswd(), entry.getNotes(), entry.getUrl(), entry.getEmail(), id};
        return jdbcTemplate.update(sql, params);
    }
}
