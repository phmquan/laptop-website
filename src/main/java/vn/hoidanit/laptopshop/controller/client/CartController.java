package vn.hoidanit.laptopshop.controller.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.CartService;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;

    public CartController(CartService cartService, UserService userService, ProductService productService) {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/cart")
    public String getMethodName(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = this.userService.getUserByEmail((String) session.getAttribute("email"));
        Cart cart = this.cartService.getCartByUser(user);
        Map<CartDetail, Product> cartDetailsWithProductName = new HashMap<>();

        List<CartDetail> cartDetails = this.cartService.getCartDetail(cart);
        for (CartDetail cartDetail : cartDetails) {
            Product product = cartDetail.getProduct();
            cartDetailsWithProductName.put(cartDetail, product);
        }
        model.addAttribute("cartDetail", cartDetailsWithProductName);
        model.addAttribute("cart", cart);

        return "client/cart/detail";
    }

    @PostMapping("/add-to-cart/{id}")
    public String addToCard(@PathVariable("id") Long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long productId = id;
        String email = (String) session.getAttribute("email");
        this.cartService.handleAddProductToCard(email, productId, session);
        return "redirect:/";
    }
}
