package com.evgeniy.model.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Yevgen on 2017-05-08.
 */
@Data
@ToString(exclude = "person")
@Entity
@Table(name = "PESEL")
public class Pesel {
    public final static String PERSON_A = "person";
    private final static String SEQ = "peselSeq";
    @SequenceGenerator(name = SEQ, sequenceName = "PESEL_SEQ", allocationSize = 1)

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = SEQ, strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "PESEL", nullable = false)
    private Integer pesel;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERSON_FK", nullable = false)
    private Person person;
}
