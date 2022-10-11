package com.example.demowithtests.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    // This is a JPA annotation. It is used to map the class to a table in the database.
    @Id
    // Telling JPA to use the database's auto-increment feature to generate the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String country;
    private String email;
    private Boolean isFull;
    private Boolean isUpdated;
    private String password;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", isFull=" + isFull +
                ", isUpdated=" + isUpdated +
                ", password='" + password + '\'' +
                '}';
    }
}
