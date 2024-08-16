package vn.hoidanit.laptopshop.controller.client;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UserService;


@Controller
public class HomepageController {
    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    public HomepageController(ProductService productService,UserService userService,PasswordEncoder passwordEncoder){
        this.productService=productService;
        this.userService=userService;
        this.passwordEncoder=passwordEncoder;
    }
    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Product> products=this.productService.getAllProduct();
        model.addAttribute("products",products);
        return "client/homepage/show";
    }
    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerUser",new RegisterDTO());
        return "client/authentication/register";
    }
    @PostMapping("/register")
    public String handleRegisterUser(@ModelAttribute("registerUser") RegisterDTO register){
        User user=this.userService.RegisterDTOtoUser(register);
        
        String hashPassword=this.passwordEncoder.encode(user.getPassword());
       
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName("USER"));
        this.userService.handleSaveUser(user);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String getLoginPage(Model model){
        
        return "client/authentication/login";
    }
}
