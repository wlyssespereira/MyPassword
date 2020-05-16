package org.wlysses.mypassword.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.wlysses.mypassword.model.Entry;
import org.wlysses.mypassword.repo.EntryRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("hsqldb_jpa")
public class EntryDataAccessServiceJpa implements EntryDao {

    private final EntryRepository repository;
    private final EntityManager entityManager;

    @Autowired
    public EntryDataAccessServiceJpa(EntryRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Override
    public Integer insertEntry(UUID id, Entry entry) {
        repository.save(entry);
        return 1;
    }

    @Override
    public List<Entry> getAll() {
        return (List<Entry>) repository.findAll();
    }

    @Override
    public List<Entry> getAllPaginated(Integer pageNo, Integer pageSize, String sortBy) {
        CriteriaBuilder criteriaBuilder = entityManager
                .getCriteriaBuilder();
        CriteriaQuery<Entry> criteriaQuery = criteriaBuilder
                .createQuery(Entry.class);
        Root<Entry> from = criteriaQuery.from(Entry.class);
        CriteriaQuery<Entry> select = criteriaQuery.select(from);
        TypedQuery<Entry> typedQuery = entityManager.createQuery(select);
        typedQuery.setFirstResult(pageNo);
        typedQuery.setMaxResults(pageSize);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<Entry> getEntryById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Integer deleteEntryById(UUID id) {
        repository.deleteById(id);
        return 1;
    }

    @Override
    public Integer updateEntryById(UUID id, Entry entry) {
        Entry entry1 = new Entry(id, entry);
        repository.save(entry1);
        return 0;
    }
}
