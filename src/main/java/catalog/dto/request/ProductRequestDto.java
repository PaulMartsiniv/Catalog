package catalog.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    private String title;
    private String serialNo;
    private String country;
    private BigDecimal price;
    private LocalDate manufactureDate;
    private LocalDate expiryDate;
    private CategoryRequestDto category;
}
