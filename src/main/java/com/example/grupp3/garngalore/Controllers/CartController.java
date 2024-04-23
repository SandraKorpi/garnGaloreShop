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
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final RestTemplate restTemplate;
    private final OrderService orderService;
    private final ProductService productService;


    @Autowired
    public CartController(CartService cartService, RestTemplate restTemplate, OrderService orderService, ProductService productService ) {
        this.cartService = cartService;
        this.restTemplate = new RestTemplate();
        this.orderService = orderService;
        this.productService = productService;
    }

    @PostMapping("/{userId}/addProduct")
    public ResponseEntity<String> addProductToCart(@PathVariable String userId, @RequestBody Product product) {
        cartService.addProductToCart(userId, product);
        return ResponseEntity.ok("Product added to cart successfully");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable String userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{userId}/removeProduct/{productId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable String userId, @PathVariable String productId) {
        cartService.removeProductFromCart(userId, productId);
        return ResponseEntity.ok("Product removed from cart successfully");
    }

    @PutMapping("/{userId}/updateProductQuantity/{productId}")
    public ResponseEntity<String> updateProductQuantity(@PathVariable String userId, @PathVariable String productId, @RequestParam int quantity) {
        cartService.updateProductQuantity(userId, productId, quantity);
        return ResponseEntity.ok("Product quantity updated successfully");
    }

    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<String> clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("Cart cleared successfully");
    }



    //WEBKONTROLL FÖR CART

    //This method is called when the user navigates to /cart/{userId} in the browser
    //The userId is passed as a path variable
    //The method fetches the cart for the user with the given userId from the database
    @GetMapping("/{userId}.html") //Lagt dit .html för att inte ha två av samma.
    public String getCartByUserId(@PathVariable String userId, Model model) {
        Cart cart = cartService.getCartByUserId(userId);
        model.addAttribute("cart", cart);
        return "ShowCartPage"; //The name of the HTML file to be rendered
    }

    //This method is called when the user clicks pay in the browser
    @PostMapping("/{userId}/pay")
    public ResponseEntity<String> payForCart(@PathVariable String userId) {
        //Skapa en order från varukorgen
        Order order = createOrderFromCart(userId);

        //Spara ordern i databasen
        orderService.createOrder(order);

        // Uppdatera produktkvantiteter
        updateProductQuantities(userId);

        // Rensa varukorgen
        cartService.clearCart(userId);

        return ResponseEntity.ok("Payment successful");
    }

    //This method calls the productService to update the quantity of each product in the inventory
    private void updateProductQuantities(String userId) {
        Cart cart = cartService.getCartByUserId(userId);
        for (Product product : cart.getProductList()) {
            productService.updateProductQuantity(product.getId(), product.getQuantity());
        }
    }

    //This method creates an order from the cart
    private Order createOrderFromCart(String userId) {
        Cart cart = cartService.getCartByUserId(userId);
        Order order = new Order();
        order.setUserID(userId);
        order.setProductList(cart.getProductList());
        order.setTotalPrice(cart.getTotalPrice());
        order.setWithShipping(cart.getWithShipping());
        order.setNumberOfProducts(cart.getNumberOfProducts());
        return order;
    }
}
