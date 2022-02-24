package catalog.repository.spec.filter.exp;

import catalog.model.Category;
import catalog.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> filterProductsByCategoryId(Long categoryId) {
        return (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            Join<Product, Category> products = root.join("products");
            list.add(cb.equal(products.get("id"), categoryId));
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        };
    }
}
