package sd.softdeving.orders.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sd.softdeving.orders.dto.OrderDTO;
import sd.softdeving.orders.entity.Order;
import sd.softdeving.orders.entity.Product;
import sd.softdeving.orders.repository.OrderRepository;
import sd.softdeving.orders.repository.ProductRepository;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    public Order saveOrder(OrderDTO dto) {
        Order order = new Order();
        order.setCustomer(dto.getCustomer());

        List<Product> products = productRepository.findAllById(dto.getProductIds());
        order.setProductList(products);

        Map<Product, Integer> quantityMap = new HashMap<>();
        for (Product product : products) {
            Integer quantity = dto.getQuantity().get(product.getProductId());
            if (quantity != null) {
                quantityMap.put(product, quantity);
            }
        }

        order.setQuantity(quantityMap);

        return orderRepository.save(order);
    }

    public Order getOrder(String orderId) {
        return orderRepository.findById(Long.valueOf(orderId)).orElse(null);
    }

    public void deleteOrder(String orderId) {
        orderRepository.deleteById(Long.valueOf(orderId));
    }

    @Async("threadPoolTaskExecutor")
    public void processOrder(String orderId) {
        log.info("Processing order {} on thread {}", orderId, Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
            log.info("Order {} processed.", orderId);
        } catch (InterruptedException e) {
            log.error("Error processing order {}", orderId, e);
            Thread.currentThread().interrupt();
        }
    }
}
