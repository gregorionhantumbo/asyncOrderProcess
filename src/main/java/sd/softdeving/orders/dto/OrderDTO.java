package sd.softdeving.orders.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class OrderDTO {
    private String customer;
    private List<Long> productIds;
    private Map<Long, Integer> quantity;
}
