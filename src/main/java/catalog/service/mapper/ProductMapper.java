package catalog.service.mapper;

import catalog.dto.request.ProductRequestDto;
import catalog.dto.response.ProductResponseDto;
import catalog.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductMapper implements RequestDtoMapper<ProductRequestDto, Product>,
        ResponseDtoMapper<ProductResponseDto, Product> {
    private final CategoryMapper categoryMapper;

    @Override
    public Product mapToModel(ProductRequestDto dto) {
        if (dto == null) {
            return null;
        }
        return Product.builder()
                .title(dto.getTitle())
                .serialNo(dto.getSerialNo())
                .country(dto.getCountry())
                .price(dto.getPrice())
                .manufactureDate(dto.getManufactureDate())
                .expiryDate(dto.getExpiryDate())
                .category(categoryMapper.mapToModel(dto.getCategory()))
                .build();
    }

    @Override
    public ProductResponseDto toResponseDto(Product product) {
        if (product == null) {
            return null;
        }
        return ProductResponseDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .serialNo(product.getSerialNo())
                .country(product.getCountry())
                .price(product.getPrice())
                .manufactureDate(product.getManufactureDate())
                .expiryDate(product.getExpiryDate())
                .category(categoryMapper.toResponseDto(product.getCategory()))
                .build();
    }
}
