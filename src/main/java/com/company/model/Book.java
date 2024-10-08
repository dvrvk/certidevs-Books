package com.company.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = false)
    private String title;

    @Column(unique = false)
    private String author;

    @Column(unique = true)
    private String isbn;

    @Column(unique = false)
    private Integer publishedYear;

    @Column(unique = false)
    private Double price;
}
