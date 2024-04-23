package com.example.grupp3.garngalore.Controllers;

import com.example.grupp3.garngalore.Models.Product;
import com.example.grupp3.garngalore.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Uppdaterat import
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
        return "product";
    }


}
