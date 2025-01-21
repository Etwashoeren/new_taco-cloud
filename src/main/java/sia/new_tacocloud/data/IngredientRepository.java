package sia.new_tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import sia.new_tacocloud.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
