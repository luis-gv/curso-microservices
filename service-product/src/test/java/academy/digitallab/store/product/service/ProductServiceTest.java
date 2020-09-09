package academy.digitallab.store.product.service;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach()
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);

        Product computer = Product.builder()
                .id(1L)
                .name("computer")
                .category(Category.builder().id(1L).build())
                .price(Double.parseDouble("12.50"))
                .stock(Double.parseDouble("5"))
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(computer));

        when(productRepository.save(computer)).thenReturn(computer);

    }

    @Test
    public void whenValidGetID_ThenReturnProduct(){
        Product productFound = productService.getProduct(1L);
        assertEquals(productFound.getName(), "computer");
    }

    @Test
    public void whenValidateUpdateStock_ThenReturnNewStock(){
        Product newStock = productService.updateStock(1L, Double.parseDouble("8"));
        assertEquals(13, newStock.getStock());
    }
}
