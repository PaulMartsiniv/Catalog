package catalog.service;

import catalog.model.Category;
import java.util.List;

public interface CategoryService extends GenericService<Category> {
    List<Category> findAll(Integer page, Integer size, String sortBy);
}
