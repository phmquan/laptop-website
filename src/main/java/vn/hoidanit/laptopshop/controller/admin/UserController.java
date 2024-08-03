package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;




@Controller
public class UserController {

    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;
    
    public UserController(PasswordEncoder passwordEncoder, UploadService uploadService, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.uploadService = uploadService;
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
        return "admin/user/show";
    }
    @RequestMapping("/admin/user/{id}")
    public String getUserByID(Model model,@PathVariable("id") Long id){
        System.out.println("User id: "+id);
        User user=this.userService.GetUserByID(id);
        model.addAttribute("user", user);
        return "admin/user/detail";
    }


    @RequestMapping("/admin/user/update/{id}")
    public String updateUserByID(Model model,@PathVariable("id") Long id){
        User user=this.userService.GetUserByID(id);
        model.addAttribute("updateUser", user);
        return "admin/user/update";
    }
    @PostMapping("/admin/user/update") 
    public String getUserUpdateSuccess(Model model, @ModelAttribute("updateUser") User user, @RequestParam("hoidanitFile") MultipartFile file){
        User currentUser=this.userService.GetUserByID(user.getId());
        String avatarFileName=this.uploadService.handleSaveUploadFile(file,"avatar");
        if(currentUser!=null){
            currentUser.setAddress(user.getAddress());
            currentUser.setFullName(user.getFullName());
            currentUser.setPhone(user.getPhone());
            currentUser.setRole(this.userService.getRoleByName(user.getRole().getName()));
            currentUser.setAvatar(avatarFileName);
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:"+"/admin/user";
    }

    @GetMapping("/admin/user/create")
    public String getUserCreate(Model model){
        model.addAttribute("newUser",new User());
        return "admin/user/create";
    }
    @PostMapping("/admin/user/create")
    public String getUserCreateSuccess(Model model, @ModelAttribute("newUser") @Valid User user, BindingResult newUserBindingResult,@RequestParam("hoidanitFile") MultipartFile file){
        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors ) {
            System.out.println (error.getField() + " - " + error.getDefaultMessage());
        }
        if(newUserBindingResult.hasErrors()){
            return "admin/user/create";
        }
        String avatarFileName=this.uploadService.handleSaveUploadFile(file,"avatar");
        String hashPassword=this.passwordEncoder.encode(user.getPassword());
        user.setAvatar(avatarFileName);
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName(user.getRole().getName()));
        this.userService.handleSaveUser(user);
        return "redirect:"+"/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getUserDelete(Model model,@PathVariable("id") Long id){
        model.addAttribute("user",new User());
        model.addAttribute("id",id);
        return "/admin/user/delete";
    }
    @PostMapping("/admin/user/delete")
    public String handleUserDelete(Model model,@ModelAttribute("user") User user){
        this.userService.handleDeleteUserById(user.getId());
        return "redirect:"+"/admin/user";
    }
}


