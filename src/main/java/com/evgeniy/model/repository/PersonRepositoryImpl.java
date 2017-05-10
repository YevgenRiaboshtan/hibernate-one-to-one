package com.evgeniy.model.repository;

import com.evgeniy.model.entity.Person;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Yevgen on 2017-05-09.
 */
public class PersonRepositoryImpl implements PersonRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void setUp() {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public List<Person> searchByName(String name) {
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Person.class).get();
        org.apache.lucene.search.Query luceneQuery = qb
                .keyword()
                .onField("firstName")
                .matching(name)
                .createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Person.class);

        return jpaQuery.getResultList();
    }
}
