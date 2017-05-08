package com.evgeniy.model.repository;

import com.evgeniy.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Yevgen on 2017-05-08.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
