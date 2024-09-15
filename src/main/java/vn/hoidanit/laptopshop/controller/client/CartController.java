package vn.hoidanit.laptopshop.controller.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import org.springframework.web.bind.annotation.RequestBody;

import vn.hoidanit.laptopshop.repository.CartDetailRepository;

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

        List<CartDetail> cartDetails = this.cartService.getCartDetail(cart) == null ? new ArrayList<CartDetail>() : this.cartService.getCartDetail(cart);

        double totalPrice = 0;
        for (CartDetail cartDetail : cartDetails) {
            Product product = cartDetail.getProduct();
            cartDetailsWithProductName.put(cartDetail, product);
            totalPrice += cartDetail.getPrice() * cartDetail.getQuantity();
        }

        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cartDetail", cartDetailsWithProductName);

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

    @PostMapping("/delete-cart-product/{id}")
    public String deleteCartProduct(@PathVariable("id") Long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Optional<CartDetail> cartDetail = this.cartService.getCartDetailById(id);
        Cart cart = cartDetail.get().getCart();
        this.cartService.deleteCartDetail(id);
        if (cart.getSum() > 1) {
            cart.setSum(cart.getSum() - 1);
            this.cartService.saveCart(cart);
            session.setAttribute("sum", (Integer) session.getAttribute("sum") - 1);
        } else {
            this.cartService.deleteCart(cart);
            session.setAttribute("sum", 0);
        }
        return "redirect:/cart";
    }

}
