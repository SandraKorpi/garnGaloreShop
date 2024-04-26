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

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "LogInPage";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            Model model,
                            HttpSession session) {
        User existingUser = userService.getUserByEmail(email);
        if (existingUser == null) {
            model.addAttribute("error", "E-postadressen finns inte i vår databas.");
            return "LogInPage";
        }

        if (!existingUser.getPassword().equals(password)) {
            model.addAttribute("error", "Fel lösenord.");
            return "LogInPage";
        }

        // Lagra användarens ID i sessionen för att hålla dem inloggade
        session.setAttribute("loggedInUserId", existingUser.getId());

        // model.addAttribute("success", "Du är nu inloggad.");

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.removeAttribute("loggedInUserId");
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String index(Model model, HttpSession session) {
        boolean loggedIn = false;
        try {
            Integer loggedInUserId = Integer.parseInt((String) session.getAttribute("loggedInUserId"));
            loggedIn = true;
        } catch (NumberFormatException | NullPointerException e) {
            loggedIn = false;
        }
        model.addAttribute("loggedIn", loggedIn);
        return "/index"; // Byt ut "index" mot din faktiska startsida
    }
}