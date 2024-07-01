package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        List<User> users=this.userService.GetAllUser();
        model.addAttribute("users", users);
        return "admin/user/showUser";
    }
    @RequestMapping("/admin/user/create")
    public String getUserCreate(Model model){
        model.addAttribute("newUser",new User());
        return "admin/user/create";
    }
    @RequestMapping(value="/admin/user/create",method=RequestMethod.POST)
    public String getUserCreateSuccess(Model model, @ModelAttribute("newUser") User user){
        this.userService.handleSaveUser(user);
        
        return "redirect:"+"/admin/user";
    }
}


