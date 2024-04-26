package com.example.grupp3.garngalore.Controllers;

import com.example.grupp3.garngalore.Models.Cart;
import com.example.grupp3.garngalore.Models.User;
import com.example.grupp3.garngalore.Repositories.CartRepository;
import com.example.grupp3.garngalore.Repositories.UserRepository;
import com.example.grupp3.garngalore.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    private final CartService cartService;

    @Autowired
    public UserController(CartService cartService) {
        this.cartService= cartService;
    }

    @Autowired
    private UserRepository userRepository;

    // Metod för att visa formulärsidan
    // Metod för att visa formulärsidan
    @GetMapping("/registerUser")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "RegisterPage";
    }

    // Metod för att hantera inkommande POST-begäranden från formuläret
    @PostMapping("/registerUser")
    public String saveUser(User user, Model model) {
        // Kontrollera om e-postadressen redan finns i databasen
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            // E-postadressen finns redan, visa felmeddelande
            model.addAttribute("error", "E-postadressen finns redan i databasen. Vänligen välj en annan e-postadress.");
            return "RegisterPage";
        }

        // Sparar användaren om e-postadressen inte finns redan
        userRepository.save(user);

        // Meddelande för lyckad registrering
        model.addAttribute("success", "Ditt konto hos Garn Galore är nu skapat.");

        // Returnera samma vy för registrering
        return "RegisterPage";
    }

    // Metod för att visa formulärsidan för inloggning
    @GetMapping("/logIn")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "LogInPage"; // Namnet på ditt inloggnings-HTML-templaten
    }
}