package com.evgeniy.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

/**
 * Created by Yevgen on 2017-05-08.
 */
@Getter
@Setter
@Entity
@Table(name = "PERSON")
@Indexed
public class Person {
    private final static String SEQ = "personSeq";
    @SequenceGenerator(name = SEQ, sequenceName = "PERSON_SEQ", allocationSize = 1)

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = SEQ, strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "FIRST_NAME", nullable = false)
    @Field
    private String firstName;

    @Column(name = "MIDDLE_NAME", nullable = false)
    @Field
    private String middleName;

    @Column(name = "LAST_NAME", nullable = false)
    @Field
    private String lastName;

    /*@OneToOne(mappedBy = Pesel.PERSON_A, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Pesel pesel;*/
}
