package vn.hoidanit.laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;


@Controller
public class ProductClientController {
    private final ProductService productService;
    public ProductClientController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping("/product/{id}")
    public String getProductByID(Model model,@PathVariable("id") Long id) {
        Product product=this.productService.getProductById(id);
        model.addAttribute("product",product);
        return "client/product/detail";
    }
    
}
