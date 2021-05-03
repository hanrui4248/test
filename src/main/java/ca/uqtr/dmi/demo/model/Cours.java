package ca.uqtr.dmi.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cours")
@Data//lombock
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sigle;
    private String title;
    private String description;
    private LocalDateTime updateAt;



}
