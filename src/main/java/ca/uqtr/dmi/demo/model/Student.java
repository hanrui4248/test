package ca.uqtr.dmi.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "student")
@Data//lombock
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;



}
