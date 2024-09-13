package vn.hoidanit.laptopshop.controller.client;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.service.CartService;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class HomepageController {

    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final CartService cartService;

    public HomepageController(ProductService productService, UserService userService, PasswordEncoder passwordEncoder, CartService cartService) {
        this.productService = productService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String getHomePage(Model model, HttpServletRequest request) {
        List<Product> products = this.productService.getAllProduct();
        HttpSession session = request.getSession(false);
        session.getAttribute("fullName");

        // User user = this.userService.getUserByEmail((String) session.getAttribute("email"));
        // Cart cart = this.cartService.getCartByUser(user);
        // model.addAttribute("cart", cart);
        model.addAttribute("products", products);
        return "client/homepage/show";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/authentication/register";
    }

    @PostMapping("/register")
    public String handleRegisterUser(@ModelAttribute("registerUser") @Valid RegisterDTO register,
            BindingResult bindingResult) {

        User user = this.userService.RegisterDTOtoUser(register);

        if (bindingResult.hasErrors()) {
            return "client/authentication/register";
        }
        String hashPassword = this.passwordEncoder.encode(user.getPassword());

        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName("USER"));
        this.userService.handleSaveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {

        return "client/authentication/login";
    }

    // @PostMapping("/login")
    // public String handleLogin(@ModelAttribute(""))
    @GetMapping("/access-denied")
    public String getAccessDeniedPage() {
        return "client/authentication/access_denied";
    }
}
