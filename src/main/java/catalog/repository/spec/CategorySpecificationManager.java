package catalog.repository.spec;

import catalog.model.Category;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CategorySpecificationManager implements SpecificationManager<Category> {
    private final Map<String, SpecificationProvider<Category>> providersMap;

    public CategorySpecificationManager(List<SpecificationProvider<Category>> list) {
        this.providersMap = list.stream()
                .collect(Collectors.toMap(SpecificationProvider::getFilterKey,
                        Function.identity()));
    }

    @Override
    public Specification<Category> get(String filterKey, String[] params) {
        if (!providersMap.containsKey(filterKey)) {
            throw new RuntimeException("Key " + filterKey + " is no supported for data filtering");
        }
        return providersMap.get(filterKey).getSpecification(params);
    }
}
