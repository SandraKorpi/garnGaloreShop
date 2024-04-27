package com.example.grupp3.garngalore.Controllers;

import com.example.grupp3.garngalore.Models.Cart;
import com.example.grupp3.garngalore.Models.User;
import com.example.grupp3.garngalore.Repositories.CartRepository;
import com.example.grupp3.garngalore.Repositories.UserRepository;
import com.example.grupp3.garngalore.Services.CartService;
import com.example.grupp3.garngalore.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    // En instans av UserService injiceras här för att hantera användarrelaterad logik
    private final UserService userService;

    // Konstruktör för att injicera UserService genom beroendeinjektion
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Metod för att visa inloggningsformuläret
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        // Lägg till ett tomt användarobjekt i modellen för formuläret
        model.addAttribute("user", new User());
        // Returnera vyn för inloggningsformuläret
        return "LogInPage";
    }

    // Metod för att hantera inloggning av användare
    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            Model model,
                            HttpSession session) {
        // Hämta användaren från databasen med hjälp av UserService
        User existingUser = userService.getUserByEmail(email);
        // Om användaren inte finns, lägg till ett felmeddelande och visa inloggningsformuläret igen
        if (existingUser == null) {
            model.addAttribute("error", "E-postadressen finns inte i vår databas.");
            return "LogInPage";
        }

        // Om lösenordet inte matchar, lägg till ett felmeddelande och visa inloggningsformuläret igen
        if (!existingUser.getPassword().equals(password)) {
            model.addAttribute("error", "Fel lösenord.");
            return "LogInPage";
        }

        // Lagra användarens ID i sessionen för att hålla dem inloggade
        session.setAttribute("loggedInUserId", existingUser.getId());
        // Skriv ut i konsolen för att testa att inloggningen har lyckats
        System.out.println("Användare med ID " + existingUser.getId() + " loggades in.");

        // Omdirigera till startsidan efter inloggningen
        return "redirect:/home";
    }

    // Metod för att logga ut användaren
    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.setAttribute("loggedInUserId", null);
        // Skriv ut i konsolen för att indikera att användaren har loggats ut
        System.out.println("Användaren har loggats ut.");
        // Omdirigera till startsidan efter utloggningen
        return "redirect:/home";
    }

    // Metod för att visa startsidan
    @GetMapping("/home")
    public String index(Model model, HttpSession session) {
        // Variabel för att kontrollera om användaren är inloggad, som börjar som falsk
        boolean loggedIn = false;
        // Försök att hämta användarens ID från sessionen som en sträng
        String loggedInUserId = (String) session.getAttribute("loggedInUserId");
        if (loggedInUserId != null) {
            // Om användarens ID finns i sessionen, sätt loggedIn till true
            loggedIn = true;
        }
        // Lägg till loggedIn-variabeln i modellen för att användas i vyn
        model.addAttribute("loggedIn", loggedIn);
        // Returnera vyn för startsidan
        return "index";
    }
}