package vn.hoidanit.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.hoidanit.laptopshop.domain.Product;


@Controller
public class ProductAdminController {
    

    @GetMapping("/admin/product")
    public String getDashBoard(){
        return "/admin/product/show";
    }
    @GetMapping("/admin/product/create")
    public String getCreateProduct(Model model){
        model.addAttribute("newProduct",new Product());
        return "/admin/product/create";
    }

}
