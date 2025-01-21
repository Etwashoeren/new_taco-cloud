package sia.new_tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import sia.new_tacocloud.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
