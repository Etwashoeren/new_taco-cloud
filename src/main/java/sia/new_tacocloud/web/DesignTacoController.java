package sia.new_tacocloud.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import sia.new_tacocloud.Ingredient;
import sia.new_tacocloud.Ingredient.Type;
import sia.new_tacocloud.Order;
import sia.new_tacocloud.Taco;
import sia.new_tacocloud.User;
import sia.new_tacocloud.data.IngredientRepository;
import sia.new_tacocloud.data.TacoRepository;
import sia.new_tacocloud.data.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    private TacoRepository tacoRepo;

    private UserRepository userRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo, UserRepository userRepo) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public String showDesignForm(Model model, Principal principal) {

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);

        // 식자재의 유형을 List에서 필터링한 후 showDesignFrom()의 인자로 전달되는 Model 객체의 속성으로 추가
        Type[] types = Ingredient.Type.values();

        for(Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        String username = principal.getName();
        User user = userRepo.findByUsername(username);
        model.addAttribute("user", user);

        return "design";
    }

    // 식자재의 유형을 필터링하는 메서드
    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @PostMapping// /design 경로의 HTTP POST 요청
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
        if(errors.hasErrors()) {
            return "design";
        }

        Taco saved = tacoRepo.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current"; // /orders/current 상대 경로로 재접속
    }
}
