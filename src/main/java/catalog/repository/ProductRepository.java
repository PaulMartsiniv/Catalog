package catalog.repository;

import catalog.model.Product;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {

    @Query(value = "select sum(p.price) "
            + "from products p "
            + "where p.category_id in ("
            + "with recursive cte (id) as ( "
            + "select c1.id "
            + "from categories as c1 "
            + "where c1.parent_category_id =:id "
            + "union all "
            + "select c2.id "
            + "from categories as c2 "
            + "inner join cte "
            + "on c2.parent_category_id = cte.id "
            + ") select * from cte) or p.category_id =:id", nativeQuery = true)
    BigDecimal getTotalSumFromOneCategory(Long id);
}
