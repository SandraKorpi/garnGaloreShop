package com.example.grupp3.garngalore.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private String description;
    private String category;
    private int quantity;
    private String bild;
    private String color;

    // Använder Allargscontruktor istället för att skriva en.
}