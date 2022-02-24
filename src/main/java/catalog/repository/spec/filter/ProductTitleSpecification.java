package catalog.repository.spec.filter;

import catalog.model.Product;
import catalog.repository.spec.SpecificationProvider;
import javax.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductTitleSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "titleIn";
    private static final String FIELD_NAME = "title";

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, cb) -> {
            CriteriaBuilder.In<String> predicate = cb.in(root.get(FIELD_NAME));
            for (String value : params) {
                predicate.value(value);
            }
            return cb.and(predicate, predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
