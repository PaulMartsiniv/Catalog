package catalog.service;

import catalog.model.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductService extends GenericService<Product> {
    void updateCategory(Long id, Long categoryId);

    BigDecimal getTotalSumFromOneCategory(Long id);

    List<Product> findAll(Integer page, Integer size, String sortBy);

    List<Product> findAll(Map<String, String> params, Integer page, Integer size, String sortBy);
}
