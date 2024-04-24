package com.example.grupp3.garngalore.Controllers;

import com.example.grupp3.garngalore.Models.Product;
import com.example.grupp3.garngalore.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Uppdaterat import
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/products")
    public String listProducts(Model model, @RequestParam(required = false) String keyword) {
        List<Product> products;

        if (keyword == null || keyword.isEmpty()) {
            products = productRepository.findAll();
        } else {
            products = productRepository.findByNameContainingIgnoreCase(keyword);
            products.addAll(productRepository.findByCategoryContainingIgnoreCase(keyword));
            products.addAll(productRepository.findByColorContainingIgnoreCase(keyword));
            products.addAll(productRepository.findByDescriptionContainingIgnoreCase(keyword));
        }

        model.addAttribute("products", products);
        //Visar resultatsidan.
        return "ShowSearchPage";
    }
    @GetMapping("/product/{id}")
    public String showProduct(@PathVariable String id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "productInfo";
    }
    @GetMapping("/index")
    public String showIndex(Model model) {
        // Hämta alla produkter från databasen
        List<Product> allProducts = productRepository.findAll();

        // Skapa en lista för att lagra slumpmässiga produkter
        List<Product> randomProducts = getRandomProducts(allProducts, 4); // Hämta 4 slumpmässiga produkter

        // Skicka de slumpmässiga produkterna till vyn
        model.addAttribute("products", randomProducts);

        return "index"; // Namnet på din startsida-HTML-templaten
    }

    // Metod för att hämta ett slumpmässigt antal produkter från en lista
    private List<Product> getRandomProducts(List<Product> productList, int count) {
        Random random = new Random();
        int listSize = productList.size();

        // Skapa en lista för att lagra slumpmässiga produkter
        List<Product> randomProducts = new ArrayList<>();

        // Hämta slumpmässiga produkter från listan baserat på det angivna antalet
        for (int i = 0; i < count; i++) {
            int randomIndex = random.nextInt(listSize);
            randomProducts.add(productList.get(randomIndex));
        }

        return randomProducts;
    }
}
