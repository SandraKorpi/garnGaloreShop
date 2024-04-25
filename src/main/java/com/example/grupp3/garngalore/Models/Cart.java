package com.example.grupp3.garngalore.Models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "carts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {

    @Id
    private String id;

    private String userId;

//    private List<Product> productList;

    private List<Product> productList = new ArrayList<>();

    private double totalPrice;

    private double withShipping;

    private int numberOfProducts;

    private String ipAddress;

    public double getTotalPrice() {
        // Calculate total price based on products in the cart
        double totalPrice = 0.0;
        for (Product product : productList) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

//    public void setIpAddress(String ipAddress) {
//        this.ipAddress = ipAddress;
//
//    }


//    public Cart(String userId) {
//    }
//
//    public void addProduct(Product product) {
//        productList.add(product);
//        totalPrice += product.getPrice();
//        numberOfProducts++;
//    }
//
//    public void removeProduct(String productId) {
//        for (Product product : productList){
//            if (productId.equals(productId)){
//                product.getPrice();
//                productList.remove(product);
//                totalPrice -= product.getPrice();
//                numberOfProducts --;
//            }
//        }
//    }
//
//    public void updateProductQuantity(String productId, int quantity) {
//        for (Product product : productList){
//            if (productId.equals(productId)){
//                totalPrice -= product.getPrice();
//                product.setQuantity(quantity);
//                totalPrice += product.getPrice();
//            }
//        }
//    }
}

