package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;




@Controller
public class UserController {

    private final UserService userService;
    private final UploadService uploadService;

    public UserController(UploadService uploadService, UserService userService) {
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
    public String getUserUpdateSuccess(Model model, @ModelAttribute("updateUser") User user){
        System.out.println(user);
        User currentUser=this.userService.GetUserByID(user.getId());
        System.out.println("User: "+currentUser);
        if(currentUser!=null){
            currentUser.setAddress(user.getAddress());
            currentUser.setFullName(user.getFullName());
            currentUser.setPhone(user.getPhone());
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
    public String getUserCreateSuccess(Model model, @ModelAttribute("newUser") User user,@RequestParam("hoidanitFile") MultipartFile file){
        String avatarFileName=this.uploadService.handleSaveUploadFile(file,"avatar");
        // this.userService.handleSaveUser(user);
        
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


