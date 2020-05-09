package org.wlysses.mypassword.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.wlysses.mypassword.model.Entry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class EntryRowMapper implements RowMapper<Entry> {
    public Entry mapRow(ResultSet rs, int rowNum) throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        return new Entry(
                id,
                rs.getString("title"),
                rs.getString("user"),
                rs.getString("passwd"),
                rs.getString("notes"),
                rs.getString("url"),
                rs.getString("email")
        );
    }
}
