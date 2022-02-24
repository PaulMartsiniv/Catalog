package catalog.repository;

import catalog.model.Category;
import catalog.model.Product;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {
    @Container
    static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("Catalog")
            .withPassword("1234")
            .withUsername("postgres");

    @DynamicPropertySource
    static void setDataSourceProperties(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url",database::getJdbcUrl);
        propertyRegistry.add("spring.datasource.password",database::getPassword);
        propertyRegistry.add("spring.datasource.username",database::getUsername);
    }

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void getTotalSumFromOneCategory_iPhones_ok() {
        saveIPhone();
        BigDecimal actual = productRepository.getTotalSumFromOneCategory(1L);
        Assertions.assertEquals(BigInteger.valueOf(4545), actual.toBigInteger());
    }

    @Test
    void getTotalSumFromOneCategory_Xiaomi_ok() {
        saveXiaomi();
        BigDecimal actual = productRepository.getTotalSumFromOneCategory(2L);
        Assertions.assertEquals(BigInteger.valueOf(945), actual.toBigInteger());
    }

    private void saveIPhone() {
        Category category = categoryRepository.save(Category.builder()
                .id(1L)
                .name("companyTest1")
                .build());
        for (int i = 0; i < 10; i++) {
            productRepository.save(Product.builder()
                    .title("iPhone X")
                    .serialNo("No: " + i)
                    .price(BigDecimal.valueOf(i % 13 * 100 + i))
                    .country(i % 2 == 0 ? "USA" : "Ukraine")
                    .manufactureDate(LocalDate.now()
                            .minusMonths(i))
                    .expiryDate(LocalDate.now()
                            .plusYears(i).plusMonths(i % 3))
                    .category(category)
                    .build());
        }
    }

    private void saveXiaomi() {
        Category category = categoryRepository.save(Category.builder()
                .id(2L)
                .name("companyTest2")
                .build());
        for (int i = 0; i < 10; i++) {
            productRepository.save(Product.builder()
                    .title("Xiaomi")
                    .serialNo("No: " + i)
                    .price(BigDecimal.valueOf(i % 3 * 100 + i))
                    .country(i % 2 == 0 ? "USA" : "Ukraine")
                    .manufactureDate(LocalDate.now()
                            .minusMonths(i))
                    .expiryDate(LocalDate.now()
                            .plusYears(i).plusMonths(i % 3))
                    .category(category)
                    .build());
        }
    }
}