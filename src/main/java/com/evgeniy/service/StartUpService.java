package com.evgeniy.service;

import com.evgeniy.model.entity.Person;
import com.evgeniy.model.entity.Pesel;
import com.evgeniy.model.repository.PersonRepository;
import com.evgeniy.model.repository.PeselRepository;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Yevgen on 2017-05-08.
 */
@Service
public class StartUpService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PeselRepository peselRepository;

    private Random random = new Random(System.currentTimeMillis());

    public void startUp() {
        insertPersons(10);
        find("3e4r");
        findFull("3e4r");

        /*find("1q2w");
        findFull("1q2w");
        printPersons();
        printPesels();*/
    }

    private void insertPersons(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Count must be greater than 0");
        }
        for (int i = 0; i < count; i++) {
            personRepository.saveAndFlush(buildPersonWithPesel());
        }
    }

    private Person buildPersonWithPesel() {
        final Person person = new Person();
        person.setFirstName(generateName());
        person.setMiddleName(generateName());
        person.setLastName(generateName());
        /*person.setPesel(buildPesel(person));*/
        return person;
    }

    private String generateName() {
        return UUID.randomUUID().toString() + (random.nextBoolean() ? "3e4r" : "1q2w");
    }

    @Nullable
    private Pesel buildPesel(final Person person) {
        if (Objects.nonNull(person)
                && random.nextBoolean()) {
            final Pesel pesel = new Pesel();
            pesel.setPesel(random.nextInt());
            pesel.setPerson(person);
            return pesel;
        }
        return null;
    }

    private void printPersons() {
        final List<Person> persons = personRepository.findAll();
        //persons.forEach(System.out::println);
        System.out.println("Persons count " + persons.size());
    }

    private void printPesels() {
        final List<Pesel> pesels = peselRepository.findAll();
        //pesels.forEach(System.out::println);
        System.out.println("Pesels count " + pesels.size());
    }

    private void find(String name) {
        long start = System.currentTimeMillis();
        String search = "%" + name + "%";
        List<Person> result = personRepository.findByFirstNameLikeOrMiddleNameLikeOrLastNameLike(search, search, search);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("Count for " + name + " = " + result.size());
    }

    private void findFull(String name) {
        long start = System.currentTimeMillis();
        String search = name;
        List<Person> result = personRepository.searchByName(search);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("Count for " + name + " = " + result.size());
    }
}
