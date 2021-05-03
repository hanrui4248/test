package ca.uqtr.dmi.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity()
@Table(name = "testuser")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
