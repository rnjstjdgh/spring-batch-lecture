package io.springbatch.springbatchlecture.job3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Customer {

    @Id @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    private String birthdate;

    @Override
    public String toString() {
        return "id: " + id + ", firstName: " + firstName + ", lastName: " + lastName + ", birthdate: " + birthdate;
    }
}
