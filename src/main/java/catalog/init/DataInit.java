package catalog.init;

import catalog.model.Category;
import catalog.model.Product;
import catalog.service.CategoryService;
import catalog.service.ProductService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInit implements ApplicationRunner {
    private final ProductService productService;
    private final CategoryService categoryService;

    @Override
    public void run(ApplicationArguments args) {
        saveAll();
    }

    private void saveAll() {
        saveIPhones();
        saveXiaomi();
    }

    private void saveXiaomi() {
        Category category = categoryService.save(createCategoryXiaomi());
        Set<Product> set = createXiaomiSet(category);
        set.forEach(productService::save);
    }

    private void saveIPhones() {
        Category category = categoryService.save(createCategoryIPhone());
        Set<Product> set = createIPhonesSet(category);
        set.forEach(productService::save);
    }

    private Set<Product> createXiaomiSet(Category category) {
        return IntStream.range(1, 100)
                .mapToObj(e -> Product.builder()
                        .title("Xiaomi " + (e % 13 + 1))
                        .serialNo("No: x" + e)
                        .price(BigDecimal.valueOf(e % 13 * 100 + e))
                        .country(e % 2 == 0 ? "China" : "Deutschland")
                        .manufactureDate(LocalDate.now())
                        .expiryDate(LocalDate.now()
                                .plusYears(3).plusMonths(e % 11))
                        .category(category)
                        .build())
                .collect(Collectors.toSet());
    }

    private Set<Product> createIPhonesSet(Category category) {
        return IntStream.range(1, 100)
                .mapToObj(e -> Product.builder()
                        .title("iPhone " + (e % 13 + 1))
                        .serialNo("No: i" + e)
                        .price(BigDecimal.valueOf(e % 13 * 100 + e))
                        .country(e % 2 == 0 ? "USA" : "Ukraine")
                        .manufactureDate(LocalDate.now()
                                .minusMonths(e % 13))
                        .expiryDate(LocalDate.now()
                                .plusYears(3).plusMonths(e % 11))
                        .category(category)
                        .build())
                .collect(Collectors.toSet());
    }

    private Category createCategoryXiaomi() {
        Category category = createCategorySmartphones();
        category.setId(3L);
        categoryService.save(category);
        return Category.builder()
                .name("Xiaomi")
                .lastUpdate(LocalDateTime.now().withNano(0))
                .parentCategory(category)
                .build();
    }

    private Category createCategoryIPhone() {
        Category category = createCategorySmartphones();
        category.setId(3L);
        categoryService.save(category);
        return Category.builder()
                .name("IPhone")
                .lastUpdate(LocalDateTime.now().withNano(0))
                .parentCategory(category)
                .build();
    }

    private Category createCategorySmartphones() {
        Category category = createCategoryPhone();
        category.setId(2L);
        categoryService.save(category);
        return Category.builder()
                .name("Smartphones")
                .lastUpdate(LocalDateTime.now().withNano(0))
                .parentCategory(category)
                .build();
    }

    private Category createCategoryPhone() {
        Category parent = createCategoryElectronics();
        parent.setId(1L);
        categoryService.save(parent);
        return Category.builder()
                .name("Phones")
                .lastUpdate(LocalDateTime.now().withNano(0))
                .parentCategory(parent)
                .build();
    }

    private Category createCategoryElectronics() {
        return Category.builder()
                .name("Smartphones, TV and Electronics")
                .lastUpdate(LocalDateTime.now().withNano(0))
                .build();
    }
}
