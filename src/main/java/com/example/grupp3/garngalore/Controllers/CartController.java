package com.example.grupp3.garngalore.Controllers;

import com.example.grupp3.garngalore.Models.Cart;
import com.example.grupp3.garngalore.Models.Order;
import com.example.grupp3.garngalore.Models.Product;
import com.example.grupp3.garngalore.Services.CartService;
import com.example.grupp3.garngalore.Services.OrderService;
import com.example.grupp3.garngalore.Services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
//@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;


    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String showCartPage(Model model, HttpSession session) {
        // Retrieve the cart associated with the user's session
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            // If there is no cart associated with the session, you can handle it here
            model.addAttribute("message", "No cart found for the current session");
        } else {
            // If the cart exists, send it to the view to display the information
            model.addAttribute("cart", cart);
        }
        return "Cart";
    }

//    @PostMapping("/cart/checkout")
//    public String checkoutCart(HttpSession session) {
//        // Retrieve the cart associated with the user's session
//        Cart cart = (Cart) session.getAttribute("cart");
//
//        if (cart == null || cart.getNumberOfProducts() == 0) {
//            // If there is no cart or the cart is empty, you can handle it here
//            return "redirect:/cart";
//        }
//
//        // Create an order based on the cart
//        Order order = new Order(cart);
//
//        // Save the order to the database
//        // orderService.saveOrder(order);
//
//        // Clear the cart after checkout
//        cart.getProductList().clear();
//        cart.setTotalPrice(0);
//        cart.setNumberOfProducts(0);
//
//        // Save the updated cart back to the session
//        session.setAttribute("cart", cart);
//
//        return "redirect:/orderConfirmation";
//    }

    @PostMapping ("/cart/clearCart")
    public String clearCart(HttpSession session) {
        // Retrieve the cart associated with the user's session
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            // If there is no cart associated with the session, you can handle it here
            return "redirect:/cart";
        }

        // Clear the cart
        cart.getProductList().clear();
        cart.setTotalPrice(0);
        cart.setNumberOfProducts(0);

        // Save the updated cart back to the session
        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }
}
