package sd.softdeving.orders.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class OrderDTO {

    @NotEmpty(message = "Customer is required")
    private String customer;

    @NotEmpty(message = "Product list cannot be empty")
    private List<Long> productIds;

    @NotEmpty(message = "Quantities must be entered")
    private Map<Long, Integer> quantity;
}