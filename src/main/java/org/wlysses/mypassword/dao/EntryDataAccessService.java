package org.wlysses.mypassword.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.wlysses.mypassword.model.Entry;
import org.wlysses.mypassword.rowmapper.EntryRowMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("hsqldb")
public class EntryDataAccessService implements EntryDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EntryDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer insertEntry(UUID id, Entry entry) {
        final String sql = "INSERT INTO ENTRY (id, title, \"user\", passwd, notes, url, email) values (?, ?, ?, ?, ?, ?, ?);";
        final Object[] params = {id, entry.getTitle(), entry.getUser(), entry.getPasswd(), entry.getNotes(), entry.getUrl(), entry.getEmail()};
        return jdbcTemplate.update(sql, params);
    }

    @Override
    public List<Entry> getAll() {
        final String sql = "SELECT * FROM ENTRY";
        return jdbcTemplate.query(sql, new EntryRowMapper());
    }

    @Override
    public List<Entry> getAllPaginated(Integer pageNo, Integer pageSize, String sortBy) {
        final String sql = String.format("SELECT * FROM Entry e ORDER BY %s OFFSET ? ROWS FETCH NEXT ? ROWS ONLY", sortBy);
        final Object[] params = {pageNo, pageSize};
        return jdbcTemplate.query(sql, new EntryRowMapper(), params);
    }

    @Override
    public Optional<Entry> getEntryById(UUID id) {
        final String sql = "SELECT * FROM ENTRY where id = ?";
        try {
            Entry entry = jdbcTemplate.queryForObject(
                    sql,
                    new EntryRowMapper(),
                    id
            );
            return Optional.ofNullable(entry);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Integer deleteEntryById(UUID id) {
        final String sql = "DELETE FROM ENTRY where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Integer updateEntryById(UUID id, Entry entry) {
        final String sql = "UPDATE ENTRY SET title = ?, \"user\" = ? , passwd = ? , notes = ?, url = ? , email = ? where id = ?";
        final Object[] params = {entry.getTitle(), entry.getUser(), entry.getPasswd(), entry.getNotes(), entry.getUrl(), entry.getEmail(), id};
        return jdbcTemplate.update(sql, params);
    }

}
