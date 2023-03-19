package io.springbatch.springbatchlecture.job3.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Customer {

    @Id @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    private String birthdate;
}
