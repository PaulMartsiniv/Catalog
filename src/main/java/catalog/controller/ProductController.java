package catalog.controller;

import catalog.dto.request.ProductRequestDto;
import catalog.dto.response.ProductResponseDto;
import catalog.service.ProductService;
import catalog.service.mapper.ProductMapper;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
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
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    @ApiOperation(value = "Create a new product")
    public ProductResponseDto add(@RequestBody ProductRequestDto requestDto) {
        return productMapper.toResponseDto(productService
                .save(productMapper.mapToModel(requestDto)));
    }

    @GetMapping
    @ApiOperation(value = "Get all products")
    public List<ProductResponseDto> findAll(@RequestParam Map<String, String> params,
                                            @RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "10") Integer size,
                                            @RequestParam(defaultValue = "id") String sortBy) {
        return productService.findAll(params, page, size, sortBy).stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get product by 'id'")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.toResponseDto(productService.getById(id));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update a product by 'id'")
    public void updateCompany(@RequestBody ProductRequestDto dto,
                              @PathVariable Long id) {
        productService.update(id, productMapper.mapToModel(dto));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete a product by 'id'")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping(value = "/total/{id}")
    @ApiOperation(value = "get Total sum of products by Category 'id' ")
    List<BigDecimal> getTotalSumFromOneCategory(@PathVariable Long id) {
        return List.of(productService.getTotalSumFromOneCategory(id));
    }
}
