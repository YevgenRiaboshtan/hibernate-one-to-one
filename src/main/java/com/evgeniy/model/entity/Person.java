package com.evgeniy.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Yevgen on 2017-05-08.
 */
@Data
@Entity
@Table(name = "PERSON")
public class Person {
    private final static String SEQ = "personSeq";
    @SequenceGenerator(name = SEQ, sequenceName = "PERSON_SEQ", allocationSize = 1)

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = SEQ, strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToOne(mappedBy = Pesel.PERSON_A, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Pesel pesel;
}
