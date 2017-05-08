package com.evgeniy.service;

import com.evgeniy.model.entity.Person;
import com.evgeniy.model.entity.Pesel;
import com.evgeniy.model.repository.PersonRepository;
import com.evgeniy.model.repository.PeselRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

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
        printPersons();
        printPesels();
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
        person.setName("NAME" + random.nextInt());
        person.setPesel(buildPesel(person));
        return person;
    }

    @Nullable
    private Pesel buildPesel(@NotNull final Person person) {
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
        persons.forEach(System.out::println);
        System.out.println("Persons count " + persons.size());
    }

    private void printPesels() {
        final List<Pesel> pesels = peselRepository.findAll();
        pesels.forEach(System.out::println);
        System.out.println("Pesels count " + pesels.size());
    }
}
