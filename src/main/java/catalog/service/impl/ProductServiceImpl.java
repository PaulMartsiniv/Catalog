package catalog.service.impl;

import catalog.model.Category;
import catalog.model.Product;
import catalog.repository.CategoryRepository;
import catalog.repository.ProductRepository;
import catalog.repository.spec.SpecificationManager;
import catalog.service.ProductService;
import catalog.util.PaginationUtil;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final SpecificationManager<Product> manager;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public void update(Long id, Product product) {
        product.setId(id);
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void updateCategory(Long id, Long categoryId) {
        Category category = categoryRepository.getById(categoryId);
        Product product = productRepository.getById(id);
        product.setCategory(category);
        productRepository.save(product);
    }

    @Override
    public BigDecimal getTotalSumFromOneCategory(Long id) {
        return productRepository.getTotalSumFromOneCategory(id);
    }

    @Override
    public List<Product> findAll(Integer page, Integer size, String sortBy) {
        return productRepository.findAll(PaginationUtil.getPageRequest(page,
                size, sortBy)).toList();
    }

    @Override
    public List<Product> findAll(Map<String, String> params,
                                 Integer page, Integer size, String sortBy) {
        Specification<Product> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Product> spec = manager.get(entry.getKey(),
                    entry.getValue().split(","));
            specification = specification == null
                    ? Specification.where(spec) : specification.and(spec);
        }
        return productRepository.findAll(specification, PaginationUtil
                .getPageRequest(page, size, sortBy)).toList();
    }
}
