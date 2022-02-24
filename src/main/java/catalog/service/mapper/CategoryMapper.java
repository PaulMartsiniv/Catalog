package catalog.service.mapper;

import catalog.dto.request.CategoryRequestDto;
import catalog.dto.response.CategoryResponseDto;
import catalog.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements RequestDtoMapper<CategoryRequestDto, Category>,
        ResponseDtoMapper<CategoryResponseDto, Category> {

    @Override
    public Category mapToModel(CategoryRequestDto dto) {
        if (dto == null) {
            return null;
        }
        return Category.builder()
                .name(dto.getName())
                .lastUpdate(dto.getLastUpdate())
                .parentCategory(dto.getParentCategory() != null
                        ? mapToModel(dto.getParentCategory()) : null)
                .build();
    }

    @Override
    public CategoryResponseDto toResponseDto(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .lastUpdate(category.getLastUpdate())
                .parentCategory(category.getParentCategory() != null
                        ? toResponseDto(category.getParentCategory()) : null)
                .build();
    }
}
