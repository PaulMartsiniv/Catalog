package catalog.controller;

import catalog.dto.request.CategoryRequestDto;
import catalog.dto.response.CategoryResponseDto;
import catalog.service.CategoryService;
import catalog.service.mapper.CategoryMapper;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/categories")
public class CategoryController {
    private final CategoryService productService;
    private final CategoryMapper productMapper;

    @PostMapping
    @ApiOperation(value = "Create a new category")
    public CategoryResponseDto add(@RequestBody CategoryRequestDto requestDto) {
        return productMapper.toResponseDto(productService
                .save(productMapper.mapToModel(requestDto)));
    }

    @GetMapping
    @ApiOperation(value = "Get all categories")
    public List<CategoryResponseDto> findAll(@RequestParam(defaultValue = "0") Integer page,
                                             @RequestParam(defaultValue = "10") Integer size,
                                             @RequestParam(defaultValue = "id") String sortBy) {
        return productService.findAll(page, size, sortBy).stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get category by Id")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return productMapper.toResponseDto(productService.getById(id));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update category")
    public void updateCompany(@RequestBody CategoryRequestDto dto,
                              @PathVariable Long id) {
        productService.update(id, productMapper.mapToModel(dto));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete category")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
