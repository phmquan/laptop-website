package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;


@Controller
public class UserController {

    private final UserService userService;
    

    public UserController(UserService userService
    ) {
        this.userService = userService;
    }
    @RequestMapping("/")
    public String getHomepage(Model model, User user){
        List<User> arrUser=this.userService.GetAllUserByEmail("20520713@gm.uit.edu.vn");
        System.out.println(arrUser);
        String test= this.userService.handleHello();
        model.addAttribute("eric","test");
        model.addAttribute("hoidanit","from controller with model");
        return "hello";
    }
    @RequestMapping("/admin/user")
    public String getUser(Model model){
        model.addAttribute("newUser",new User());
        return "admin/user/create";
    }
    @RequestMapping("/admin/user/create")
    public String getUserCreate(Model model, @ModelAttribute("newUser") User hoiDanIt){
        System.out.println("Run here" + hoiDanIt);
        this.userService.handleSaveUser(hoiDanIt);
        return "hello";
    }
}


