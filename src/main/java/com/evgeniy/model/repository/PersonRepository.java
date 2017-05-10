package com.evgeniy.model.repository;

import com.evgeniy.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yevgen on 2017-05-08.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>, PersonRepositoryCustom {

    List<Person> findByFirstNameLikeOrMiddleNameLikeOrLastNameLike(String fName, String mName, String lName);

}
