package com.evgeniy.model.repository;

import com.evgeniy.model.entity.Person;

import java.util.List;

/**
 * Created by Yevgen on 2017-05-09.
 */
public interface PersonRepositoryCustom {
    List<Person> searchByName(String name);
}
