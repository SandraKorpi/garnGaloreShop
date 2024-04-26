package com.example.grupp3.garngalore.Controllers;

import com.example.grupp3.garngalore.Models.Cart;
import com.example.grupp3.garngalore.Models.Order;
import com.example.grupp3.garngalore.Models.Product;
import com.example.grupp3.garngalore.Services.CartService;
import com.example.grupp3.garngalore.Services.OrderService;
import com.example.grupp3.garngalore.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
//@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
//    private final ProductService productService;


    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
//        this.productService = new ProductService();
    }

//    @GetMapping("/cart")
//    public String showCartPage(Model model) {
//        model.addAttribute("cart", new Cart());
//        return "Cart";
//    }

    @GetMapping("/cart/{ipAddress}")
    public String showCartPageForIpAddress(@PathVariable("ipAddress") String ipAddress, Model model) {
        // Hämta kundvagnen baserat på IP-adressen
        Cart cart = cartService.getCartByIpAddress(ipAddress);

        if (cart == null) {
            // Om det inte finns någon kundvagn för den angivna IP-adressen, kan du hantera det här
            model.addAttribute("message", "No cart found for the given IP address");
        } else {
            // Om kundvagnen finns, skicka den till vyn för att visa informationen
            model.addAttribute("cart", cart);
        }

        return "Cart";
    }


}
