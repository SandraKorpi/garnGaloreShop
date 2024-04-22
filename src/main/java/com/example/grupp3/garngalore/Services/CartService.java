package com.example.grupp3.garngalore.Services;


import com.example.grupp3.garngalore.Models.Cart;
import com.example.grupp3.garngalore.Repositories.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public void createOrder(Cart cart) {
        cartRepository.save(cart);
    }

    public Cart getCartById(String id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);

        if (optionalCart.isPresent()) {
            return optionalCart.get();
        } else {
            throw new EntityNotFoundException("Kundvagn med ID " + id + " hittades inte.");
        }
    }

    public void deleteCart(String id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Kundvagn med ID " + id + " finns inte i databasen.");
        }
    }

    public void updateCart(String id, Cart cart) {
        if (cartRepository.existsById(id)) {
            cart.setId(id);
            cartRepository.save(cart);
        } else {
            throw new EntityNotFoundException("Kundvagn med ID " + id + " finns inte i databasen.");
        }
    }

    public Iterable<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
}
