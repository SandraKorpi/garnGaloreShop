package com.example.grupp3.garngalore.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private String id;

    private String userID;

    private String userAdress;

    private List<Product> productList;

    private double totalPrice;

    private double withShipping;

    private int numberOfProducts;

    private String paymentMethod;

    private String status;
}
