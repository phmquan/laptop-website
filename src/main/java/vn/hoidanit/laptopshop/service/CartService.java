package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    private final ProductService productService;

    public CartService(CartDetailRepository cartDetailRepository, CartRepository cartRepository, UserService userService, ProductService productService) {
        this.cartDetailRepository = cartDetailRepository;
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.userService = userService;
    }

    public void handleAddProductToCard(String email, Long productId, HttpSession session) {
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            Cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum(0);
                cart = this.cartRepository.save(newCart);
            }
            Product product = this.productService.getProductById(productId);
            if (product != null) {
                CartDetail cartDetailCheckProduct = this.cartDetailRepository.findByCartAndProduct(cart, product);
                if (cartDetailCheckProduct != null) {
                    cartDetailCheckProduct.setQuantity(cartDetailCheckProduct.getQuantity() + 1);
                    this.cartDetailRepository.save(cartDetailCheckProduct);
                } else {
                    CartDetail cartDetail = new CartDetail();
                    cartDetail.setProduct(product);
                    cartDetail.setQuantity(1);
                    cartDetail.setPrice(product.getPrice());
                    cartDetail.setCart(cart);
                    this.cartDetailRepository.save(cartDetail);

                    int sum = cart.getSum() + 1;
                    cart.setSum(cart.getSum() + 1);
                    session.setAttribute("sum", sum);
                }

            }
        }
    }

    public Cart getCartByUser(User user) {
        return this.cartRepository.findByUser(user);
    }

    public List<CartDetail> getCartDetail(Cart cart) {
        return this.cartDetailRepository.findAllCartDetailByCart(cart);
    }

    public Optional<CartDetail> getCartDetailById(Long id) {
        Optional<CartDetail> currentCart = this.cartDetailRepository.findById(id);
        if (currentCart.isPresent()) {
            return currentCart;
        } else {
            return null;
        }
    }

    public void saveCart(Cart cart) {
        this.cartRepository.save(cart);
    }

    public void deleteCartDetail(Long id) {
        this.cartDetailRepository.deleteById(id);
    }

    public void deleteCart(Cart cart) {
        this.cartRepository.delete(cart);
    }
}
