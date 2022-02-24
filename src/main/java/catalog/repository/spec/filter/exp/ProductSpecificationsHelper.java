package catalog.repository.spec.filter.exp;

import catalog.model.Category;
import catalog.model.Product;
import javax.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecificationsHelper {

    public static Specification<Product> withBrandName(final String name) {
        return (root, query, cb) -> {
            final Path<Category> brandPath = root.get("id");
            return cb.equal(brandPath.<String>get("category_id"), name);
        };
    }
}
