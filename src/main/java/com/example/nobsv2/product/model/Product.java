package com.example.nobsv2.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity // Maps java class to MySQL
@Data   // Adds getters and setters
@Table(name = "product")    // Name of the table/relation
public class Product {
    @Id // MySQL primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto generates id
    @Column(name = "id") // SQL column name = "id"
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;
}
