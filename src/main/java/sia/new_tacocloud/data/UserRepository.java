package sia.new_tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import sia.new_tacocloud.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
