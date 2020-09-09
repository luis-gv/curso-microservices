package academy.digitallab.store.product.repository;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProductRepositoryMockTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindByCategory_thenReturnProductList() {
        Product product = Product.builder()
                .name("computer")
                .category(Category.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.09"))
                .status("Created")
                .creatAt(new Date())
                .build();

        productRepository.save(product);

        List<Product> founds = productRepository.findByCategory(product.getCategory());

        assertEquals(3, founds.size(),"Products size error");

    }
}
