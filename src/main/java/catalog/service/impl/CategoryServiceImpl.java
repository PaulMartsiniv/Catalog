package catalog.service.impl;

import catalog.model.Category;
import catalog.repository.CategoryRepository;
import catalog.service.CategoryService;
import catalog.util.PaginationUtil;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.getById(id);
    }

    @Override
    public void update(Long id, Category category) {
        category.setId(id);
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> findAll(Integer page, Integer size, String sortBy) {
        return categoryRepository.findAll(PaginationUtil.getPageRequest(page,
                size, sortBy)).toList();
    }
}
