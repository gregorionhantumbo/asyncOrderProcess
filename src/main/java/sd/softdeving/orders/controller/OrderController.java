package sd.softdeving.orders.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sd.softdeving.orders.dto.OrderDTO;
import sd.softdeving.orders.entity.Order;
import sd.softdeving.orders.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> saveOrder(@Valid @RequestBody OrderDTO orderDTO) {
        Order savedOrder = orderService.saveOrder(orderDTO);
        return ResponseEntity.ok("Order saved with ID: " + savedOrder.getOrderId());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
        Order order = orderService.getOrder(orderId);
        return order != null
                ? ResponseEntity.ok(order)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/process/{orderId}")
    public ResponseEntity<String> processOrder(@PathVariable String orderId) {
        orderService.processOrder(orderId);
        return ResponseEntity.ok("Order " + orderId + " is being processed...");
    }
}
