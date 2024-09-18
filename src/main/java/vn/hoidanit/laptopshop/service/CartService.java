package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.OrderDetailRepository;
import vn.hoidanit.laptopshop.repository.OrderRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public CartService(CartRepository cartRepository, CartDetailRepository cartDetailRepository,
            UserService userService, ProductService productService, OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository) {
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
        this.productService = productService;
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
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

    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails) {
        for (CartDetail cartDetail : cartDetails) {
            Optional<CartDetail> cdOptional = this.cartDetailRepository.findById(cartDetail.getId());
            if (cdOptional.isPresent()) {
                CartDetail currentCartDetail = cdOptional.get();
                currentCartDetail.setQuantity(cartDetail.getQuantity());
                this.cartDetailRepository.save(currentCartDetail);
            }
        }
    }

    public void handlePlaceOrder(User user, HttpSession session, String receiverName, String receiverAddress, String receiverPhone) {
        Order order = new Order();
        order.setUser(user);
        order.setReceiverName(receiverName);
        order.setReceiverAddress(receiverAddress);
        order.setReceiverPhone(receiverPhone);
        order.setStatus("Pending");

        Cart cart = this.cartRepository.findByUser(user);
        long totalPrice = 0;
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();
            for (CartDetail cd : cartDetails) {
                totalPrice += cd.getPrice() * cd.getQuantity();
            }
            order.setTotalPrice(totalPrice);
            this.orderRepository.save(order);
            if (cartDetails != null) {
                for (CartDetail cd : cartDetails) {
                    totalPrice += cd.getPrice() * cd.getQuantity();
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cd.getProduct());
                    orderDetail.setPrice(cd.getPrice());
                    orderDetail.setQuantity(cd.getQuantity());
                    this.orderDetailRepository.save(orderDetail);
                }
                order.setTotalPrice(totalPrice);
                order = this.orderRepository.save(order);
                for (CartDetail cd : cartDetails) {
                    this.cartDetailRepository.deleteById(cd.getId());
                }

                this.cartRepository.deleteById(cart.getId());

                session.setAttribute("sum", 0);
            }
        }

    }
}
