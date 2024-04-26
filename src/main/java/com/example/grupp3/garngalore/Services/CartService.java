package com.example.grupp3.garngalore.Services;


import com.example.grupp3.garngalore.Models.Cart;
import com.example.grupp3.garngalore.Models.Product;
import com.example.grupp3.garngalore.Repositories.CartRepository;
import com.mongodb.MongoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

//    @Autowired
//    private CartRepository cartRepository;

    private final CartRepository cartRepository;

    // Konstruktör för att injicera CartRepository
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }



//    public Cart getCart() {
//        Optional<Cart> optionalCart = cartRepository.findById("1");
//
//        if (optionalCart.isPresent()) {
//            return optionalCart.get();
//        } else {
//            throw new MongoException("Kundvagn hittades inte.");
//        }
//    }

    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    public void saveOrUpdateCart(Cart cart) {
        cartRepository.save(cart);
    }

    public Cart getCartByIpAddress(String ipAddress) {
        Optional<Cart> optionalCart = cartRepository.findByIpAddress(ipAddress);

        if (optionalCart.isPresent()) {
            return optionalCart.get();
        } else {
            Cart cart = new Cart();
            cart.setIpAddress(ipAddress);
            cartRepository.save(cart);
            return cart;
        }
    }

//    public Cart getOrCreateCartForCurrentUser() {
//        Optional<Cart> optionalCart = cartRepository.findById("1");
//
//        if (optionalCart.isPresent()) {
//            return optionalCart.get();
//        } else {
//            Cart cart = new Cart();
//            cart.setId("1");
//            cartRepository.save(cart);
//            return cart;
//        }
//    }



//    public void createCart(Cart cart) {
//        cartRepository.save(cart);
//    }
//
//    public Cart getCartById(String id) {
//        Optional<Cart> optionalCart = cartRepository.findById(id);
//
//        if (optionalCart.isPresent()) {
//            return optionalCart.get();
//        } else {
//            throw new MongoException("Kundvagn med ID " + id + " hittades inte.");
//        }
//    }
//
//    public void deleteCart(String id) {
//        if (cartRepository.existsById(id)) {
//            cartRepository.deleteById(id);
//        } else {
//            throw new MongoException("Kundvagn med ID " + id + " finns inte i databasen.");
//        }
//    }
//
//    public void updateCart(String id, Cart cart) {
//        if (cartRepository.existsById(id)) {
//            cart.setId(id);
//            cartRepository.save(cart);
//        } else {
//            throw new MongoException("Kundvagn med ID " + id + " finns inte i databasen.");
//        }
//    }
//
//    public Iterable<Cart> getAllCarts() {
//        return cartRepository.findAll();
//    }
//
//
//    public void addProductToCart(String userId, Product product) {
//        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
//
//        if (optionalCart.isPresent()) {
//            Cart cart = optionalCart.get();
//            cart.addProduct(product);
//            cartRepository.save(cart);
//        } else {
//            Cart cart = new Cart(userId);
//            cart.addProduct(product);
//            cartRepository.save(cart);
//        }
//    }
//
//    public Cart getCartByUserId(String userId) {
//        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
//
//        if (optionalCart.isPresent()) {
//            return optionalCart.get();
//        } else {
//            throw new MongoException("Kundvagn för användare med ID " + userId + " hittades inte.");
//        }
//    }
//
//    public void removeProductFromCart(String userId, String productId) {
//        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
//
//        if (optionalCart.isPresent()) {
//            Cart cart = optionalCart.get();
//            cart.removeProduct(productId);
//            cartRepository.save(cart);
//        } else {
//            throw new MongoException("Kundvagn för användare med ID " + userId + " hittades inte.");
//        }
//
//    }
//
//    public void updateProductQuantity(String userId, String productId, int quantity) {
//        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
//
//        if (optionalCart.isPresent()) {
//            Cart cart = optionalCart.get();
//            cart.updateProductQuantity(productId, quantity);
//            cartRepository.save(cart);
//        } else {
//            throw new MongoException("Kundvagn för användare med ID " + userId + " hittades inte.");
//        }
//    }
//
//    public void clearCart(String userId) {
//        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
//
//        if (optionalCart.isPresent()) {
//            Cart cart = optionalCart.get();
//            cart.getProductList().clear();
//            cart.setTotalPrice(0);
//            cart.setNumberOfProducts(0);
//            cartRepository.save(cart);
//        } else {
//            throw new MongoException("Kundvagn för användare med ID " + userId + " hittades inte.");
//        }
//    }
}
