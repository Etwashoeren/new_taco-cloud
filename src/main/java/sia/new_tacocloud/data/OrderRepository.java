package sia.new_tacocloud.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import sia.new_tacocloud.Order;
import sia.new_tacocloud.User;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable); // 내림차순 정렬: 최신순
}
