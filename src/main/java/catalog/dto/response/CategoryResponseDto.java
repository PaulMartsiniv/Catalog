package catalog.dto.response;

import java.time.LocalDateTime;
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
public class CategoryResponseDto {
    private Long id;
    private String name;
    private LocalDateTime lastUpdate;
    private CategoryResponseDto parentCategory;
}
