package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

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
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;

@Controller
public class ProductAdminController {

    private final UploadService uploadService;
    private final ProductService productService;

    public ProductAdminController(UploadService uploadService, ProductService productService) {
        this.uploadService = uploadService;
        this.productService = productService;
    }

    @GetMapping("/admin/product")
    public String getDashBoard(Model model) {
        List<Product> products = this.productService.getAllProduct();
        model.addAttribute("products", products);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/{id}")
    public String getDetailProduct(Model model, @PathVariable Long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/detail";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProduct(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String postMethodName(@ModelAttribute("newProduct") @Valid Product product, BindingResult productBindingResult, @RequestParam("hoidanitFile") MultipartFile file) {
        List<FieldError> errors = productBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }
        if (productBindingResult.hasErrors()) {
            return "admin/user/create";
        }
        String productFileName = this.uploadService.handleSaveUploadFile(file, "product");
        product.setImage(productFileName);
        this.productService.handleSaveProduct(product);
        return "redirect:" + "/admin/product/show";

    }

    @RequestMapping("/admin/product/update/{id}")
    public String updateProductById(Model model, @PathVariable Long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String handleUpdateProduct(Model model, @ModelAttribute("product") Product product, @RequestParam("hoidanitFile") MultipartFile file) {
        Product currentProduct = this.productService.getProductById(product.getId());
        String productFileName = this.uploadService.handleSaveUploadFile(file, "product");
        if (product != null) {
            currentProduct.setName(product.getName());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setQuantity(product.getQuantity());
            currentProduct.setFactory(product.getFactory());
            currentProduct.setTarget(product.getTarget());
            currentProduct.setDetailDesc(product.getDetailDesc());
            currentProduct.setShortDesc(product.getShortDesc());
            currentProduct.setImage(productFileName);
            this.productService.handleSaveProduct(currentProduct);
        }
        return "redirect:" + "admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProductById(Model model, @PathVariable("id") Long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("id", id);
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String handleProductDelete(Model model, @ModelAttribute("product") Product product) {
        this.productService.handleDeleteProduct(product.getId());
        return "redirect:" + "admin/product";
    }
}
