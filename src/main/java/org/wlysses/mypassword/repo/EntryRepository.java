package org.wlysses.mypassword.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.wlysses.mypassword.model.Entry;

import java.util.UUID;

@Repository
public interface EntryRepository extends CrudRepository<Entry, UUID> {}
